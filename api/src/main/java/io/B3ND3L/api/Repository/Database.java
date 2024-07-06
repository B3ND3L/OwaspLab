package io.B3ND3L.api.Repository;

import io.B3ND3L.api.Repository.Model.Game;
import io.B3ND3L.api.Repository.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class Database {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    /****************
     *
     * USERS
     *
     ****************/

     public Optional<User> getUserById(int id) throws SQLException {
        String query = "SELECT id, name FROM Users WHERE id = " + id;
        return jdbcTemplate.query(query, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                0,
                null)
        ).stream().findFirst();
    }

    public boolean login(String username, String password) throws SQLException {
        String query = "SELECT id, name FROM Users WHERE name = '" + username + "' AND password = '" + password + "'";
        return !jdbcTemplate.query(query, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                0,
                Collections.emptyList()
        )).isEmpty();
    }

    /****************
     *
     * WALLETS
     *
     ****************/

    public Optional<Integer> getUserAmount(int id) throws SQLException {
        String query = "SELECT amount FROM Wallets WHERE UserId = '" + id + "'";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getInt("amount")).stream().findFirst();
    }

    /****************
     *
     * LIBRARIES
     *
     ****************/

    public List<Game> getGamesByUserId(int id) {
        String query = "SELECT * FROM Libraries JOIN Games ON Libraries.gameId = Games.id WHERE UserId = '" + id + "'";
        return jdbcTemplate.query(query, (rs, rowNum) -> new Game(
                rs.getInt("Games.id"),
                rs.getString("name"),
                rs.getInt("price"))
        ).stream().toList();
    }


    public boolean updateWallet(int id, int amount) throws SQLException {
        String query = "UPDATE Wallets SET amount=" + amount + " WHERE userId=" + id;
        return jdbcTemplate.update(query) == 1;
    }

    /****************
     *
     * GAMES
     *
     ****************/

    public List<Game> getGames(int limit) throws SQLException {
        String query = "SELECT * FROM Games LIMIT " + limit;
        return jdbcTemplate.query(query, (rs, rowNum) -> new Game(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"))
        ).stream().toList();
    }

    public List<Game> getGames() throws SQLException {
        String query = "SELECT * FROM Games";
        return jdbcTemplate.query(query, (rs, rowNum) -> new Game(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"))
        ).stream().toList();
    }

    public Optional<Game> getGameById(int id) {
        String query = "SELECT * FROM Games WHERE id = '" + id + "'";
        return jdbcTemplate.query(query, (rs, rowNum) -> new Game(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"))
        ).stream().findFirst();
    }



/*
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
    }*/
}
