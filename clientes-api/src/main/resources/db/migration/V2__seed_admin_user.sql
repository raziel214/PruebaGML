-- Flyway migration V2: seed initial admin user.
-- Password: 'admin123' hashed with BCrypt strength 12.
-- IMPORTANT: change this password on first login in any real environment.

INSERT INTO `users` (
    `user_name`,
    `user_last_name`,
    `user_email`,
    `user_pass`,
    `date_creation`,
    `date_update`,
    `state`,
    `first`,
    `recovery_pass`
) VALUES (
    'Admin',
    'System',
    'admin@example.com',
    '$2a$12$5jZpoy8N1Twe1KtCgPMNvOY1F.9Y4tEhrtBYzx4yYI6ZWQePCQqp.',
    NOW(6),
    NOW(6),
    1,
    1,
    0
);
