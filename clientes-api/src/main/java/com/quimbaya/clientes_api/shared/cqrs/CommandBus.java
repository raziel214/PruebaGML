package com.quimbaya.clientes_api.shared.cqrs;

public interface CommandBus {

    <R, C extends Command<R>> R dispatch(C command);
}
