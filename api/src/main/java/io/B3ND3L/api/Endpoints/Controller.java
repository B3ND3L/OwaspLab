package io.B3ND3L.api.Endpoints;

import io.B3ND3L.api.Endpoints.Data.Messages;
import io.B3ND3L.api.Repository.Database;
import io.B3ND3L.api.Repository.Model.Message;
import io.B3ND3L.api.Repository.Model.Points;
import io.B3ND3L.api.Repository.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Database database;

    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        return ResponseEntity.of(database.getUserById(id));
    }

    @GetMapping(value = "/api/user/{id}/points")
    public ResponseEntity<Points> getPoints(@PathVariable int id) throws SQLException {
        Optional<Points> points =  database.getPointsByUserId(id);
        return points.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/api/login", produces = "application/json")
    public ResponseEntity<Boolean> connection(@RequestBody User user) throws SQLException {
        return ResponseEntity.of(Optional.of(database.login(user.name(), user.password())));
    }

    @PutMapping(value = "/api/user/{id}/addPoints/{nbPoints}")
    public ResponseEntity<Boolean> addPoints(@PathVariable int id, @PathVariable int nbPoints) throws SQLException {
        return ResponseEntity.of(Optional.of(database.addPoints(id, nbPoints)));
    }

    @GetMapping(value = "/api/user/{id}/messages")
    public ResponseEntity<Messages> getMessages(@PathVariable int id) throws SQLException {
        return ResponseEntity.of(Optional.of(
                new Messages(
                        database.getMessagesByFromUserId(id).orElse(null),
                        database.getMessagesByToUserId(id).orElse(null))
        ));
    }

    @PutMapping(value = "/api/user/{id}/message", produces = "application/json")
    public ResponseEntity<Boolean> addMessage(@PathVariable int id, @RequestBody Message message) throws SQLException {
        return ResponseEntity.of(Optional.of(database.sendMessage(id, message.fromUserId(), message.message())));
    }
}
