package com.quimbaya.clientes_api.shared.cqrs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

@Component
public class SpringCommandBus implements CommandBus {

    private final Map<Class<? extends Command<?>>, CommandHandler<?, ?>> handlersByCommandType;

    public SpringCommandBus(List<CommandHandler<?, ?>> handlers) {
        this.handlersByCommandType = new HashMap<>();
        for (CommandHandler<?, ?> handler : handlers) {
            Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handler.getClass(), CommandHandler.class);
            if (generics == null || generics.length < 1) {
                throw new IllegalStateException(
                        "Cannot resolve Command type for handler: " + handler.getClass().getName());
            }
            @SuppressWarnings("unchecked")
            Class<? extends Command<?>> commandType = (Class<? extends Command<?>>) generics[0];
            CommandHandler<?, ?> previous = this.handlersByCommandType.put(commandType, handler);
            if (previous != null) {
                throw new IllegalStateException(
                        "Multiple handlers registered for command " + commandType.getName());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, C extends Command<R>> R dispatch(C command) {
        CommandHandler<C, R> handler = (CommandHandler<C, R>) handlersByCommandType.get(command.getClass());
        if (handler == null) {
            throw new IllegalStateException(
                    "No CommandHandler registered for " + command.getClass().getName());
        }
        return handler.handle(command);
    }
}
