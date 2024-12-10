package com.spsoft.callcenter;

public class Director extends Employee {
    public Director(String empId, String name){
        super(empId,name, Employee.Rank.DIRECTOR);
    }
    @Override
    void escalateCall() {
        throw new UnsupportedOperationException("Director can not escalate call, They must be able to handle calls");
    }
}
