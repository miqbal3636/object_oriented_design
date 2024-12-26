package com.spsoft.connectionpool;

import java.sql.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ConnectionPoolTest {

    String url = "jdbc:mysql://localhost:3306/salesmanager";
    String driverClassName = "com.mysql.cj.jdbc.Driver";
    String user = System.getenv("user");
    String password = System.getenv("password");
    int poolSize = 100;

    ConnectionPool connectionPool = new ConnectionPoolUsingList(driverClassName, url, user, password, poolSize);
    //ConnectionPool connectionPool = new ConnectionPoolUsingBlockingQueue(driverClassName, url, user, password, poolSize);

    public void executeQueries(){
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            IntStream.range(0,1000).forEach(
                    i-> executorService.submit(queryRunnable)
            );
        };
    }

    Runnable queryRunnable =()-> {
        // Get a connection from the pool
        Connection connection = null;
        try {
            connection = connectionPool.getConnection(100);

            Statement statement = connection.createStatement();

            // Execute your SQL queries
            ResultSet rs = statement.executeQuery("select * from salesmanager.product");

            rs.next();

            System.out.println("Product Id: "+rs.getInt(1));

            System.out.println("Thread: "+ Thread.currentThread().threadId()+ " Connections in Pool: " + connectionPool.getConnectionsInPool()+" Used Connections: "+ connectionPool.getUsedConnections());

            // Release the connection back to the pool
            connectionPool.releaseConnection(connection);

        } catch (SQLException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    };



    public static void main(String[] args) {
        ConnectionPoolTest test = new ConnectionPoolTest();
        test.executeQueries();
    }
}

