package com.spsoft.callcenter;

public class Supervisor extends Employee {
    public Supervisor(String empId, String name) {
        super(empId, name, Rank.SUPERVISOR);
    }

    @Override
    void escalateCall() {
        synchronized (this) {
            this.currentCall.rank = Rank.DIRECTOR;
            //put call in the queue.
            this.callCenter.addCallToQueue(currentCall);
            this.setFree(true); //free the operator for next call
            //notify all Director
        }
    }
}
