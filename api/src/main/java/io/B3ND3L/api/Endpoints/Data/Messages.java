package io.B3ND3L.api.Endpoints.Data;

import io.B3ND3L.api.Repository.Model.Message;

import java.util.List;

public record Messages(List<Message> received, List<Message> sent) {
}
