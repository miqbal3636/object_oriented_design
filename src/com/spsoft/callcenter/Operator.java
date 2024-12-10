package com.spsoft.callcenter;

public class Operator extends Employee {
    public Operator(String empId, String name) {
        super(empId, name, Rank.OPERATOR);
    }

    @Override
    void escalateCall() {
        synchronized (this) {
            this.currentCall.rank = Rank.SUPERVISOR;
            //put call in the queue
            this.callCenter.addCallToQueue(currentCall);
            this.setFree(true); //free the operator for next call
            //notify all supervisor
        }
    }
}
