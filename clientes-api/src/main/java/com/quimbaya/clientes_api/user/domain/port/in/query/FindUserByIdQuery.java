package com.quimbaya.clientes_api.user.domain.port.in.query;

import com.quimbaya.clientes_api.shared.cqrs.Query;
import com.quimbaya.clientes_api.user.domain.model.User;

public record FindUserByIdQuery(Long id) implements Query<User> {
}
