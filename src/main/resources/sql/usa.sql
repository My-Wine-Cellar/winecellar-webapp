-- USA/Region
INSERT INTO region (name, description) VALUES ('Alabama', '');
INSERT INTO region (name, description) VALUES ('Alaska', '');
INSERT INTO region (name, description) VALUES ('Arizona', '');
INSERT INTO region (name, description) VALUES ('Arkansas', '');
INSERT INTO region (name, description) VALUES ('California', '');
INSERT INTO region (name, description) VALUES ('Colorado', '');
INSERT INTO region (name, description) VALUES ('Connecticut', '');
INSERT INTO region (name, description) VALUES ('Delaware', '');
INSERT INTO region (name, description) VALUES ('Florida', '');
INSERT INTO region (name, description) VALUES ('Georgia', '');
INSERT INTO region (name, description) VALUES ('Hawaii', '');
INSERT INTO region (name, description) VALUES ('Idaho', '');
INSERT INTO region (name, description) VALUES ('Illinois', '');
INSERT INTO region (name, description) VALUES ('Indiana', '');
INSERT INTO region (name, description) VALUES ('Iowa', '');
INSERT INTO region (name, description) VALUES ('Kansas', '');
INSERT INTO region (name, description) VALUES ('Kentucky', '');
INSERT INTO region (name, description) VALUES ('Louisiana', '');
INSERT INTO region (name, description) VALUES ('Maine', '');
INSERT INTO region (name, description) VALUES ('Maryland', '');
INSERT INTO region (name, description) VALUES ('Massachusetts', '');
INSERT INTO region (name, description) VALUES ('Michigan', '');
INSERT INTO region (name, description) VALUES ('Minnesota', '');
INSERT INTO region (name, description) VALUES ('Mississippi', '');
INSERT INTO region (name, description) VALUES ('Missouri', '');
INSERT INTO region (name, description) VALUES ('Montana', '');
INSERT INTO region (name, description) VALUES ('Nebraska', '');
INSERT INTO region (name, description) VALUES ('Nevada', '');
INSERT INTO region (name, description) VALUES ('New Hampshire', '');
INSERT INTO region (name, description) VALUES ('New Jersey', '');
INSERT INTO region (name, description) VALUES ('New Mexico', '');
INSERT INTO region (name, description) VALUES ('New York', '');
INSERT INTO region (name, description) VALUES ('North Carolina', '');
INSERT INTO region (name, description) VALUES ('North Dakota', '');
INSERT INTO region (name, description) VALUES ('Ohio', '');
INSERT INTO region (name, description) VALUES ('Oklahoma', '');
INSERT INTO region (name, description) VALUES ('Oregon', '');
INSERT INTO region (name, description) VALUES ('Pennsylvania', '');
INSERT INTO region (name, description) VALUES ('Rhode Island', '');
INSERT INTO region (name, description) VALUES ('South Carolina', '');
INSERT INTO region (name, description) VALUES ('South Dakota', '');
INSERT INTO region (name, description) VALUES ('Tennessee', '');
INSERT INTO region (name, description) VALUES ('Texas', '');
INSERT INTO region (name, description) VALUES ('Utah', '');
INSERT INTO region (name, description) VALUES ('Vermont', '');
INSERT INTO region (name, description) VALUES ('Virginia', '');
INSERT INTO region (name, description) VALUES ('Washington', '');
INSERT INTO region (name, description) VALUES ('West Virginia', '');
INSERT INTO region (name, description) VALUES ('Wisconsin', '');
INSERT INTO region (name, description) VALUES ('Wyoming', '');

INSERT INTO country_regions (SELECT 185, i FROM generate_series(1, 50) as i);

-- USA/Area
INSERT INTO area (name, description) VALUES ('Alabama', '');
INSERT INTO region_area (SELECT 1, i FROM generate_series(1, 1) as i);

INSERT INTO area (name, description) VALUES ('Alaska', '');
INSERT INTO region_area (SELECT 2, i FROM generate_series(2, 2) as i);

