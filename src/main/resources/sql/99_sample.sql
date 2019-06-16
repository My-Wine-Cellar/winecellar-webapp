INSERT INTO grape_area (grape_id, area_id) VALUES (116, 80);
INSERT INTO grape_area (grape_id, area_id) VALUES (201, 704);

-- Opus One Winery / Opus One 2015
INSERT INTO producer (name, website) VALUES ('Opus One Winery', 'https://www.opusonewinery.com/');
INSERT INTO area_producer (area_id, producer_id) VALUES (80, 1);
INSERT INTO wine (name, vintage, size, producer_id) VALUES ('Opus One', 2015, 0.75, 1);
INSERT INTO maceration (days) VALUES (21);
INSERT INTO grape_component (percentage, grape_id, wine_id, maceration_id) VALUES (81, 116, 1, 1);
INSERT INTO grape_component (percentage, grape_id, wine_id) VALUES (7, 115, 1);
INSERT INTO grape_component (percentage, grape_id, wine_id) VALUES (6, 162, 1);
INSERT INTO grape_component (percentage, grape_id, wine_id) VALUES (4, 184, 1);
INSERT INTO grape_component (percentage, grape_id, wine_id) VALUES (2, 157, 1);
INSERT INTO barrel_component (percentage, aging, grape_component_id, barrel_id) VALUES (100, 548, 1, 1);

-- Poggio Antico
INSERT INTO producer (name, website) VALUES ('Poggio Antico', 'https://www.poggioantico.com/');
INSERT INTO area_producer (area_id, producer_id) VALUES (704, 2);
-- Brunello di Montalcino
INSERT INTO wine (name, vintage, size, alcohol, acid, bottle_aging, producer_id) VALUES ('Brunello di Montalcino', 2013, 0.75, 14.0, 5.6, 240, 2);
INSERT INTO maceration (days) VALUES (8);
INSERT INTO fermentation (days, temperature) VALUES (20, 26);
INSERT INTO grape_component (percentage, harvest_begin, harvest_end, grape_id, wine_id, maceration_id, fermentation_id) VALUES (100, '2013-09-26'::date, '2013-10-21'::date, 201, 2, 2, 1);
INSERT INTO barrel_component (percentage, aging, grape_component_id, barrel_id) VALUES (100, 1095, 6, 5);
-- Madre
INSERT INTO wine (name, vintage, size, alcohol, acid, bottle_aging, producer_id) VALUES ('Madre', 2016, 0.75, 14.5, 5.7, 120, 2);
INSERT INTO fermentation (days, temperature) VALUES (10, 30);
INSERT INTO fermentation (days, temperature) VALUES (9, 28);
INSERT INTO grape_component (percentage, harvest_begin, harvest_end, grape_id, wine_id, fermentation_id) VALUES (50, '2016-09-22'::date, '2016-10-07'::date, 201, 3, 2);
INSERT INTO grape_component (percentage, harvest_begin, harvest_end, grape_id, wine_id, fermentation_id) VALUES (50, '2016-09-22'::date, '2016-10-07'::date, 116, 3, 3);
INSERT INTO barrel_component (percentage, aging, size, grape_component_id, barrel_id) VALUES (100, 540, 500, 7, 1);
INSERT INTO barrel_component (percentage, aging, size, grape_component_id, barrel_id) VALUES (100, 540, 500, 8, 1);

-- Bottles for users
INSERT INTO bottle (number, show, user_id, wine_id) VALUES (1, false, 888888, 1);
INSERT INTO bottle (number, show, user_id, wine_id) VALUES (6, true, 777777, 2);
INSERT INTO bottle (number, show, user_id, wine_id) VALUES (12, true, 777777, 3);
