-- USA/Region
INSERT INTO region (name, description, weblink) VALUES ('Alabama', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Alaska', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Arizona', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Arkansas', '', '');
INSERT INTO region (name, description, weblink) VALUES ('California', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Colorado', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Connecticut', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Delaware', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Florida', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Georgia', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Hawaii', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Idaho', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Illinois', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Indiana', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Iowa', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Kansas', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Kentucky', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Louisiana', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Maine', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Maryland', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Massachusetts', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Michigan', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Minnesota', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Mississippi', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Missouri', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Montana', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Nebraska', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Nevada', '', '');
INSERT INTO region (name, description, weblink) VALUES ('New Hampshire', '', '');
INSERT INTO region (name, description, weblink) VALUES ('New Jersey', '', '');
INSERT INTO region (name, description, weblink) VALUES ('New Mexico', '', '');
INSERT INTO region (name, description, weblink) VALUES ('New York', '', '');
INSERT INTO region (name, description, weblink) VALUES ('North Carolina', '', '');
INSERT INTO region (name, description, weblink) VALUES ('North Dakota', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Ohio', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Oklahoma', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Oregon', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Pennsylvania', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Rhode Island', '', '');
INSERT INTO region (name, description, weblink) VALUES ('South Carolina', '', '');
INSERT INTO region (name, description, weblink) VALUES ('South Dakota', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Tennessee', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Texas', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Utah', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Vermont', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Virginia', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Washington', '', '');
INSERT INTO region (name, description, weblink) VALUES ('West Virginia', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Wisconsin', '', '');
INSERT INTO region (name, description, weblink) VALUES ('Wyoming', '', '');

INSERT INTO country_regions (SELECT 185, i FROM generate_series(1, 50) as i);

-- USA/Area
INSERT INTO area (name, description, weblink) VALUES ('Alabama', '', '');
INSERT INTO region_area (SELECT 1, i FROM generate_series(1, 1) as i);

INSERT INTO area (name, description, weblink) VALUES ('Alaska', '', '');
INSERT INTO region_area (SELECT 2, i FROM generate_series(2, 2) as i);

INSERT INTO area (name, description, weblink) VALUES ('Arizona', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Sonoita AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Willcox AVA', '', '');
INSERT INTO region_area (SELECT 3, i FROM generate_series(3, 5) as i);

INSERT INTO area (name, description, weblink) VALUES ('Arkansas', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Altus AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Arkansas Mountain AVA', '', '');
-- (Shared with Missouri and Oklahoma)
INSERT INTO area (name, description, weblink) VALUES ('Ozark Mountain AVA', '', '');
INSERT INTO region_area (SELECT 4, i FROM generate_series(6, 9) as i);

INSERT INTO area (name, description, weblink) VALUES ('California', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Alexander Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Alta Mesa AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Anderson Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Antelope Valley of the California High Desert AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Arroyo Grande Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Arroyo Seco AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Atlas Peak AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Ballard Canyon', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Ben Lomond Mountain AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Benmore Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Bennett Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Big Valley Lake County', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Borden Ranch AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('California Shenandoah Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Calistoga AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Capay Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Carmel Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Central Coast AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Chalk Hill AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Chalone AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Chiles Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Cienega Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Clarksburg AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Clear Lake AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Clements Hills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Cole Ranch AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Coombsville AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Cosumnes River AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Covelo AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Cucamonga Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Diablo Grande AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Diamond Mountain District AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Dos Rios AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Dry Creek Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Dunnigan Hills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Edna Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('El Dorado AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Fair Play AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Fiddletown AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Fort Ross-Seaview AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Fountaingrove District AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Green Valley of Russian River Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Guenoc Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Hames Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Happy Canyon of Santa Barbara AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('High Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Howell Mountain AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Jahant AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Kelsey Bench-Lake County', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Knights Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Lamorinda AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Leona Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Lime Kiln Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Livermore Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Lodi AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Los Carneros AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Madera AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Malibu Coast AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Malibu-Newton Canyon AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Manton Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('McDowell Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Mendocino AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Mendocino Ridge AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Merritt Island AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Mokelumne River AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Monterey AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Moon Mountain District Sonoma County', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Mt. Harlan AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Mt. Veeder AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Napa Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('North Coast AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('North Yuba AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Northern Sonoma AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Oak Knoll District of Napa Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Oakville AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Pacheco Pass AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Paicines AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Paso Robles AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Petaluma Gap AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Pine Mountain-Cloverdale AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Potter Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Ramona Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Red Hills Lake County AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Redwood Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('River Junction AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Rockpile AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Russian River Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Rutherford AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Saddle Rock-Malibu AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Salado Creek AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('San Antonio Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('San Benito AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('San Bernabe AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('San Francisco Bay AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('San Lucas AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('San Pasqual Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('San Ysidro District AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Santa Clara Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Santa Cruz Mountains AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Santa Lucia Highlands AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Santa Maria Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Santa Ynez Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Seiad Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Sierra Foothills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Sierra Pelona Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Sloughhouse AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Solano County Green Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Sonoma Coast AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Sonoma Mountain AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Sonoma Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('South Coast AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Spring Mountain District AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('St. Helena AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Sta. Rita Hills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Stags Leap District AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Suisun Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Temecula Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Tracy Hills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Trinity Lakes AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Wild Horse Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Willow Creek AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('York Mountain AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Yorkville Highlands AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Yountville AVA', '', '');
INSERT INTO region_area (SELECT 5, i FROM generate_series(10, 134) as i);

INSERT INTO area (name, description, weblink) VALUES ('Colorado', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Grand Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('West Elks AVA', '', '');
INSERT INTO region_area (SELECT 6, i FROM generate_series(135, 137) as i);

INSERT INTO area (name, description, weblink) VALUES ('Connecticut', '', '');
-- (Shared with Massachusetts and Rhode Island)
INSERT INTO area (name, description, weblink) VALUES ('Southeastern New England AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Western Connecticut Highlands AVA', '', '');
INSERT INTO region_area (SELECT 7, i FROM generate_series(138, 140) as i);

INSERT INTO area (name, description, weblink) VALUES ('Delaware', '', '');
INSERT INTO region_area (SELECT 8, i FROM generate_series(141, 141) as i);

INSERT INTO area (name, description, weblink) VALUES ('Florida', '', '');
INSERT INTO region_area (SELECT 9, i FROM generate_series(142, 142) as i);

INSERT INTO area (name, description, weblink) VALUES ('Georgia', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Dahlonega Plateau AVA', '', '');
-- (Shared with North Carolina)
INSERT INTO area (name, description, weblink) VALUES ('Upper Hiwassee Highlands AVA', '', '');
INSERT INTO region_area (SELECT 10, i FROM generate_series(143, 145) as i);

INSERT INTO area (name, description, weblink) VALUES ('Hawaii', '', '');
INSERT INTO region_area (SELECT 11, i FROM generate_series(146, 146) as i);

INSERT INTO area (name, description, weblink) VALUES ('Idaho', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Eagle Foothills AVA', '', '');
-- (Shared with Washington)
INSERT INTO area (name, description, weblink) VALUES ('Lewis-Clark Valley AVA', '', '');
-- (Shared with Oregon)
INSERT INTO area (name, description, weblink) VALUES ('Snake River Valley AVA', '', '');
INSERT INTO region_area (SELECT 12, i FROM generate_series(147, 150) as i);

INSERT INTO area (name, description, weblink) VALUES ('Illinois', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Shawnee Hills AVA', '', '');
-- (Shared with Iowa, Minnesota, and Wisconsin)
INSERT INTO area (name, description, weblink) VALUES ('Upper Mississippi River Valley AVA', '', '');
INSERT INTO region_area (SELECT 13, i FROM generate_series(151, 153) as i);

INSERT INTO area (name, description, weblink) VALUES ('Indiana', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Indiana Uplands', '', '');
-- (Shared with Kentucky, Ohio, and West Virginia)
INSERT INTO area (name, description, weblink) VALUES ('Ohio River Valley AVA', '', '');
INSERT INTO region_area (SELECT 14, i FROM generate_series(154, 156) as i);

INSERT INTO area (name, description, weblink) VALUES ('Iowa', '', '');
-- (Shared with Illinois, Minnesota, and Wisconsin)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');
INSERT INTO region_area (SELECT 15, i FROM generate_series(157, 157) as i);
INSERT INTO region_area (region_id, area_id) VALUES (15, 153);

INSERT INTO area (name, description, weblink) VALUES ('Kansas', '', '');
INSERT INTO region_area (SELECT 16, i FROM generate_series(158, 158) as i);

INSERT INTO area (name, description, weblink) VALUES ('Kentucky', '', '');
-- (Shared with Indiana, Ohio, and West Virginia)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');
INSERT INTO region_area (SELECT 17, i FROM generate_series(159, 159) as i);
INSERT INTO region_area (region_id, area_id) VALUES (17, 156);

INSERT INTO area (name, description, weblink) VALUES ('Louisiana', '', '');
-- (Shared with Mississippi and Tennessee)
INSERT INTO area (name, description, weblink) VALUES ('Mississippi Delta AVA', '', '');
INSERT INTO region_area (SELECT 18, i FROM generate_series(160, 161) as i);

INSERT INTO area (name, description, weblink) VALUES ('Maine', '', '');
INSERT INTO region_area (SELECT 19, i FROM generate_series(162, 162) as i);

INSERT INTO area (name, description, weblink) VALUES ('Maryland', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Catoctin AVA', '', '');
-- (Shared with Pennsylvania)
INSERT INTO area (name, description, weblink) VALUES ('Cumberland Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Linganore AVA', '', '');
INSERT INTO region_area (SELECT 20, i FROM generate_series(163, 166) as i);

INSERT INTO area (name, description, weblink) VALUES ('Massachusetts', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Marthas Vineyard AVA', '', '');
-- (Shared with Connecticut and Rhode Island)
-- INSERT INTO area (name, description) VALUES ('Southeastern New England AVA', '');
INSERT INTO region_area (SELECT 21, i FROM generate_series(167, 168) as i);
INSERT INTO region_area (region_id, area_id) VALUES (21, 139);

INSERT INTO area (name, description, weblink) VALUES ('Michigan', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Fennville AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Lake Michigan Shore AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Leelanau Peninsula AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Old Mission Peninsula AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Tip of the Mitt AVA', '', '');
INSERT INTO region_area (SELECT 22, i FROM generate_series(169, 174) as i);

INSERT INTO area (name, description, weblink) VALUES ('Minnesota', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Alexandria Lakes AVA', '', '');
-- (Shared with Illinois, Iowa, and Wisconsin)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');
INSERT INTO region_area (SELECT 23, i FROM generate_series(175, 176) as i);
INSERT INTO region_area (region_id, area_id) VALUES (23, 153);

INSERT INTO area (name, description, weblink) VALUES ('Mississippi', '', '');
-- (Shared with Louisiana and Tennessee)
-- INSERT INTO area (name, description) VALUES ('Mississippi Delta AVA', '');
INSERT INTO region_area (SELECT 24, i FROM generate_series(177, 177) as i);
INSERT INTO region_area (region_id, area_id) VALUES (24, 161);

INSERT INTO area (name, description, weblink) VALUES ('Missouri', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Augusta AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Hermann AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Ozark Highlands AVA', '', '');
-- (Shared with Arkansas and Oklahoma)
-- INSERT INTO area (name, description) VALUES ('Ozark Mountain AVA', '');
INSERT INTO region_area (SELECT 25, i FROM generate_series(178, 181) as i);
INSERT INTO region_area (region_id, area_id) VALUES (25, 9);

INSERT INTO area (name, description, weblink) VALUES ('Montana', '', '');
INSERT INTO region_area (SELECT 26, i FROM generate_series(182, 182) as i);

INSERT INTO area (name, description, weblink) VALUES ('Nebraska', '', '');
INSERT INTO region_area (SELECT 27, i FROM generate_series(183, 183) as i);

INSERT INTO area (name, description, weblink) VALUES ('Nevada', '', '');
INSERT INTO region_area (SELECT 28, i FROM generate_series(184, 184) as i);

INSERT INTO area (name, description, weblink) VALUES ('New Hampshire', '', '');
INSERT INTO region_area (SELECT 29, i FROM generate_series(185, 185) as i);

INSERT INTO area (name, description, weblink) VALUES ('New Jersey', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Cape May Peninsula AVA', '', '');
-- (Shared with Pennsylvania)
INSERT INTO area (name, description, weblink) VALUES ('Central Delaware Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Outer Coastal Plain AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Warren Hills AVA', '', '');
INSERT INTO region_area (SELECT 30, i FROM generate_series(186, 190) as i);

INSERT INTO area (name, description, weblink) VALUES ('New Mexico', '', '');
-- (Shared with Texas)
INSERT INTO area (name, description, weblink) VALUES ('Mesilla Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Middle Rio Grande Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Mimbres Valley AVA', '', '');
INSERT INTO region_area (SELECT 31, i FROM generate_series(191, 194) as i);

INSERT INTO area (name, description, weblink) VALUES ('New York', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Cayuga Lake AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Champlain Valley of New York AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Finger Lakes AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Hudson River Region AVA', '', '');
-- (Shared with Ohio and Pennsylvania)
INSERT INTO area (name, description, weblink) VALUES ('Lake Erie AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Long Island AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Niagara Escarpment AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('North Fork of Long Island AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Seneca Lake AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('The Hamptons, Long Island AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Upper Hudson AVA', '', '');
INSERT INTO region_area (SELECT 32, i FROM generate_series(195, 206) as i);

INSERT INTO area (name, description, weblink) VALUES ('North Carolina', '', '');
-- (Shared with Tennessee and Virginia)
INSERT INTO area (name, description, weblink) VALUES ('Appalachian High Country AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Haw River Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Swan Creek AVA', '', '');
-- (Shared with Georgia)
-- INSERT INTO area (name, description) VALUES ('Upper Hiwassee Highlands AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Yadkin Valley AVA', '', '');
INSERT INTO region_area (SELECT 33, i FROM generate_series(207, 211) as i);
INSERT INTO region_area (region_id, area_id) VALUES (33, 145);

INSERT INTO area (name, description, weblink) VALUES ('North Dakota', '', '');
INSERT INTO region_area (SELECT 34, i FROM generate_series(212, 212) as i);

INSERT INTO area (name, description, weblink) VALUES ('Ohio', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Grand River Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Isle St. George AVA', '', '');
-- (Shared with New York and Pennsylvania)
-- INSERT INTO area (name, description) VALUES ('Lake Erie AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Loramie Creek AVA', '', '');
-- (Shared with Indiana, Kentucky, and West Virginia)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');
INSERT INTO region_area (SELECT 35, i FROM generate_series(213, 216) as i);
INSERT INTO region_area (region_id, area_id) VALUES (35, 200);
INSERT INTO region_area (region_id, area_id) VALUES (35, 156);

INSERT INTO area (name, description, weblink) VALUES ('Oklahoma', '', '');
-- (Shared with Arkansas and Missouri)
-- INSERT INTO area (name, description) VALUES ('Ozark Mountain AVA', '');
INSERT INTO region_area (SELECT 36, i FROM generate_series(217, 217) as i);
INSERT INTO region_area (region_id, area_id) VALUES (36, 9);

INSERT INTO area (name, description, weblink) VALUES ('Oregon', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Applegate Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Chehalem Mountains AVA', '', '');
-- (Shared with Washington)
INSERT INTO area (name, description, weblink) VALUES ('Columbia Gorge AVA', '', '');
-- (Shared with Washington)
INSERT INTO area (name, description, weblink) VALUES ('Columbia Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Dundee Hills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Elkton Oregon AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Eola-Amity Hills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('McMinnville AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Red Hill Douglas County Oregon AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Ribbon Ridge AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Rogue Valley AVA', '', '');
-- (Shared with Idaho)
-- INSERT INTO area (name, description) VALUES ('Snake River Valley AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Southern Oregon AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('The Rocks District of Milton–Freewater AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Umpqua Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Van Duzer Corridor AVA', '', '');
-- (Shared with Washington)
INSERT INTO area (name, description, weblink) VALUES ('Walla Walla Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Willamette Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Yamhill-Carlton District AVA', '', '');
INSERT INTO region_area (SELECT 37, i FROM generate_series(218, 236) as i);
INSERT INTO region_area (region_id, area_id) VALUES (37, 150);

INSERT INTO area (name, description, weblink) VALUES ('Pennsylvania', '', '');
-- (Shared with New Jersey)
-- INSERT INTO area (name, description) VALUES ('Central Delaware Valley AVA', '');
-- (Shared with Maryland)
-- INSERT INTO area (name, description) VALUES ('Cumberland Valley AVA', '');
-- (Shared with New York and Ohio)
-- INSERT INTO area (name, description) VALUES ('Lake Erie AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Lancaster Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Lehigh Valley AVA', '', '');
INSERT INTO region_area (SELECT 38, i FROM generate_series(237, 239) as i);
INSERT INTO region_area (region_id, area_id) VALUES (38, 188);
INSERT INTO region_area (region_id, area_id) VALUES (38, 165);
INSERT INTO region_area (region_id, area_id) VALUES (38, 200);

INSERT INTO area (name, description, weblink) VALUES ('Rhode Island', '', '');
-- (Shared with Connecticut and Massachusetts)
-- INSERT INTO area (name, description) VALUES ('Southeastern New England AVA', '');
INSERT INTO region_area (SELECT 39, i FROM generate_series(240, 240) as i);
INSERT INTO region_area (region_id, area_id) VALUES (39, 139);

INSERT INTO area (name, description, weblink) VALUES ('South Carolina', '', '');
INSERT INTO region_area (SELECT 40, i FROM generate_series(241, 241) as i);

INSERT INTO area (name, description, weblink) VALUES ('South Dakota', '', '');
INSERT INTO region_area (SELECT 41, i FROM generate_series(242, 242) as i);

INSERT INTO area (name, description, weblink) VALUES ('Tennessee', '', '');
-- (Shared with Louisiana and Mississippi)
-- INSERT INTO area (name, description) VALUES ('Mississippi Delta AVA', '');
INSERT INTO region_area (SELECT 42, i FROM generate_series(243, 243) as i);
INSERT INTO region_area (region_id, area_id) VALUES (42, 161);

INSERT INTO area (name, description, weblink) VALUES ('Texas', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Bell Mountain AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Escondido Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Fredericksburg in the Texas Hill Country AVA', '', '');
-- (Shared with New Mexico)
-- INSERT INTO area (name, description) VALUES ('Mesilla Valley AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Texas Davis Mountains AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Texas High Plains AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Texas Hill Country AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Texoma AVA', '', '');
INSERT INTO region_area (SELECT 43, i FROM generate_series(244, 251) as i);
INSERT INTO region_area (region_id, area_id) VALUES (43, 192);

INSERT INTO area (name, description, weblink) VALUES ('Utah', '', '');
INSERT INTO region_area (SELECT 44, i FROM generate_series(252, 252) as i);

INSERT INTO area (name, description, weblink) VALUES ('Vermont', '', '');
INSERT INTO region_area (SELECT 45, i FROM generate_series(253, 253) as i);

INSERT INTO area (name, description, weblink) VALUES ('Virginia', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Middleburg AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Monticello AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('North Fork of Roanoke AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Northern Neck George Washington Birthplace AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Rocky Knob AVA', '', '');
-- (Shared with West Virginia)
INSERT INTO area (name, description, weblink) VALUES ('Shenandoah Valley AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Virginias Eastern Shore AVA', '', '');
INSERT INTO region_area (SELECT 46, i FROM generate_series(254, 261) as i);

INSERT INTO area (name, description, weblink) VALUES ('Washington', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Ancient Lakes of the Columbia Valley AVA', '', '');
-- (Shared with Oregon)
-- INSERT INTO area (name, description) VALUES ('Columbia Gorge AVA', '');
-- (Shared with Oregon)
-- INSERT INTO area (name, description) VALUES ('Columbia Valley AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Horse Heaven Hills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Lake Chelan AVA', '', '');
-- (Shared with Idaho)
-- INSERT INTO area (name, description) VALUES ('Lewis-Clark Valley AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Naches Heights AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Puget Sound AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Rattlesnake Hills AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Red Mountain AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Snipes Mountain AVA', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Wahluke Slope AVA', '', '');
-- (Shared with Oregon)
-- INSERT INTO area (name, description) VALUES ('Walla Walla Valley AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Yakima Valley AVA', '', '');
INSERT INTO region_area (SELECT 47, i FROM generate_series(262, 272) as i);
INSERT INTO region_area (region_id, area_id) VALUES (47, 221);
INSERT INTO region_area (region_id, area_id) VALUES (47, 222);
INSERT INTO region_area (region_id, area_id) VALUES (47, 149);
INSERT INTO region_area (region_id, area_id) VALUES (47, 234);

INSERT INTO area (name, description, weblink) VALUES ('West Virginia', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Kanawha River Valley AVA', '', '');
-- (Shared with Indiana, Kentucky, and Ohio)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');
-- (Shared with Virginia)
-- INSERT INTO area (name, description) VALUES ('Shenandoah Valley AVA', '');
INSERT INTO region_area (SELECT 48, i FROM generate_series(273, 274) as i);
INSERT INTO region_area (region_id, area_id) VALUES (48, 156);
INSERT INTO region_area (region_id, area_id) VALUES (48, 260);

INSERT INTO area (name, description, weblink) VALUES ('Wisconsin', '', '');
INSERT INTO area (name, description, weblink) VALUES ('Lake Wisconsin AVA', '', '');
-- (Shared with Illinois, Iowa, and Minnesota)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');
INSERT INTO area (name, description, weblink) VALUES ('Wisconsin Ledge AVA', '', '');
INSERT INTO region_area (SELECT 49, i FROM generate_series(275, 277) as i);
INSERT INTO region_area (region_id, area_id) VALUES (49, 153);

INSERT INTO area (name, description, weblink) VALUES ('Wyoming', '', '');
INSERT INTO region_area (SELECT 50, i FROM generate_series(278, 278) as i);