INSERT INTO area (name, description) VALUES ('Arizona', '');
INSERT INTO area (name, description) VALUES ('Sonoita AVA', '');
INSERT INTO area (name, description) VALUES ('Willcox AVA', '');
INSERT INTO region_area (SELECT 3, i FROM generate_series(3, 5) as i);

INSERT INTO area (name, description) VALUES ('Arkansas', '');
INSERT INTO area (name, description) VALUES ('Altus AVA', '');
INSERT INTO area (name, description) VALUES ('Arkansas Mountain AVA', '');
-- (Shared with Missouri and Oklahoma)
INSERT INTO area (name, description) VALUES ('Ozark Mountain AVA', '');
INSERT INTO region_area (SELECT 4, i FROM generate_series(6, 9) as i);

INSERT INTO area (name, description) VALUES ('California', '');
INSERT INTO area (name, description) VALUES ('Alexander Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Alta Mesa AVA', '');
INSERT INTO area (name, description) VALUES ('Anderson Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Antelope Valley of the California High Desert AVA', '');
INSERT INTO area (name, description) VALUES ('Arroyo Grande Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Arroyo Seco AVA', '');
INSERT INTO area (name, description) VALUES ('Atlas Peak AVA', '');
INSERT INTO area (name, description) VALUES ('Ballard Canyon', '');
INSERT INTO area (name, description) VALUES ('Ben Lomond Mountain AVA', '');
INSERT INTO area (name, description) VALUES ('Benmore Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Bennett Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Big Valley Lake County', '');
INSERT INTO area (name, description) VALUES ('Borden Ranch AVA', '');
INSERT INTO area (name, description) VALUES ('California Shenandoah Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Calistoga AVA', '');
INSERT INTO area (name, description) VALUES ('Capay Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Carmel Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Central Coast AVA', '');
INSERT INTO area (name, description) VALUES ('Chalk Hill AVA', '');
INSERT INTO area (name, description) VALUES ('Chalone AVA', '');
INSERT INTO area (name, description) VALUES ('Chiles Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Cienega Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Clarksburg AVA', '');
INSERT INTO area (name, description) VALUES ('Clear Lake AVA', '');
INSERT INTO area (name, description) VALUES ('Clements Hills AVA', '');
INSERT INTO area (name, description) VALUES ('Cole Ranch AVA', '');
INSERT INTO area (name, description) VALUES ('Coombsville AVA', '');
INSERT INTO area (name, description) VALUES ('Cosumnes River AVA', '');
INSERT INTO area (name, description) VALUES ('Covelo AVA', '');
INSERT INTO area (name, description) VALUES ('Cucamonga Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Diablo Grande AVA', '');
INSERT INTO area (name, description) VALUES ('Diamond Mountain District AVA', '');
INSERT INTO area (name, description) VALUES ('Dos Rios AVA', '');
INSERT INTO area (name, description) VALUES ('Dry Creek Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Dunnigan Hills AVA', '');
INSERT INTO area (name, description) VALUES ('Edna Valley AVA', '');
INSERT INTO area (name, description) VALUES ('El Dorado AVA', '');
INSERT INTO area (name, description) VALUES ('Fair Play AVA', '');
INSERT INTO area (name, description) VALUES ('Fiddletown AVA', '');
INSERT INTO area (name, description) VALUES ('Fort Ross-Seaview AVA', '');
INSERT INTO area (name, description) VALUES ('Fountaingrove District AVA', '');
INSERT INTO area (name, description) VALUES ('Green Valley of Russian River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Guenoc Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Hames Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Happy Canyon of Santa Barbara AVA', '');
INSERT INTO area (name, description) VALUES ('High Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Howell Mountain AVA', '');
INSERT INTO area (name, description) VALUES ('Jahant AVA', '');
INSERT INTO area (name, description) VALUES ('Kelsey Bench-Lake County', '');
INSERT INTO area (name, description) VALUES ('Knights Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Lamorinda AVA', '');
INSERT INTO area (name, description) VALUES ('Leona Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Lime Kiln Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Livermore Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Lodi AVA', '');
INSERT INTO area (name, description) VALUES ('Los Carneros AVA', '');
INSERT INTO area (name, description) VALUES ('Madera AVA', '');
INSERT INTO area (name, description) VALUES ('Malibu Coast AVA', '');
INSERT INTO area (name, description) VALUES ('Malibu-Newton Canyon AVA', '');
INSERT INTO area (name, description) VALUES ('Manton Valley AVA', '');
INSERT INTO area (name, description) VALUES ('McDowell Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Mendocino AVA', '');
INSERT INTO area (name, description) VALUES ('Mendocino Ridge AVA', '');
INSERT INTO area (name, description) VALUES ('Merritt Island AVA', '');
INSERT INTO area (name, description) VALUES ('Mokelumne River AVA', '');
INSERT INTO area (name, description) VALUES ('Monterey AVA', '');
INSERT INTO area (name, description) VALUES ('Moon Mountain District Sonoma County', '');
INSERT INTO area (name, description) VALUES ('Mt. Harlan AVA', '');
INSERT INTO area (name, description) VALUES ('Mt. Veeder AVA', '');
INSERT INTO area (name, description) VALUES ('Napa Valley AVA', '');
INSERT INTO area (name, description) VALUES ('North Coast AVA', '');
INSERT INTO area (name, description) VALUES ('North Yuba AVA', '');
INSERT INTO area (name, description) VALUES ('Northern Sonoma AVA', '');
INSERT INTO area (name, description) VALUES ('Oak Knoll District of Napa Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Oakville AVA', '');
INSERT INTO area (name, description) VALUES ('Pacheco Pass AVA', '');
INSERT INTO area (name, description) VALUES ('Paicines AVA', '');
INSERT INTO area (name, description) VALUES ('Paso Robles AVA', '');
INSERT INTO area (name, description) VALUES ('Petaluma Gap AVA', '');
INSERT INTO area (name, description) VALUES ('Pine Mountain-Cloverdale AVA', '');
INSERT INTO area (name, description) VALUES ('Potter Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Ramona Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Red Hills Lake County AVA', '');
INSERT INTO area (name, description) VALUES ('Redwood Valley AVA', '');
INSERT INTO area (name, description) VALUES ('River Junction AVA', '');
INSERT INTO area (name, description) VALUES ('Rockpile AVA', '');
INSERT INTO area (name, description) VALUES ('Russian River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Rutherford AVA', '');
INSERT INTO area (name, description) VALUES ('Saddle Rock-Malibu AVA', '');
INSERT INTO area (name, description) VALUES ('Salado Creek AVA', '');
INSERT INTO area (name, description) VALUES ('San Antonio Valley AVA', '');
INSERT INTO area (name, description) VALUES ('San Benito AVA', '');
INSERT INTO area (name, description) VALUES ('San Bernabe AVA', '');
INSERT INTO area (name, description) VALUES ('San Francisco Bay AVA', '');
INSERT INTO area (name, description) VALUES ('San Lucas AVA', '');
INSERT INTO area (name, description) VALUES ('San Pasqual Valley AVA', '');
INSERT INTO area (name, description) VALUES ('San Ysidro District AVA', '');
INSERT INTO area (name, description) VALUES ('Santa Clara Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Santa Cruz Mountains AVA', '');
INSERT INTO area (name, description) VALUES ('Santa Lucia Highlands AVA', '');
INSERT INTO area (name, description) VALUES ('Santa Maria Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Santa Ynez Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Seiad Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Sierra Foothills AVA', '');
INSERT INTO area (name, description) VALUES ('Sierra Pelona Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Sloughhouse AVA', '');
INSERT INTO area (name, description) VALUES ('Solano County Green Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Sonoma Coast AVA', '');
INSERT INTO area (name, description) VALUES ('Sonoma Mountain AVA', '');
INSERT INTO area (name, description) VALUES ('Sonoma Valley AVA', '');
INSERT INTO area (name, description) VALUES ('South Coast AVA', '');
INSERT INTO area (name, description) VALUES ('Spring Mountain District AVA', '');
INSERT INTO area (name, description) VALUES ('St. Helena AVA', '');
INSERT INTO area (name, description) VALUES ('Sta. Rita Hills AVA', '');
INSERT INTO area (name, description) VALUES ('Stags Leap District AVA', '');
INSERT INTO area (name, description) VALUES ('Suisun Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Temecula Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Tracy Hills AVA', '');
INSERT INTO area (name, description) VALUES ('Trinity Lakes AVA', '');
INSERT INTO area (name, description) VALUES ('Wild Horse Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Willow Creek AVA', '');
INSERT INTO area (name, description) VALUES ('York Mountain AVA', '');
INSERT INTO area (name, description) VALUES ('Yorkville Highlands AVA', '');
INSERT INTO area (name, description) VALUES ('Yountville AVA', '');
INSERT INTO region_area (SELECT 5, i FROM generate_series(10, 134) as i);

