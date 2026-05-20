package com.quimbaya.clientes_api.shared.cqrs;

public interface QueryBus {

    <R, Q extends Query<R>> R dispatch(Q query);
}
