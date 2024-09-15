package com.unimelb.swen90007.studentclub.auth;

import com.unimelb.swen90007.studentclub.dao.StudentDAO;
import com.unimelb.swen90007.studentclub.model.Person;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.PasswordUtils;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class StudentLoginModule implements LoginModule {

    private CallbackHandler callbackHandler;
    private Person student;

    @Override
    public boolean login() throws LoginException {
        // Get the username and password from the callback handler
        NameCallback nameCallback = new NameCallback("username");
        PasswordCallback passwordCallback = new PasswordCallback("password", false);

        Callback[] callbacks = new Callback[] { nameCallback, passwordCallback };

        try {
            callbackHandler.handle(callbacks);
        } catch (Exception e) {
            throw new LoginException("Error getting username and password");
        }

        String username = nameCallback.getName();
        String password = new String(passwordCallback.getPassword());

        // Hash the password for comparison
        String hashedPassword = PasswordUtils.hashPassword(password);

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);
            StudentDAO studentDAO = new StudentDAO();

            // Validate the login
            student = studentDAO.validateLogin(username, hashedPassword, unitOfWork);
            if (student != null) {
                return true;  // Login success
            } else {
                throw new LoginException("Invalid username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoginException("Database error: " + e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return student != null;
    }

    @Override
    public boolean abort() {
        student = null;
        return false;
    }

    @Override
    public boolean logout() {
        student = null;
        return true;
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }
}