INSERT INTO area (name, description) VALUES ('Colorado', '');
INSERT INTO area (name, description) VALUES ('Grand Valley AVA', '');
INSERT INTO area (name, description) VALUES ('West Elks AVA', '');
INSERT INTO region_area (SELECT 6, i FROM generate_series(135, 137) as i);

INSERT INTO area (name, description) VALUES ('Connecticut', '');
-- (Shared with Massachusetts and Rhode Island)
INSERT INTO area (name, description) VALUES ('Southeastern New England AVA', '');
INSERT INTO area (name, description) VALUES ('Western Connecticut Highlands AVA', '');
INSERT INTO region_area (SELECT 7, i FROM generate_series(138, 140) as i);

INSERT INTO area (name, description) VALUES ('Delaware', '');
INSERT INTO region_area (SELECT 8, i FROM generate_series(141, 141) as i);

INSERT INTO area (name, description) VALUES ('Florida', '');
INSERT INTO region_area (SELECT 9, i FROM generate_series(142, 142) as i);

INSERT INTO area (name, description) VALUES ('Georgia', '');
INSERT INTO area (name, description) VALUES ('Dahlonega Plateau AVA', '');
-- (Shared with North Carolina)
INSERT INTO area (name, description) VALUES ('Upper Hiwassee Highlands AVA', '');
INSERT INTO region_area (SELECT 10, i FROM generate_series(143, 145) as i);

