INSERT INTO authority (id,authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO authority (id,authority) VALUES (2,'ROLE_USER');

INSERT INTO user (id, enabled, username, password) VALUES ( 999999, true, 'paulpearson', '$2a$10$/GlmRbPOiK31IhckttEeFuNadoELtgzixJVquUQR3xhlv9HfOjy5q');

INSERT INTO user_authority (authority_id, user_id) VALUES ( 1, 999999 );

INSERT INTO user (id, enabled, username, password) VALUES ( 888888, true, 'pauldpearson', '$2a$10$/GlmRbPOiK31IhckttEeFuNadoELtgzixJVquUQR3xhlv9HfOjy5q');

INSERT INTO user_authority (authority_id, user_id) VALUES ( 2, 888888 );