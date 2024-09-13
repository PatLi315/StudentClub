package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Club;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubDAO {

    private static final String INSERT_CLUB_SQL = "INSERT INTO clubs (name) VALUES (?) RETURNING id";
    private static final String SELECT_LAST_INSERT_ID_SQL = "SELECT currval(pg_get_serial_sequence('clubs', 'id'))";
    private static final String SELECT_ALL_CLUBS_SQL = "SELECT * FROM clubs";
    private static final String CHECK_ADMIN_SQL = "SELECT COUNT(*) FROM admins WHERE student_id = ? AND club_id = ?";

    // Register the operation to add a club with UnitOfWork
    public void addClub(Club club, UnitOfWork unitOfWork) {
        Connection connection = unitOfWork.getConnection();  // Get connection from UnitOfWork

        // Register this operation with UnitOfWork
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLUB_SQL)) {
                // Set the name of the club
                preparedStatement.setString(1, club.getName());

                // Execute the query and get the result set containing the generated ID
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    if (rs.next()) {
                        // Set the ID of the newly created club
                        club.setId(rs.getInt(1));
                    }
                }
            } catch (SQLException e) {
                // If an exception occurs, wrap it in a RuntimeException so it can be caught by UnitOfWork
                throw new RuntimeException("Error occurred while adding the club", e);
            }
        });
    }

    // Get the last inserted club ID
//    public int getLastInsertedClubId(UnitOfWork unitOfWork) throws SQLException {
//        Connection connection = unitOfWork.getConnection();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_INSERT_ID_SQL)) {
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//        }
//        return -1;
//    }

    // Retrieve all clubs
    public List<Club> listAllClubs(UnitOfWork unitOfWork) throws SQLException {
        List<Club> clubs = new ArrayList<>();
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLUBS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //int id = rs.getInt("id");
                String name = rs.getString("name");
                clubs.add(new Club(name));
            }
        }
        return clubs;
    }

    // Check if the logged-in student is an admin
    public boolean isAdmin(int studentId, int clubId, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ADMIN_SQL)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, clubId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
