package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Club;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubDAO {

    private static final String INSERT_CLUB_SQL = "INSERT INTO clubs (name) VALUES (?)";
    private static final String SELECT_ALL_CLUBS_SQL = "SELECT * FROM clubs";

    public void addClub(Club club) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLUB_SQL)) {
            preparedStatement.setString(1, club.getName());
            preparedStatement.executeUpdate();
        }
    }

    public List<Club> listAllClubs() throws SQLException {
        List<Club> clubs = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLUBS_SQL)) {
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
