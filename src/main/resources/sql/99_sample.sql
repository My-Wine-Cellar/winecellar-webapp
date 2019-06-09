INSERT INTO grape_area (grape_id, area_id) VALUES (116, 80);

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

INSERT INTO bottle (number, user_id, wine_id) VALUES (1, 888888, 1);
INSERT INTO bottle (number, user_id, wine_id) VALUES (1, 777777, 1);
