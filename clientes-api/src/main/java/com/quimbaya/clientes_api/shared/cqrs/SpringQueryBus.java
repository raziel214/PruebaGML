package com.quimbaya.clientes_api.shared.cqrs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

@Component
public class SpringQueryBus implements QueryBus {

    private final Map<Class<? extends Query<?>>, QueryHandler<?, ?>> handlersByQueryType;

    public SpringQueryBus(List<QueryHandler<?, ?>> handlers) {
        this.handlersByQueryType = new HashMap<>();
        for (QueryHandler<?, ?> handler : handlers) {
            Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handler.getClass(), QueryHandler.class);
            if (generics == null || generics.length < 1) {
                throw new IllegalStateException(
                        "Cannot resolve Query type for handler: " + handler.getClass().getName());
            }
            @SuppressWarnings("unchecked")
            Class<? extends Query<?>> queryType = (Class<? extends Query<?>>) generics[0];
            QueryHandler<?, ?> previous = this.handlersByQueryType.put(queryType, handler);
            if (previous != null) {
                throw new IllegalStateException(
                        "Multiple handlers registered for query " + queryType.getName());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, Q extends Query<R>> R dispatch(Q query) {
        QueryHandler<Q, R> handler = (QueryHandler<Q, R>) handlersByQueryType.get(query.getClass());
        if (handler == null) {
            throw new IllegalStateException(
                    "No QueryHandler registered for " + query.getClass().getName());
        }
        return handler.handle(query);
    }
}
