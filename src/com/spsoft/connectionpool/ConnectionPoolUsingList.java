package com.spsoft.connectionpool;

import java.sql.*;
import java.util.*;


public class ConnectionPoolUsingList implements ConnectionPool {
    private final String url;
    private final String user;
    private final String password;
    private final int poolSize;
    private Driver driver;
    private final Queue<Connection> connectionPool;

    public ConnectionPoolUsingList(String driverClassName, String url, String user, String password, int poolSize) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.poolSize = poolSize;
        this.connectionPool = new LinkedList<>();

        try {
            this.driver = (Driver) Class.forName(driverClassName).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        initializePool();
    }

    private void initializePool() {

        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionPool.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException, InterruptedException {
        return this.getConnection(100);
    }

    @Override
    public synchronized Connection getConnection(long timeout) throws SQLException, InterruptedException {
        long timestamp = System.currentTimeMillis() + timeout;
        while (connectionPool.isEmpty()) {
            long waitTime = timestamp - System.currentTimeMillis();
            if (waitTime <= 0) {
                throw new SQLException("Connection not available");
            }
            try {
                //wait() in java.lang.Object Causes the current thread to wait until it is awakened, typically by being notified or interrupted.
                wait(waitTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InterruptedException("Thread interrupted while waiting for connection.");
            }
        }
        return connectionPool.poll();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionPool.add(connection);
            notifyAll();
        }
    }

    /**
     * Get the number of connections currently in the pool.
     */
    public synchronized int getConnectionsInPool() {
        return connectionPool.size();
    }

    /**
     * Get the number of used connections (i.e., connections not currently in the pool).
     */
    public synchronized int getUsedConnections() {
        return poolSize - connectionPool.size();
    }
}

