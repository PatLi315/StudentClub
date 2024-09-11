package com.unimelb.swen90007.studentclub.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {

    private Connection connection;
    private List<Runnable> operations;
    private boolean isCommitted = false;

    // Constructor: UnitOfWork receives a connection object
    public UnitOfWork(Connection connection) {
        this.connection = connection;
        this.operations = new ArrayList<>();
    }

    // Method to retrieve the connection
    public Connection getConnection() {
        return this.connection;
    }

    // Register an operation (add, update, delete) to be committed later
    public void registerOperation(Runnable operation) {
        operations.add(operation);
    }

    // Commit all registered operations as a single transaction
    public void commit() throws SQLException {
        try {
            connection.setAutoCommit(false); // Begin transaction

            // Execute all registered operations
            for (Runnable operation : operations) {
                operation.run();
            }

            connection.commit();  // Commit transaction
            isCommitted = true;
        } catch (SQLException e) {
            connection.rollback();  // Rollback if any operation fails
            throw e;
        } finally {
            connection.setAutoCommit(true);  // Reset auto-commit
        }
    }

    // Rollback the transaction if commit wasn't successful
    public void rollback() throws SQLException {
        if (!isCommitted) {
            connection.rollback();
        }
    }

    // Clean up the connection
    public void close() throws SQLException {
        if (!isCommitted) {
            rollback();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
