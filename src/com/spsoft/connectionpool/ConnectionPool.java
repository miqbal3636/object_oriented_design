package com.spsoft.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    /**
     * Returns a connection from this pool if it is available
     * or throws SQLException when no connection available
     * @return connection from this pool
     * @throws SQLException thrown when connection is not available
     */
    public Connection getConnection() throws SQLException, InterruptedException;

    /**
     * Returns a connection from this pool if it is available
     * otherwise waits for no more than timeout milliseconds to get a connection
     * @param timeout timeout in milliseconds
     * @return connection from this pool is it becomes available within timeout milliseconds
     * @throws SQLException thrown when connection is not available
     */
    public Connection getConnection(long timeout) throws SQLException, InterruptedException;

    /**
     * Returns connection to the pool
     * @param connection connection to be returned to the pool
     */
    public void releaseConnection(Connection connection);

    /**
     * Get the number of connections currently in the pool.
     */
    public int getConnectionsInPool();

    /**
     * Get the number of used connections (i.e., connections not currently in the pool).
     */
    public int getUsedConnections();
}
