INSERT INTO authority (id, authority)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, authority)
VALUES (2, 'ROLE_USER');

INSERT INTO users (id, enabled, username, password, first_name, last_name, email)
VALUES (1, true, 'wineadmin', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG', 'Admin', 'McSecurity', 'root@mywinecellar.info');

INSERT INTO user_authority (authority_id, user_id)
VALUES (1, 1);

INSERT INTO user_authority (authority_id, user_id)
VALUES (2, 1);

INSERT INTO users (id, enabled, username, password, first_name, last_name, email)
VALUES (2, true, 'wineuser1', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG', 'Alice', 'Wonderland', 'alice@wonderland.com');

INSERT INTO user_authority (authority_id, user_id)
VALUES (2, 2);

INSERT INTO users (id, enabled, username, password, first_name, last_name, email)
VALUES (3, true, 'wineuser2', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG', 'Bob', 'Paulson', 'bob@fightclub.com');

INSERT INTO user_authority (authority_id, user_id)
VALUES (2, 3);
