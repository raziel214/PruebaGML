package com.quimbaya.clientes_api.cliente.domain.model;

public record Cliente(
        Long id,
        String sharedKey,
        String nombre,
        String apellido,
        String correo,
        String telefono) {

    public static Cliente createNew(
            String sharedKey,
            String nombre,
            String apellido,
            String correo,
            String telefono) {
        validate(sharedKey, nombre, apellido, correo, telefono);
        return new Cliente(null, sharedKey, nombre, apellido, correo, telefono);
    }

    public static Cliente reconstitute(
            Long id,
            String sharedKey,
            String nombre,
            String apellido,
            String correo,
            String telefono) {
        return new Cliente(id, sharedKey, nombre, apellido, correo, telefono);
    }

    public Cliente withUpdatedContactInfo(
            String sharedKey,
            String nombre,
            String apellido,
            String correo,
            String telefono) {
        validate(sharedKey, nombre, apellido, correo, telefono);
        return new Cliente(this.id, sharedKey, nombre, apellido, correo, telefono);
    }

    private static void validate(
            String sharedKey,
            String nombre,
            String apellido,
            String correo,
            String telefono) {
        requireNonBlank(sharedKey, "sharedKey");
        requireNonBlank(nombre, "nombre");
        requireNonBlank(apellido, "apellido");
        requireNonBlank(correo, "correo");
        requireNonBlank(telefono, "telefono");
    }

    private static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }
}