INSERT INTO area (name, description) VALUES ('Hawaii', '');
INSERT INTO region_area (SELECT 11, i FROM generate_series(146, 146) as i);

INSERT INTO area (name, description) VALUES ('Idaho', '');
INSERT INTO area (name, description) VALUES ('Eagle Foothills AVA', '');
-- (Shared with Washington)
INSERT INTO area (name, description) VALUES ('Lewis-Clark Valley AVA', '');
-- (Shared with Oregon)
INSERT INTO area (name, description) VALUES ('Snake River Valley AVA', '');
INSERT INTO region_area (SELECT 12, i FROM generate_series(147, 150) as i);

INSERT INTO area (name, description) VALUES ('Illinois', '');
INSERT INTO area (name, description) VALUES ('Shawnee Hills AVA', '');
-- (Shared with Iowa, Minnesota, and Wisconsin)
INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');
INSERT INTO region_area (SELECT 13, i FROM generate_series(151, 153) as i);

INSERT INTO area (name, description) VALUES ('Indiana', '');
INSERT INTO area (name, description) VALUES ('Indiana Uplands', '');
-- (Shared with Kentucky, Ohio, and West Virginia)
INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');
INSERT INTO region_area (SELECT 14, i FROM generate_series(154, 156) as i);

INSERT INTO area (name, description) VALUES ('Iowa', '');
-- (Shared with Illinois, Minnesota, and Wisconsin)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');
INSERT INTO region_area (SELECT 15, i FROM generate_series(157, 157) as i);
INSERT INTO region_area (region_id, area_id) VALUES (15, 153);

INSERT INTO area (name, description) VALUES ('Kansas', '');
INSERT INTO region_area (SELECT 16, i FROM generate_series(158, 158) as i);

