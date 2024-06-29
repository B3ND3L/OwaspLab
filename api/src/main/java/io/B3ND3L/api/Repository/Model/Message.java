package io.B3ND3L.api.Repository.Model;

public record Message(int id, int fromUserId, int toUserId, String message) {
}
