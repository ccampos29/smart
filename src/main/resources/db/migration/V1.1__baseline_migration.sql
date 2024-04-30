CREATE TABLE `roles`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(80) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `users`
(
    `id`                  bigint(20)  NOT NULL AUTO_INCREMENT,
    `nombre_completo`     varchar(20) NOT NULL,
    `email`               varchar(45) NOT NULL,
    `password`            varchar(64) NOT NULL,
    `fecha_actualizacion` datetime(6) NOT NULL default now(),
    `fecha_creacion`      datetime(6) NOT NULL default now(),
    `enabled`             bit         NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE if NOT exists `users_roles`
(
    `user_id` BIGINT NOT NULL,
    `rol_id`  BIGINT NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

## CONSTRAINTS

ALTER TABLE `roles`
    ADD CONSTRAINT `UK_b2i715tdnjr1edai4fn7tss63` UNIQUE (`name`);

ALTER TABLE `users`
    ADD CONSTRAINT `UK_6dotkott2kjsp8vw4d0m25fb7` UNIQUE (`email`);

ALTER TABLE `users_roles`
    ADD CONSTRAINT `FKccfs29wkqo18yqkxwd3ye8sq5b` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`);

ALTER TABLE `users_roles`
    ADD CONSTRAINT `FKgd3iendaoyh04b95ykqiase6qh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

## PRE PROCESSED DATA

INSERT INTO `roles`
(`id`, `name`)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO `users`
    (`id`, `nombre_completo`, `email`, `password`)
VALUES (1, 'carlos campos', 'carloscampos807@gmail.com',
        '$2a$10$96MNoRVEvTZMb2zNZAYClOg9sJcGstRYWn9ProdjPEm63B7NImUEq');

INSERT INTO `users_roles`
(`user_id`, `rol_id`)
VALUES (1, 1);