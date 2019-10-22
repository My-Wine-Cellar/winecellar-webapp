INSERT INTO authority (id, authority)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, authority)
VALUES (2, 'ROLE_USER');

INSERT INTO users (id, enabled, password, first_name, last_name, username, email)
VALUES (999999, true, '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG', 'Paul', 'Pearson', 'paulpearson', 'pauldarlingtonpearson@gmail.com');

INSERT INTO user_authority (authority_id, user_id)
VALUES (1, 999999);

INSERT INTO user_authority (authority_id, user_id)
VALUES (2, 999999);

INSERT INTO users (id, enabled, username, password, first_name, last_name, email)
VALUES (888888, true, 'user1', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG', 'Alice', 'Wonderland', 'alice@wonderland.com');

INSERT INTO user_authority (authority_id, user_id)
VALUES (2, 888888);

INSERT INTO users (id, enabled, username, password, first_name, last_name, email)
VALUES (777777, true, 'user2', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG', 'Bob', 'Paulson', 'bob@fightclub.com');

INSERT INTO user_authority (authority_id, user_id)
VALUES (2, 777777);
