package com.quimbaya.clientes_api.user.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.quimbaya.clientes_api.user.domain.model.User;
import com.quimbaya.clientes_api.user.infrastructure.persistence.entity.UserEntity;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserEntity(
                user.id(),
                user.userName(),
                user.userLastName(),
                user.userEmail(),
                user.passwordHash(),
                user.dateCreation(),
                user.dateUpdate(),
                user.dateDelete(),
                user.state(),
                user.first(),
                user.recoveryPass());
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return User.reconstitute(
                entity.getId(),
                entity.getUserName(),
                entity.getUserLastName(),
                entity.getUserEmail(),
                entity.getPasswordHash(),
                entity.getDateCreation(),
                entity.getDateUpdate(),
                entity.getDateDelete(),
                entity.getState(),
                entity.getFirst(),
                entity.getRecoveryPass());
    }
}