INSERT INTO area (name, description) VALUES ('Kentucky', '');
-- (Shared with Indiana, Ohio, and West Virginia)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');
INSERT INTO region_area (SELECT 17, i FROM generate_series(159, 159) as i);
INSERT INTO region_area (region_id, area_id) VALUES (17, 156);

INSERT INTO area (name, description) VALUES ('Louisiana', '');
-- (Shared with Mississippi and Tennessee)
INSERT INTO area (name, description) VALUES ('Mississippi Delta AVA', '');
INSERT INTO region_area (SELECT 18, i FROM generate_series(160, 161) as i);

INSERT INTO area (name, description) VALUES ('Maine', '');
INSERT INTO region_area (SELECT 19, i FROM generate_series(162, 162) as i);

INSERT INTO area (name, description) VALUES ('Maryland', '');
INSERT INTO area (name, description) VALUES ('Catoctin AVA', '');
-- (Shared with Pennsylvania)
INSERT INTO area (name, description) VALUES ('Cumberland Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Linganore AVA', '');
INSERT INTO region_area (SELECT 20, i FROM generate_series(163, 166) as i);

INSERT INTO area (name, description) VALUES ('Massachusetts', '');
INSERT INTO area (name, description) VALUES ('Marthas Vineyard AVA', '');
-- (Shared with Connecticut and Rhode Island)
-- INSERT INTO area (name, description) VALUES ('Southeastern New England AVA', '');
INSERT INTO region_area (SELECT 21, i FROM generate_series(167, 168) as i);
INSERT INTO region_area (region_id, area_id) VALUES (21, 139);

INSERT INTO area (name, description) VALUES ('Michigan', '');
INSERT INTO area (name, description) VALUES ('Fennville AVA', '');
INSERT INTO area (name, description) VALUES ('Lake Michigan Shore AVA', '');
INSERT INTO area (name, description) VALUES ('Leelanau Peninsula AVA', '');
INSERT INTO area (name, description) VALUES ('Old Mission Peninsula AVA', '');
INSERT INTO area (name, description) VALUES ('Tip of the Mitt AVA', '');
INSERT INTO region_area (SELECT 22, i FROM generate_series(169, 174) as i);

INSERT INTO area (name, description) VALUES ('Minnesota', '');
INSERT INTO area (name, description) VALUES ('Alexandria Lakes AVA', '');
-- (Shared with Illinois, Iowa, and Wisconsin)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');
INSERT INTO region_area (SELECT 23, i FROM generate_series(175, 176) as i);
INSERT INTO region_area (region_id, area_id) VALUES (23, 153);

INSERT INTO area (name, description) VALUES ('Mississippi', '');
-- (Shared with Louisiana and Tennessee)
-- INSERT INTO area (name, description) VALUES ('Mississippi Delta AVA', '');
INSERT INTO region_area (SELECT 24, i FROM generate_series(177, 177) as i);
INSERT INTO region_area (region_id, area_id) VALUES (24, 161);

INSERT INTO area (name, description) VALUES ('Missouri', '');
INSERT INTO area (name, description) VALUES ('Augusta AVA', '');
INSERT INTO area (name, description) VALUES ('Hermann AVA', '');
INSERT INTO area (name, description) VALUES ('Ozark Highlands AVA', '');
-- (Shared with Arkansas and Oklahoma)
-- INSERT INTO area (name, description) VALUES ('Ozark Mountain AVA', '');
INSERT INTO region_area (SELECT 25, i FROM generate_series(178, 181) as i);
INSERT INTO region_area (region_id, area_id) VALUES (25, 9);

INSERT INTO area (name, description) VALUES ('Montana', '');
INSERT INTO region_area (SELECT 26, i FROM generate_series(182, 182) as i);

INSERT INTO area (name, description) VALUES ('Nebraska', '');
INSERT INTO region_area (SELECT 27, i FROM generate_series(183, 183) as i);

INSERT INTO area (name, description) VALUES ('Nevada', '');
INSERT INTO region_area (SELECT 28, i FROM generate_series(184, 184) as i);

INSERT INTO area (name, description) VALUES ('New Hampshire', '');
INSERT INTO region_area (SELECT 29, i FROM generate_series(185, 185) as i);

