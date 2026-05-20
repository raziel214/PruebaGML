package com.quimbaya.clientes_api.shared.cqrs;

public interface QueryHandler<Q extends Query<R>, R> {

    R handle(Q query);
}
