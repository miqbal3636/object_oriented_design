package com.spsoft.callcenter;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Employee {
    String empId;
    String name;
    public Rank rank;
    volatile Call currentCall;
    CallCenter callCenter;
    private final AtomicBoolean isFree = new AtomicBoolean(true);

    public void setCallCenter(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    public enum Rank {
        OPERATOR, SUPERVISOR, DIRECTOR
    }

    public Employee(String empId, String name, Rank rank) {
        this.empId = empId;
        this.name = name;
        this.rank = rank;
    }

    abstract void escalateCall();

    public void receiveCall(Call call) {
        synchronized (this) {
            this.setFree(false);
            // Associate the call with this employee as call handler
            call.handler = this;
            this.currentCall = call;
            currentCall.callState = Call.CallState.IN_PROGRESS;
        }
        processCall(call, call.handler);

        // If issue is resolved employee will complete the call, otherwise will escalate
        if (currentCall.caller.isIssueResolved()) {
            completeCall();
        } else {
            currentCall.callState = Call.CallState.READY;
            try {
                escalateCall();
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
                completeCall();
            }
        }
    }

    public void completeCall() {
        synchronized (this) {
            this.currentCall.callState = Call.CallState.COMPLETE;
            System.out.println("Call completed with caller id: " + currentCall.caller.callerId + " Call handler: " + currentCall.handler.name + " Type: " + currentCall.handler.rank + " Call status: " + currentCall.callState);
            this.currentCall.caller.setIssueResolved(true);
            this.setFree(true);
        }
    }

    public boolean isFree() {
        return isFree.get() && currentCall == null;
    }

    boolean assignNewCall(Call call) {
        receiveCall(call);
        return true;
    }

    public void setFree(boolean free) {
        this.isFree.set(free);
        this.currentCall = null;
    }

    private void processCall(Call call, Employee employee) {
        try {
            System.out.println("Placing call with callerId: " + call.caller.callerId + " to call handler: " + employee.name + ", Type: " + call.handler.rank);
            Thread.sleep(5000); // Simulate call processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
