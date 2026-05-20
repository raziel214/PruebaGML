package com.quimbaya.clientes_api.user.domain.model;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public record User(
        Long id,
        String userName,
        String userLastName,
        String userEmail,
        String passwordHash,
        LocalDateTime dateCreation,
        LocalDateTime dateUpdate,
        LocalDateTime dateDelete,
        Short state,
        Short first,
        Integer recoveryPass) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Short STATE_ENABLED = (short) 1;
    private static final Short FIRST_LOGIN_PENDING = (short) 1;
    private static final Integer RECOVERY_PASS_INITIAL = 0;

    public static User createNew(
            String userName,
            String userLastName,
            String userEmail,
            String passwordHash) {
        validateRequired(userName, "userName");
        validateRequired(userLastName, "userLastName");
        validateEmail(userEmail);
        validateRequired(passwordHash, "passwordHash");
        LocalDateTime now = LocalDateTime.now();
        return new User(
                null,
                userName,
                userLastName,
                userEmail,
                passwordHash,
                now,
                now,
                null,
                STATE_ENABLED,
                FIRST_LOGIN_PENDING,
                RECOVERY_PASS_INITIAL);
    }

    public static User reconstitute(
            Long id,
            String userName,
            String userLastName,
            String userEmail,
            String passwordHash,
            LocalDateTime dateCreation,
            LocalDateTime dateUpdate,
            LocalDateTime dateDelete,
            Short state,
            Short first,
            Integer recoveryPass) {
        return new User(
                id, userName, userLastName, userEmail, passwordHash,
                dateCreation, dateUpdate, dateDelete,
                state, first, recoveryPass);
    }

    public User withUpdatedProfile(
            String userName,
            String userLastName,
            String userEmail) {
        validateRequired(userName, "userName");
        validateRequired(userLastName, "userLastName");
        validateEmail(userEmail);
        return new User(
                this.id, userName, userLastName, userEmail, this.passwordHash,
                this.dateCreation, LocalDateTime.now(), this.dateDelete,
                this.state, this.first, this.recoveryPass);
    }

    private static void validateRequired(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("userEmail must be a valid email address");
        }
    }
}
