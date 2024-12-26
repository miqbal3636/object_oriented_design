package com.spsoft.connectionpool;

import java.sql.*;
import java.util.concurrent.*;


public class ConnectionPoolUsingBlockingQueue implements ConnectionPool {
    private final String url;
    private final String user;
    private final String password;
    private final int poolSize;
    private Driver driver;
    private final BlockingQueue<Connection> connectionPool;

    public ConnectionPoolUsingBlockingQueue(String driverClassName, String url, String user, String password, int poolSize) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.poolSize = poolSize;
        this.connectionPool = new ArrayBlockingQueue<>(poolSize);

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
        return this.getConnection(10);
    }

    @Override
    public Connection getConnection(long timeout) throws SQLException, InterruptedException {
        Connection connection = connectionPool.poll(timeout,TimeUnit.MILLISECONDS);
        if (connection == null) {
            throw new SQLException("Connection not available");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connectionPool.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the number of connections currently in the pool.
     */
    public int getConnectionsInPool() {
        return connectionPool.size();
    }

    /**
     * Get the number of used connections (i.e., connections not currently in the pool).
     */
    public int getUsedConnections() {
        return poolSize - connectionPool.size();
    }
}

