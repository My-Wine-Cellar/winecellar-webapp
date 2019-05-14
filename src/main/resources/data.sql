INSERT INTO authority (id, authority)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, authority)
VALUES (2, 'ROLE_USER');

INSERT INTO users (id, enabled, username, password)
VALUES (999999, true, 'admin', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG');

INSERT INTO user_authority (authority_id, user_id)
VALUES (1, 999999);

INSERT INTO users (id, enabled, username, password)
VALUES (888888, true, 'user1', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG');

INSERT INTO user_authority (authority_id, user_id)
VALUES (2, 888888);

INSERT INTO users (id, enabled, username, password)
VALUES (777777, true, 'user2', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG');

INSERT INTO user_authority (authority_id, user_id)
VALUES (2, 777777);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Russian River Valley', 'USA', 'Moshin Vineyards', 'California', 888888);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Willamette Valley', 'USA', 'Brickhouse', 'Oregon', 888888);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Napa', 'USA', 'Silver Oak', 'California', 888888);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Sonoma', 'USA', 'Ravenswood', 'California', 888888);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Walla Walla', 'USA', 'Locati Cellars', 'Washington', 888888);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Turin', 'ITA', 'Signorvino Torino', 'Piedmont', 777777);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Cote de Beaune', 'FRA', 'Chateau de Pommard', 'Burgundy', 777777);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Marlborough', 'NWZ', 'Yealands Estate', 'N/A', 777777);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Rioja', 'ESP', 'Bodegas Baigorri', 'Rioja', 777777);

INSERT INTO producer (appellation, country, name, province, user_id)
VALUES ('Mendoza', 'ARG', 'Dona Paula', 'Mendoza', 777777);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Beringer', 'Cabernet Sauvignon', '2015' , 1);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Bogle', 'Blend', '2016' , 2);

INSERT INTO wine (label, varietal, vintage, producer_id)
 VALUES ('Silver Creek', 'Merlot', '2014' , 3);

INSERT INTO wine (label, varietal, vintage, producer_id)
 VALUES ('Chateau Ste. Michelle', 'Malbec', '2015' , 4);

INSERT INTO wine (label, varietal, vintage, producer_id)
 VALUES ('Columbia Crest', 'Cabernet Sauvignon', '2017' , 5);

INSERT INTO wine (label, varietal, vintage, producer_id)
 VALUES ('Foxglove', 'Chardonnay', '2018' , 6);

INSERT INTO wine (label, varietal, vintage, producer_id)
 VALUES ('Hess Select', 'Pinot Noir', '2016' , 7);

INSERT INTO wine (label, varietal, vintage, producer_id)
 VALUES ('J. Lohr', 'Zinfandel', '2014' , 8);

INSERT INTO wine (label, varietal, vintage, producer_id)
 VALUES ('Kendall Jackson', 'Chardonnay', '2016' , 9);

INSERT INTO wine (label, varietal, vintage, producer_id)
 VALUES ('Pine Ridge', 'Chenin Blanc', '2013' , 10);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Rancho Zabaco', 'Zinfandel', '2016', 1);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Ravenswood', 'Pinot Noir', '2011', 2);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Robert Mondavi', 'Fume Blanc', '2013', 3);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Rodeny Strong', 'Chardonnay', '2016', 4);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('La Crema', 'Chardonnay', '2017', 5);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Brancott', 'Sauvignon Blanc', '2017', 6);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Giesen', 'Chenin Blanc', '2019', 7);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Jim Barry', 'Malbec', '2016', 8);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Yalumba', 'Viognier', '2015', 9);

INSERT INTO wine (label, varietal, vintage, producer_id)
VALUES ('Bodgea Norton', 'Malbec', '2018', 10);