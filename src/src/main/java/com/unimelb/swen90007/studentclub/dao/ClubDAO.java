package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Club;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubDAO {

    private static final String LIST_CLUBS_SQL = "SELECT * FROM clubs";
    private static final String GET_CLUBS_FOR_STUDENT_SQL = "SELECT clubs.* FROM clubs INNER JOIN memberships ON clubs.id = memberships.club_id WHERE memberships.student_id = ?";

    public List<Club> listAllClubs() throws SQLException {
        List<Club> clubs = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LIST_CLUBS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Club club = new Club (
                rs.getInt("id"),
                rs.getString("name")
                );
                System.out.println("Club ID: " + club.getId() + ", Club Name: " + club.getName());
                clubs.add(club);
            }
        }
        return clubs;
    }

    public List<Club> getClubsForStudent(int studentId, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        List<Club> clubs = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CLUBS_FOR_STUDENT_SQL)) {
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                clubs.add(new Club(id, name));
            }
        }
        return clubs;
    }
}
