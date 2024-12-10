package com.spsoft.callcenter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CallCenter {

    String name;
    private final Map<Employee.Rank, Queue<Call>> callQueues = new ConcurrentHashMap<>();
    private final Map<Employee.Rank, List<Employee>> allEmployees = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    Runnable assignCallFromQueue = this::checkAvailabilityAndAssign;

    public CallCenter(String name, List<Employee> operators, List<Employee> supervisors, List<Employee> directors) {
        callQueues.put(Employee.Rank.OPERATOR, new LinkedList<>());
        callQueues.put(Employee.Rank.SUPERVISOR, new LinkedList<>());
        callQueues.put(Employee.Rank.DIRECTOR, new LinkedList<>());

        // Associate all employee to this call center
        operators.forEach(o -> o.setCallCenter(this));
        supervisors.forEach(s -> s.setCallCenter(this));
        directors.forEach(d -> d.setCallCenter(this));

        allEmployees.put(Employee.Rank.OPERATOR, operators);
        allEmployees.put(Employee.Rank.SUPERVISOR, supervisors);
        allEmployees.put(Employee.Rank.DIRECTOR, directors);

        // Schedule assignCallFromQueue
        scheduler.scheduleAtFixedRate(assignCallFromQueue, 5, 10, SECONDS);
    }

    // Adds a call to the respective call queue by call handler's rank
    public void addCallToQueue(Call call) {
        lock.lock();
        try {
            Queue<Call> callQueue = callQueues.get(call.rank);
            callQueue.add(call);
            System.out.println("Queue Size for rank: " + call.rank + " is: " + callQueue.size());
        } finally {
            lock.unlock();
        }
    }

    /*
        The handleCall method is designed to manage incoming calls by finding an available employee and assigning the call to them.
        It aims to ensure that call processing is handled concurrently, allowing the system to handle multiple calls efficiently without blocking new incoming calls.
     */
    public void handleCall(Call call) {
        Optional<Employee> employeeOptional = findAvailableEmployee(call.rank);
        // Dispatch call in a new thread so that a new call is not blocked.
        new Thread(() -> {
            dispatchCall(call, employeeOptional);
        }).start();
    }

    /*
        The dispatchCall method is responsible for assigning a call to an available employee if one is present.
        If no employee is available, it adds the call to a waiting queue.
        Input Parameters:
        Call call: The call that needs to be dispatched.
        Optional<Employee> employeeOptional: An Optional that may contain an available employee to handle the call.
     */
    private void dispatchCall(Call call, Optional<Employee> employeeOptional) {
        employeeOptional.ifPresentOrElse(emp -> {
            emp.assignNewCall(call);
            System.out.println("Call :" + call.caller.callerId + " assigned to employee: " + emp.name + " rank: " + emp.rank);
        }, () -> {
            lock.lock();
            try {
                System.out.println("Adding call to queue. Call Handler Rank: " + call.rank + " Caller: " + call.caller.callerId);
                addCallToQueue(call);
            } finally {
                lock.unlock();
            }
        });
    }

    private Optional<Employee> findAvailableEmployee(Employee.Rank rank) {
        lock.lock();
        try {
            List<Employee> employees = allEmployees.get(rank);
            Optional<Employee> employee = employees.stream()
                    .filter(Employee::isFree)
                    .findFirst();

            employee.ifPresent(emp -> {
                // This is the most important part, otherwise first employee in the list will be picked up by all threads.
                emp.setFree(false);
            });

            return employee;
        } finally {
            lock.unlock();
        }
    }

    /*
        The checkAvailabilityAndAssign method is responsible for checking the call queues for each rank of employees and assigning available calls to free employees.
        in a thread-safe manner.
        By using a lock, it prevents concurrent modifications to the call queues and employees' availability status.
    */
    private void checkAvailabilityAndAssign() {
        lock.lock();
        try {
            callQueues.forEach((rank, callQueue) -> {
                System.out.println("Checking call Queue for rank: " + rank.name() + " Queue Size: " + callQueue.size());
                if (!callQueue.isEmpty()) {
                    List<Employee> employees = allEmployees.get(rank);
                    for (Employee employee : employees) {
                        if (!callQueue.isEmpty() && employee.isFree()) {
                            Call call = callQueue.poll();
                            employee.assignNewCall(call);
                            if (callQueue.isEmpty()) {
                                break;
                            }
                        }
                    }
                }
            });
        } finally {
            lock.unlock();
        }
    }
}
