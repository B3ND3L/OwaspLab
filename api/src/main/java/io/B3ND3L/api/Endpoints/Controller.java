package io.B3ND3L.api.Endpoints;

import io.B3ND3L.api.Endpoints.Pojos.Login;
import io.B3ND3L.api.Endpoints.Pojos.Token;
import io.B3ND3L.api.Repository.Model.Game;
import io.B3ND3L.api.Repository.Model.User;
import io.B3ND3L.api.Services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) throws SQLException {
        return ResponseEntity.of(databaseService.getUserById(id));
    }

    @GetMapping(value = "/api/game/all")
    public ResponseEntity<List<Game>> getGames(@RequestParam int limit) throws SQLException {
        return ResponseEntity.of(Optional.of(databaseService.getGames(limit)));
    }

    @GetMapping(value = "/api/game/{id}")
    public ResponseEntity<Game> getGame(@PathVariable("id") int id) throws SQLException {
        return ResponseEntity.of(databaseService.getGameById(id));
    }

    @PostMapping(value = "/api/login", produces = "application/json")
    public ResponseEntity<Token> connection(@RequestBody Login login) throws SQLException, NoSuchAlgorithmException {
        return ResponseEntity.of(databaseService.login(login.name(), login.password()));
    }

    @PutMapping(value = "/api/user/{id}/credit/{nbPoints}")
    public ResponseEntity<Boolean> addPoints(@PathVariable int id, @PathVariable int nbPoints) throws SQLException {
        return ResponseEntity.of(Optional.of(databaseService.addAmount(id, nbPoints)));
    }

    @PostMapping(value = "/api/user/{idUser}/buy/{idGame}", produces = "application/json")
    public ResponseEntity<Boolean> buyGame(@PathVariable int idUser, @PathVariable int idGame) throws SQLException {
        return null;
    }
}
