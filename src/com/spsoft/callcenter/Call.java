package com.spsoft.callcenter;

public class Call {
    public enum CallState{
        READY, IN_PROGRESS, COMPLETE
    }
    /* Minimal rank of employee who can handle this call */
    Employee.Rank rank;
    Caller caller;
    Employee handler;
    CallState callState;

    public Call(Caller c, Employee.Rank rank){
        this.caller = c;
        this.rank = rank;
        this.callState = Call.CallState.READY;
    }

    public void setHandler(Employee handler) {
        this.handler = handler;
    }

}
