INSERT INTO grape_area (grape_id, area_id) VALUES (116, 80);
INSERT INTO grape_area (grape_id, area_id) VALUES (201, 704);

-- Opus One Winery / Opus One 2015
INSERT INTO producer (name, website) VALUES ('Opus One Winery', 'https://www.opusonewinery.com/');
INSERT INTO area_producer (area_id, producer_id) VALUES (80, 1);
INSERT INTO wine (name, vintage, size, weblink, producer_id, closure_id, shape_id, color_id, type_id) VALUES ('Opus One', 2015, 0.75, 'https://www.opusonewinery.com/wine/', 1, 1, 4, 2, 1);
INSERT INTO fermentation (days) VALUES (21);
INSERT INTO grape_component (percentage, harvest_begin, harvest_end, grape_id, wine_id, fermentation_id) VALUES (81, '2015-09-01'::date, '2015-10-8'::date, 116, 1, 1);
INSERT INTO grape_component (percentage, grape_id, wine_id) VALUES (7, 115, 1);
INSERT INTO grape_component (percentage, grape_id, wine_id) VALUES (6, 162, 1);
INSERT INTO grape_component (percentage, grape_id, wine_id) VALUES (4, 184, 1);
INSERT INTO grape_component (percentage, grape_id, wine_id) VALUES (2, 157, 1);
INSERT INTO barrel_component (percentage, aging, size, grape_component_id, barrel_id) VALUES (100, 545, 225, 1, 1);

-- Poggio Antico
INSERT INTO producer (name, phone, fax, email, description, website) VALUES ('Poggio Antico', '+39 0577-848044', '+39 0577-846563', 'mail@poggioantico.com', 'Poggio Antico, Tuscany', 'https://www.poggioantico.com/');
INSERT INTO area_producer (area_id, producer_id) VALUES (704, 2);
-- Brunello di Montalcino
INSERT INTO wine (name, vintage, size, alcohol, acid, bottle_aging, weblink, producer_id, closure_id, shape_id, color_id, type_id) VALUES ('Brunello di Montalcino', 2013, 0.75, 14.0, 5.6, 240, 'https://www.poggioantico.com/product/brunello-di-montalcino/', 2, 1, 1, 2, 1);
INSERT INTO maceration (days) VALUES (8);
INSERT INTO fermentation (days, temperature) VALUES (20, 26);
INSERT INTO grape_component (percentage, harvest_begin, harvest_end, grape_id, wine_id, maceration_id, fermentation_id) VALUES (100, '2013-09-26'::date, '2013-10-21'::date, 201, 2, 1, 1);
INSERT INTO barrel_component (percentage, aging, size, grape_component_id, barrel_id) VALUES (100, 1095, 500, 6, 5);
-- Madre
INSERT INTO wine (name, vintage, size, alcohol, acid, bottle_aging, weblink, producer_id, closure_id, shape_id, color_id, type_id) VALUES ('Madre', 2016, 0.75, 14.5, 5.7, 120, 'https://www.poggioantico.com/product/madre/', 2, 1, 1, 2, 1);
INSERT INTO fermentation (days, temperature) VALUES (10, 30);
INSERT INTO fermentation (days, temperature) VALUES (9, 28);
INSERT INTO grape_component (percentage, harvest_begin, harvest_end, grape_id, wine_id, fermentation_id) VALUES (50, '2016-09-22'::date, '2016-10-07'::date, 201, 3, 2);
INSERT INTO grape_component (percentage, harvest_begin, harvest_end, grape_id, wine_id, fermentation_id) VALUES (50, '2016-09-22'::date, '2016-10-07'::date, 116, 3, 3);
INSERT INTO barrel_component (percentage, aging, size, grape_component_id, barrel_id) VALUES (100, 545, 500, 7, 1);
INSERT INTO barrel_component (percentage, aging, size, grape_component_id, barrel_id) VALUES (100, 545, 500, 8, 1);

-- Bottles for users
INSERT INTO bottle (number, show, user_id, wine_id) VALUES (1, false, 2, 1);
INSERT INTO bottle (number, show, user_id, wine_id) VALUES (6, true, 3, 2);
INSERT INTO bottle (number, show, user_id, wine_id) VALUES (12, true, 3, 3);

-- Reviews for users
INSERT INTO review (stars, comment, date, user_id, wine_id) VALUES (4.0, 'Very good', now()::date, 2, 1);
INSERT INTO review (stars, comment, date, user_id, wine_id) VALUES (5.0, 'Excellent', now()::date, 3, 2);
INSERT INTO review (stars, comment, date, user_id, wine_id) VALUES (4.0, 'Very good', now()::date, 3, 3);

-- Wishlist for users
INSERT INTO wishlist (date, user_id, wine_id) VALUES (now()::date, 2, 2);
INSERT INTO wishlist (date, user_id, wine_id) VALUES (now()::date, 3, 1);