INSERT INTO area (name, description) VALUES ('New Jersey', '');
INSERT INTO area (name, description) VALUES ('Cape May Peninsula AVA', '');
-- (Shared with Pennsylvania)
INSERT INTO area (name, description) VALUES ('Central Delaware Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Outer Coastal Plain AVA', '');
INSERT INTO area (name, description) VALUES ('Warren Hills AVA', '');
INSERT INTO region_area (SELECT 30, i FROM generate_series(186, 190) as i);

INSERT INTO area (name, description) VALUES ('New Mexico', '');
-- (Shared with Texas)
INSERT INTO area (name, description) VALUES ('Mesilla Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Middle Rio Grande Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Mimbres Valley AVA', '');
INSERT INTO region_area (SELECT 31, i FROM generate_series(191, 194) as i);

INSERT INTO area (name, description) VALUES ('New York', '');
INSERT INTO area (name, description) VALUES ('Cayuga Lake AVA', '');
INSERT INTO area (name, description) VALUES ('Champlain Valley of New York AVA', '');
INSERT INTO area (name, description) VALUES ('Finger Lakes AVA', '');
INSERT INTO area (name, description) VALUES ('Hudson River Region AVA', '');
-- (Shared with Ohio and Pennsylvania)
INSERT INTO area (name, description) VALUES ('Lake Erie AVA', '');
INSERT INTO area (name, description) VALUES ('Long Island AVA', '');
INSERT INTO area (name, description) VALUES ('Niagara Escarpment AVA', '');
INSERT INTO area (name, description) VALUES ('North Fork of Long Island AVA', '');
INSERT INTO area (name, description) VALUES ('Seneca Lake AVA', '');
INSERT INTO area (name, description) VALUES ('The Hamptons, Long Island AVA', '');
INSERT INTO area (name, description) VALUES ('Upper Hudson AVA', '');
INSERT INTO region_area (SELECT 32, i FROM generate_series(195, 206) as i);

INSERT INTO area (name, description) VALUES ('North Carolina', '');
-- (Shared with Tennessee and Virginia)
INSERT INTO area (name, description) VALUES ('Appalachian High Country AVA', '');
INSERT INTO area (name, description) VALUES ('Haw River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Swan Creek AVA', '');
-- (Shared with Georgia)
-- INSERT INTO area (name, description) VALUES ('Upper Hiwassee Highlands AVA', '');
INSERT INTO area (name, description) VALUES ('Yadkin Valley AVA', '');
INSERT INTO region_area (SELECT 33, i FROM generate_series(207, 211) as i);
INSERT INTO region_area (region_id, area_id) VALUES (33, 145);

INSERT INTO area (name, description) VALUES ('North Dakota', '');
INSERT INTO region_area (SELECT 34, i FROM generate_series(212, 212) as i);

INSERT INTO area (name, description) VALUES ('Ohio', '');
INSERT INTO area (name, description) VALUES ('Grand River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Isle St. George AVA', '');
-- (Shared with New York and Pennsylvania)
-- INSERT INTO area (name, description) VALUES ('Lake Erie AVA', '');
INSERT INTO area (name, description) VALUES ('Loramie Creek AVA', '');
-- (Shared with Indiana, Kentucky, and West Virginia)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');
INSERT INTO region_area (SELECT 35, i FROM generate_series(213, 216) as i);
INSERT INTO region_area (region_id, area_id) VALUES (35, 200);
INSERT INTO region_area (region_id, area_id) VALUES (35, 156);

INSERT INTO area (name, description) VALUES ('Oklahoma', '');
-- (Shared with Arkansas and Missouri)
-- INSERT INTO area (name, description) VALUES ('Ozark Mountain AVA', '');
INSERT INTO region_area (SELECT 36, i FROM generate_series(217, 217) as i);
INSERT INTO region_area (region_id, area_id) VALUES (36, 9);

