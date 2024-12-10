package com.spsoft.callcenter;

import java.util.ArrayList;
import java.util.List;


public class CallCenterTest {
    public static void main(String args[]){

        List<Employee> operators = new ArrayList<>();

        operators.add(new Operator("1","Bob"));
        operators.add(new Operator("2","Alex"));
        operators.add(new Operator("3","Tom"));
        operators.add(new Operator("4","Tina"));

        List<Employee> supervisors = new ArrayList<>();
        supervisors.add(new Supervisor("5","Adam"));
        supervisors.add(new Supervisor("6","Josh"));


        List<Employee> directors = new ArrayList<>();
        directors.add(new Director("7","John"));
        directors.add(new Director("8","Jain"));

        CallCenter callCenter = new CallCenter("Verizon Wireless", operators,supervisors,directors);

        callCenter.handleCall(new Call(new Caller("1"), Employee.Rank.OPERATOR));
        callCenter.handleCall(new Call(new Caller("2"), Employee.Rank.OPERATOR));
        callCenter.handleCall(new Call(new Caller("3"), Employee.Rank.OPERATOR));
        callCenter.handleCall(new Call(new Caller("4"), Employee.Rank.OPERATOR));
        callCenter.handleCall(new Call(new Caller("5"), Employee.Rank.OPERATOR));

        //Extra Credit
        callCenter.handleCall(new Call(new Caller("6"), Employee.Rank.SUPERVISOR));
        callCenter.handleCall(new Call(new Caller("7"), Employee.Rank.DIRECTOR));


    }
}
