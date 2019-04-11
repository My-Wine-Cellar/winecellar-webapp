INSERT INTO authority (id,authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO authority (id,authority) VALUES (2,'ROLE_USER');

INSERT INTO user (id, enabled, username, password) VALUES ( 999999, true, 'admin', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG');

INSERT INTO user_authority (authority_id, user_id) VALUES ( 1, 999999 );

INSERT INTO user (id, enabled, username, password) VALUES ( 888888, true, 'user', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG');

INSERT INTO user_authority (authority_id, user_id) VALUES ( 2, 888888 );