INSERT INTO area (name, description) VALUES ('Oregon', '');
INSERT INTO area (name, description) VALUES ('Applegate Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Chehalem Mountains AVA', '');
-- (Shared with Washington)
INSERT INTO area (name, description) VALUES ('Columbia Gorge AVA', '');
-- (Shared with Washington)
INSERT INTO area (name, description) VALUES ('Columbia Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Dundee Hills AVA', '');
INSERT INTO area (name, description) VALUES ('Elkton Oregon AVA', '');
INSERT INTO area (name, description) VALUES ('Eola-Amity Hills AVA', '');
INSERT INTO area (name, description) VALUES ('McMinnville AVA', '');
INSERT INTO area (name, description) VALUES ('Red Hill Douglas County Oregon AVA', '');
INSERT INTO area (name, description) VALUES ('Ribbon Ridge AVA', '');
INSERT INTO area (name, description) VALUES ('Rogue Valley AVA', '');
-- (Shared with Idaho)
-- INSERT INTO area (name, description) VALUES ('Snake River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Southern Oregon AVA', '');
INSERT INTO area (name, description) VALUES ('The Rocks District of Milton–Freewater AVA', '');
INSERT INTO area (name, description) VALUES ('Umpqua Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Van Duzer Corridor AVA', '');
-- (Shared with Washington)
INSERT INTO area (name, description) VALUES ('Walla Walla Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Willamette Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Yamhill-Carlton District AVA', '');
INSERT INTO region_area (SELECT 37, i FROM generate_series(218, 236) as i);
INSERT INTO region_area (region_id, area_id) VALUES (37, 150);

INSERT INTO area (name, description) VALUES ('Pennsylvania', '');
-- (Shared with New Jersey)
-- INSERT INTO area (name, description) VALUES ('Central Delaware Valley AVA', '');
-- (Shared with Maryland)
-- INSERT INTO area (name, description) VALUES ('Cumberland Valley AVA', '');
-- (Shared with New York and Ohio)
-- INSERT INTO area (name, description) VALUES ('Lake Erie AVA', '');
INSERT INTO area (name, description) VALUES ('Lancaster Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Lehigh Valley AVA', '');
INSERT INTO region_area (SELECT 38, i FROM generate_series(237, 239) as i);
INSERT INTO region_area (region_id, area_id) VALUES (38, 188);
INSERT INTO region_area (region_id, area_id) VALUES (38, 165);
INSERT INTO region_area (region_id, area_id) VALUES (38, 200);

INSERT INTO area (name, description) VALUES ('Rhode Island', '');
-- (Shared with Connecticut and Massachusetts)
-- INSERT INTO area (name, description) VALUES ('Southeastern New England AVA', '');
INSERT INTO region_area (SELECT 39, i FROM generate_series(240, 240) as i);
INSERT INTO region_area (region_id, area_id) VALUES (39, 139);

INSERT INTO area (name, description) VALUES ('South Carolina', '');
INSERT INTO region_area (SELECT 40, i FROM generate_series(241, 241) as i);

INSERT INTO area (name, description) VALUES ('South Dakota', '');
INSERT INTO region_area (SELECT 41, i FROM generate_series(242, 242) as i);

INSERT INTO area (name, description) VALUES ('Tennessee', '');
-- (Shared with Louisiana and Mississippi)
-- INSERT INTO area (name, description) VALUES ('Mississippi Delta AVA', '');
INSERT INTO region_area (SELECT 42, i FROM generate_series(243, 243) as i);
INSERT INTO region_area (region_id, area_id) VALUES (42, 161);

INSERT INTO area (name, description) VALUES ('Texas', '');
INSERT INTO area (name, description) VALUES ('Bell Mountain AVA', '');
INSERT INTO area (name, description) VALUES ('Escondido Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Fredericksburg in the Texas Hill Country AVA', '');
-- (Shared with New Mexico)
-- INSERT INTO area (name, description) VALUES ('Mesilla Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Texas Davis Mountains AVA', '');
INSERT INTO area (name, description) VALUES ('Texas High Plains AVA', '');
INSERT INTO area (name, description) VALUES ('Texas Hill Country AVA', '');
INSERT INTO area (name, description) VALUES ('Texoma AVA', '');
INSERT INTO region_area (SELECT 43, i FROM generate_series(244, 251) as i);
INSERT INTO region_area (region_id, area_id) VALUES (43, 192);

