package com.spsoft.callcenter;

import java.util.concurrent.ThreadLocalRandom;

public class Caller {
    String callerId;
    private volatile boolean isIssueResolved;

    public Caller(String callerId) {
        this.callerId = callerId;
    }

    public void setIssueResolved(boolean flag) {
        this.isIssueResolved = flag;
    }

    public boolean isIssueResolved() {
        isIssueResolved = ThreadLocalRandom.current().nextDouble() < 0.5;
        return isIssueResolved;
    }
}
