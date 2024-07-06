package io.B3ND3L.api.Repository.Model;

import java.util.List;

public record User(int id, String name, int amount, List<Game> library) {
}