INSERT INTO area (name, description) VALUES ('Utah', '');
INSERT INTO region_area (SELECT 44, i FROM generate_series(252, 252) as i);

INSERT INTO area (name, description) VALUES ('Vermont', '');
INSERT INTO region_area (SELECT 45, i FROM generate_series(253, 253) as i);

INSERT INTO area (name, description) VALUES ('Virginia', '');
INSERT INTO area (name, description) VALUES ('Middleburg AVA', '');
INSERT INTO area (name, description) VALUES ('Monticello AVA', '');
INSERT INTO area (name, description) VALUES ('North Fork of Roanoke AVA', '');
INSERT INTO area (name, description) VALUES ('Northern Neck George Washington Birthplace AVA', '');
INSERT INTO area (name, description) VALUES ('Rocky Knob AVA', '');
-- (Shared with West Virginia)
INSERT INTO area (name, description) VALUES ('Shenandoah Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Virginias Eastern Shore AVA', '');
INSERT INTO region_area (SELECT 46, i FROM generate_series(254, 261) as i);

INSERT INTO area (name, description) VALUES ('Washington', '');
INSERT INTO area (name, description) VALUES ('Ancient Lakes of the Columbia Valley AVA', '');
-- (Shared with Oregon)
-- INSERT INTO area (name, description) VALUES ('Columbia Gorge AVA', '');
-- (Shared with Oregon)
-- INSERT INTO area (name, description) VALUES ('Columbia Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Horse Heaven Hills AVA', '');
INSERT INTO area (name, description) VALUES ('Lake Chelan AVA', '');
-- (Shared with Idaho)
-- INSERT INTO area (name, description) VALUES ('Lewis-Clark Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Naches Heights AVA', '');
INSERT INTO area (name, description) VALUES ('Puget Sound AVA', '');
INSERT INTO area (name, description) VALUES ('Rattlesnake Hills AVA', '');
INSERT INTO area (name, description) VALUES ('Red Mountain AVA', '');
INSERT INTO area (name, description) VALUES ('Snipes Mountain AVA', '');
INSERT INTO area (name, description) VALUES ('Wahluke Slope AVA', '');
-- (Shared with Oregon)
-- INSERT INTO area (name, description) VALUES ('Walla Walla Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Yakima Valley AVA', '');
INSERT INTO region_area (SELECT 47, i FROM generate_series(262, 272) as i);
INSERT INTO region_area (region_id, area_id) VALUES (47, 221);
INSERT INTO region_area (region_id, area_id) VALUES (47, 222);
INSERT INTO region_area (region_id, area_id) VALUES (47, 149);
INSERT INTO region_area (region_id, area_id) VALUES (47, 234);

INSERT INTO area (name, description) VALUES ('West Virginia', '');
INSERT INTO area (name, description) VALUES ('Kanawha River Valley AVA', '');
-- (Shared with Indiana, Kentucky, and Ohio)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');
-- (Shared with Virginia)
-- INSERT INTO area (name, description) VALUES ('Shenandoah Valley AVA', '');
INSERT INTO region_area (SELECT 48, i FROM generate_series(273, 274) as i);
INSERT INTO region_area (region_id, area_id) VALUES (48, 156);
INSERT INTO region_area (region_id, area_id) VALUES (48, 260);

INSERT INTO area (name, description) VALUES ('Wisconsin', '');
INSERT INTO area (name, description) VALUES ('Lake Wisconsin AVA', '');
-- (Shared with Illinois, Iowa, and Minnesota)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Wisconsin Ledge AVA', '');
INSERT INTO region_area (SELECT 49, i FROM generate_series(275, 277) as i);
INSERT INTO region_area (region_id, area_id) VALUES (49, 153);

INSERT INTO area (name, description) VALUES ('Wyoming', '');
INSERT INTO region_area (SELECT 50, i FROM generate_series(278, 278) as i);
