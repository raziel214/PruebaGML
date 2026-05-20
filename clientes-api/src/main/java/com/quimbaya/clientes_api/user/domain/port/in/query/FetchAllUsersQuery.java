package com.quimbaya.clientes_api.user.domain.port.in.query;

import java.util.List;

import com.quimbaya.clientes_api.shared.cqrs.Query;
import com.quimbaya.clientes_api.user.domain.model.User;

public record FetchAllUsersQuery() implements Query<List<User>> {
}
