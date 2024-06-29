package io.B3ND3L.api.Repository;

import io.B3ND3L.api.Repository.Model.Message;
import io.B3ND3L.api.Repository.Model.Points;
import io.B3ND3L.api.Repository.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class Database {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = " + id;
        return jdbcTemplate.query(query, (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"))).stream().findFirst();
    }

    public Optional<Points> getPointsByUserId(int id) throws SQLException {
        String query = "SELECT NbPoints FROM Points WHERE UserId = '" + id + "'";

        return jdbcTemplate.query(query, (rs, rowNum) -> new Points(rs.getInt("nbPoints"))).stream().findFirst();
    }

    public boolean login(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE name = '" + username + "' AND password = '" + password + "'";
        return !jdbcTemplate.query(query, (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"))).isEmpty();
    }

    public boolean updatePoints(Points points, int id) throws SQLException {
        String query = "UPDATE Points SET nbPoints=" + points.nbPoints() + " WHERE userId=" + id;
        return jdbcTemplate.update(query) == 1;
    }

    public boolean addPoints(int id, int nbPoints) throws SQLException {
        Optional<Points> optionalPoints = getPointsByUserId(id);
        if (optionalPoints.isPresent()) {
            Points points = optionalPoints.get();

            Points newPoints = new Points(points.nbPoints() + nbPoints);
            return updatePoints(newPoints, id);
        }
        return false;
    }

    public Optional<List<Message>> getMessagesByFromUserId(int id) throws SQLException {
        String query = "SELECT * FROM messages WHERE fromUserId = " + id;
        return Optional.of(jdbcTemplate.query(query, (rs, rowNum) -> new Message(rs.getInt("id"), rs.getInt("fromUserId"),  rs.getInt("toUserId"), rs.getString("message"))));
    }

    public Optional<List<Message>> getMessagesByToUserId(int id) throws SQLException {
        String query = "SELECT * FROM messages WHERE toUserId = " + id;
        return Optional.of(jdbcTemplate.query(query, (rs, rowNum) -> new Message(rs.getInt("id"), rs.getInt("fromUserId"),  rs.getInt("toUserId"), rs.getString("message"))));
    }

    public boolean sendMessage(int fromUserId, int toUserId, String message) throws SQLException {
        String query = "INSERT INTO messages (fromUserId, toUserId, message) VALUES ("+fromUserId+", "+toUserId+", "+message+")";
        return jdbcTemplate.update(query) == 1;
    }
}
