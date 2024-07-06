package io.B3ND3L.api.Services;

import io.B3ND3L.api.Endpoints.Pojos.Token;
import io.B3ND3L.api.Repository.Database;
import io.B3ND3L.api.Repository.Model.Game;
import io.B3ND3L.api.Repository.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseService {

    @Autowired
    Database database;

    public Optional<User> getUserById(int id) throws SQLException {
        Optional<User> optionalUser = database.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            int amount = database.getUserAmount(id).orElse(0);
            List<Game> games = getGamesByUserId(id);
            return Optional.of(new User(user.id(), user.name(), amount, games));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Token> login(String username, String password) throws SQLException, NoSuchAlgorithmException {
        if( database.login(username, password)) {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    username.getBytes());
            return Optional.of(new Token(Base64.getEncoder().encodeToString(encodedhash)));
        } else {
            return Optional.empty();
        }
    }

    /****************
     *
     * WALLETS
     *
     ****************/

    public boolean updateWallet(int id, int amount) throws SQLException {
        return database.updateWallet(id, amount);
    }

    public boolean addAmount(int id, int amount) throws SQLException {
        Optional<User> optionalUser = getUserById(id);
        if (optionalUser.isPresent()) {
            int newAmount = optionalUser.get().amount() + amount;
            return updateWallet(id, newAmount);
        }
        return false;
    }

    /****************
     *
     * LIBRARIES
     *
     ****************/

    public List<Game> getGamesByUserId(int id) {
        return database.getGamesByUserId(id);
    }


    /****************
     *
     * GAMES
     *
     ****************/

    public List<Game> getGames(int limit) throws SQLException {
        if (limit > 0) {
            return database.getGames(limit);
        } else {
            return database.getGames();
        }
    }

    public Optional<Game> getGameById(int id) {
        return database.getGameById(id);
    }
}
