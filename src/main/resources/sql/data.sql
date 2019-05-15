INSERT INTO authority (id,authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO authority (id,authority) VALUES (2,'ROLE_USER');

INSERT INTO users (id, enabled, username, password) VALUES ( 999999, true, 'admin', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG');

INSERT INTO user_authority (authority_id, user_id) VALUES ( 1, 999999 );

INSERT INTO users (id, enabled, username, password) VALUES ( 888888, true, 'user', '$2a$10$FQvwDTU8onTZ74SJy1N2zOLAeIC5em9ETr5ggcPS9/Ne/S4WXM2zG');

INSERT INTO user_authority (authority_id, user_id) VALUES ( 2, 888888 );

INSERT INTO country (name, description) VALUES ('Afghanistan', '');
INSERT INTO country (name, description) VALUES ('Albania', '');
INSERT INTO country (name, description) VALUES ('Algeria', '');
INSERT INTO country (name, description) VALUES ('Andorra', '');
INSERT INTO country (name, description) VALUES ('Angola', '');
INSERT INTO country (name, description) VALUES ('Antigua and Barbuda', '');
INSERT INTO country (name, description) VALUES ('Argentina', '');
INSERT INTO country (name, description) VALUES ('Armenia', '');
INSERT INTO country (name, description) VALUES ('Australia', '');
INSERT INTO country (name, description) VALUES ('Austria', '');
INSERT INTO country (name, description) VALUES ('Azerbaijan', '');
INSERT INTO country (name, description) VALUES ('The Bahamas', '');
INSERT INTO country (name, description) VALUES ('Bahrain', '');
INSERT INTO country (name, description) VALUES ('Bangladesh', '');
INSERT INTO country (name, description) VALUES ('Barbados', '');
INSERT INTO country (name, description) VALUES ('Belarus', '');
INSERT INTO country (name, description) VALUES ('Belgium', '');
INSERT INTO country (name, description) VALUES ('Belize', '');
INSERT INTO country (name, description) VALUES ('Benin', '');
INSERT INTO country (name, description) VALUES ('Bhutan', '');
INSERT INTO country (name, description) VALUES ('Bolivia', '');
INSERT INTO country (name, description) VALUES ('Bosnia and Herzegovina', '');
INSERT INTO country (name, description) VALUES ('Botswana', '');
INSERT INTO country (name, description) VALUES ('Brazil', '');
INSERT INTO country (name, description) VALUES ('Brunei', '');
INSERT INTO country (name, description) VALUES ('Bulgaria', '');
INSERT INTO country (name, description) VALUES ('Burkina Faso', '');
INSERT INTO country (name, description) VALUES ('Burundi', '');
INSERT INTO country (name, description) VALUES ('Cabo Verde', '');
INSERT INTO country (name, description) VALUES ('Cambodia', '');
INSERT INTO country (name, description) VALUES ('Cameroon', '');
INSERT INTO country (name, description) VALUES ('Canada', '');
INSERT INTO country (name, description) VALUES ('Central African Republic', '');
INSERT INTO country (name, description) VALUES ('Chad', '');
INSERT INTO country (name, description) VALUES ('Chile', '');
INSERT INTO country (name, description) VALUES ('China', '');
INSERT INTO country (name, description) VALUES ('Colombia', '');
INSERT INTO country (name, description) VALUES ('Comoros', '');
INSERT INTO country (name, description) VALUES ('Congo, Democratic Republic of the', '');
INSERT INTO country (name, description) VALUES ('Costa Rica', '');
INSERT INTO country (name, description) VALUES ('Côte d’Ivoire', '');
INSERT INTO country (name, description) VALUES ('Croatia', '');
INSERT INTO country (name, description) VALUES ('Cuba', '');
INSERT INTO country (name, description) VALUES ('Cyprus', '');
INSERT INTO country (name, description) VALUES ('Czech Republic', '');
INSERT INTO country (name, description) VALUES ('Denmark', '');
INSERT INTO country (name, description) VALUES ('Djibouti', '');
INSERT INTO country (name, description) VALUES ('Dominica', '');
INSERT INTO country (name, description) VALUES ('Dominican Republic', '');
INSERT INTO country (name, description) VALUES ('East Timor', '');
INSERT INTO country (name, description) VALUES ('Ecuador', '');
INSERT INTO country (name, description) VALUES ('Egypt', '');
INSERT INTO country (name, description) VALUES ('El Salvador', '');
INSERT INTO country (name, description) VALUES ('Equatorial Guinea', '');
INSERT INTO country (name, description) VALUES ('Eritrea', '');
INSERT INTO country (name, description) VALUES ('Estonia', '');
INSERT INTO country (name, description) VALUES ('Eswatini', '');
INSERT INTO country (name, description) VALUES ('Ethiopia', '');
INSERT INTO country (name, description) VALUES ('Fiji', '');
INSERT INTO country (name, description) VALUES ('Finland', '');
INSERT INTO country (name, description) VALUES ('France', '');
INSERT INTO country (name, description) VALUES ('Gabon', '');
INSERT INTO country (name, description) VALUES ('The Gambia', '');
INSERT INTO country (name, description) VALUES ('Georgia', '');
INSERT INTO country (name, description) VALUES ('Germany', '');
INSERT INTO country (name, description) VALUES ('Ghana', '');
INSERT INTO country (name, description) VALUES ('Greece', '');
INSERT INTO country (name, description) VALUES ('Grenada', '');
INSERT INTO country (name, description) VALUES ('Guatemala', '');
INSERT INTO country (name, description) VALUES ('Guinea', '');
INSERT INTO country (name, description) VALUES ('Guinea-Bissau', '');
INSERT INTO country (name, description) VALUES ('Guyana', '');
INSERT INTO country (name, description) VALUES ('Haiti', '');
INSERT INTO country (name, description) VALUES ('Honduras', '');
INSERT INTO country (name, description) VALUES ('Hungary', '');
INSERT INTO country (name, description) VALUES ('Iceland', '');
INSERT INTO country (name, description) VALUES ('India', '');
INSERT INTO country (name, description) VALUES ('Indonesia', '');
INSERT INTO country (name, description) VALUES ('Iran', '');
INSERT INTO country (name, description) VALUES ('Iraq', '');
INSERT INTO country (name, description) VALUES ('Ireland', '');
INSERT INTO country (name, description) VALUES ('Israel', '');
INSERT INTO country (name, description) VALUES ('Italy', '');
INSERT INTO country (name, description) VALUES ('Jamaica', '');
INSERT INTO country (name, description) VALUES ('Japan', '');
INSERT INTO country (name, description) VALUES ('Jordan', '');
INSERT INTO country (name, description) VALUES ('Kazakhstan', '');
INSERT INTO country (name, description) VALUES ('Kenya', '');
INSERT INTO country (name, description) VALUES ('Kiribati', '');
INSERT INTO country (name, description) VALUES ('Korea North', '');
INSERT INTO country (name, description) VALUES ('Korea South', '');
INSERT INTO country (name, description) VALUES ('Kosovo', '');
INSERT INTO country (name, description) VALUES ('Kuwait', '');
INSERT INTO country (name, description) VALUES ('Kyrgyzstan', '');
INSERT INTO country (name, description) VALUES ('Laos', '');
INSERT INTO country (name, description) VALUES ('Latvia', '');
INSERT INTO country (name, description) VALUES ('Lebanon', '');
INSERT INTO country (name, description) VALUES ('Lesotho', '');
INSERT INTO country (name, description) VALUES ('Liberia', '');
INSERT INTO country (name, description) VALUES ('Libya', '');
INSERT INTO country (name, description) VALUES ('Liechtenstein', '');
INSERT INTO country (name, description) VALUES ('Lithuania', '');
INSERT INTO country (name, description) VALUES ('Luxembourg', '');
INSERT INTO country (name, description) VALUES ('Madagascar', '');
INSERT INTO country (name, description) VALUES ('Malawi', '');
INSERT INTO country (name, description) VALUES ('Malaysia', '');
INSERT INTO country (name, description) VALUES ('Maldives', '');
INSERT INTO country (name, description) VALUES ('Mali', '');
INSERT INTO country (name, description) VALUES ('Malta', '');
INSERT INTO country (name, description) VALUES ('Marshall Islands', '');
INSERT INTO country (name, description) VALUES ('Mauritania', '');
INSERT INTO country (name, description) VALUES ('Mauritius', '');
INSERT INTO country (name, description) VALUES ('Mexico', '');
INSERT INTO country (name, description) VALUES ('Micronesia, Federated States of', '');
INSERT INTO country (name, description) VALUES ('Moldova', '');
INSERT INTO country (name, description) VALUES ('Monaco', '');
INSERT INTO country (name, description) VALUES ('Mongolia', '');
INSERT INTO country (name, description) VALUES ('Montenegro', '');
INSERT INTO country (name, description) VALUES ('Morocco', '');
INSERT INTO country (name, description) VALUES ('Mozambique', '');
INSERT INTO country (name, description) VALUES ('Myanmar (Burma)', '');
INSERT INTO country (name, description) VALUES ('Namibia', '');
INSERT INTO country (name, description) VALUES ('Nauru', '');
INSERT INTO country (name, description) VALUES ('Nepal', '');
INSERT INTO country (name, description) VALUES ('Netherlands', '');
INSERT INTO country (name, description) VALUES ('New Zealand', '');
INSERT INTO country (name, description) VALUES ('Nicaragua', '');
INSERT INTO country (name, description) VALUES ('Niger', '');
INSERT INTO country (name, description) VALUES ('Nigeria', '');
INSERT INTO country (name, description) VALUES ('North Macedonia', '');
INSERT INTO country (name, description) VALUES ('Norway', '');
INSERT INTO country (name, description) VALUES ('Oman', '');
INSERT INTO country (name, description) VALUES ('Pakistan', '');
INSERT INTO country (name, description) VALUES ('Palau', '');
INSERT INTO country (name, description) VALUES ('Panama', '');
INSERT INTO country (name, description) VALUES ('Papua New Guinea', '');
INSERT INTO country (name, description) VALUES ('Paraguay', '');
INSERT INTO country (name, description) VALUES ('Peru', '');
INSERT INTO country (name, description) VALUES ('Philippines', '');
INSERT INTO country (name, description) VALUES ('Poland', '');
INSERT INTO country (name, description) VALUES ('Portugal', '');
INSERT INTO country (name, description) VALUES ('Qatar', '');
INSERT INTO country (name, description) VALUES ('Romania', '');
INSERT INTO country (name, description) VALUES ('Russia', '');
INSERT INTO country (name, description) VALUES ('Rwanda', '');
INSERT INTO country (name, description) VALUES ('Saint Kitts and Nevis', '');
INSERT INTO country (name, description) VALUES ('Saint Lucia', '');
INSERT INTO country (name, description) VALUES ('Saint Vincent and the Grenadines', '');
INSERT INTO country (name, description) VALUES ('Samoa', '');
INSERT INTO country (name, description) VALUES ('San Marino', '');
INSERT INTO country (name, description) VALUES ('Sao Tome and Principe', '');
INSERT INTO country (name, description) VALUES ('Saudi Arabia', '');
INSERT INTO country (name, description) VALUES ('Senegal', '');
INSERT INTO country (name, description) VALUES ('Serbia', '');
INSERT INTO country (name, description) VALUES ('Seychelles', '');
INSERT INTO country (name, description) VALUES ('Sierra Leone', '');
INSERT INTO country (name, description) VALUES ('Singapore', '');
INSERT INTO country (name, description) VALUES ('Slovakia', '');
INSERT INTO country (name, description) VALUES ('Slovenia', '');
INSERT INTO country (name, description) VALUES ('Solomon Islands', '');
INSERT INTO country (name, description) VALUES ('Somalia', '');
INSERT INTO country (name, description) VALUES ('South Africa', '');
INSERT INTO country (name, description) VALUES ('Spain', '');
INSERT INTO country (name, description) VALUES ('Sri Lanka', '');
INSERT INTO country (name, description) VALUES ('Sudan', '');
INSERT INTO country (name, description) VALUES ('Suriname', '');
INSERT INTO country (name, description) VALUES ('Sweden', '');
INSERT INTO country (name, description) VALUES ('Switzerland', '');
INSERT INTO country (name, description) VALUES ('Syria', '');
INSERT INTO country (name, description) VALUES ('Taiwan', '');
INSERT INTO country (name, description) VALUES ('Tajikistan', '');
INSERT INTO country (name, description) VALUES ('Tanzania', '');
INSERT INTO country (name, description) VALUES ('Thailand', '');
INSERT INTO country (name, description) VALUES ('Togo', '');
INSERT INTO country (name, description) VALUES ('Tonga', '');
INSERT INTO country (name, description) VALUES ('Trinidad and Tobago', '');
INSERT INTO country (name, description) VALUES ('Tunisia', '');
INSERT INTO country (name, description) VALUES ('Turkey', '');
INSERT INTO country (name, description) VALUES ('Turkmenistan', '');
INSERT INTO country (name, description) VALUES ('Tuvalu', '');
INSERT INTO country (name, description) VALUES ('Uganda', '');
INSERT INTO country (name, description) VALUES ('Ukraine', '');
INSERT INTO country (name, description) VALUES ('United Arab Emirates', '');
INSERT INTO country (name, description) VALUES ('United Kingdom', '');
INSERT INTO country (name, description) VALUES ('United States', '');
INSERT INTO country (name, description) VALUES ('Uruguay', '');
INSERT INTO country (name, description) VALUES ('Uzbekistan', '');
INSERT INTO country (name, description) VALUES ('Vanuatu', '');
INSERT INTO country (name, description) VALUES ('Vatican City', '');
INSERT INTO country (name, description) VALUES ('Venezuela', '');
INSERT INTO country (name, description) VALUES ('Vietnam', '');
INSERT INTO country (name, description) VALUES ('Yemen', '');
INSERT INTO country (name, description) VALUES ('Zambia', '');
INSERT INTO country (name, description) VALUES ('Zimbabwe', '');

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

-- USA/Area
INSERT INTO area (name, description) VALUES ('Alabama', '');

INSERT INTO area (name, description) VALUES ('Alaska', '');

INSERT INTO area (name, description) VALUES ('Arizona', '');
INSERT INTO area (name, description) VALUES ('Sonoita AVA', '');
INSERT INTO area (name, description) VALUES ('Willcox AVA', '');

INSERT INTO area (name, description) VALUES ('Arkansas', '');
INSERT INTO area (name, description) VALUES ('Altus AVA', '');
INSERT INTO area (name, description) VALUES ('Arkansas Mountain AVA', '');
-- (Shared with Missouri and Oklahoma)
INSERT INTO area (name, description) VALUES ('Ozark Mountain AVA', '');

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

INSERT INTO area (name, description) VALUES ('Colorado', '');
INSERT INTO area (name, description) VALUES ('Grand Valley AVA', '');
INSERT INTO area (name, description) VALUES ('West Elks AVA', '');

INSERT INTO area (name, description) VALUES ('Connecticut', '');
-- (Shared with Massachusetts and Rhode Island)
INSERT INTO area (name, description) VALUES ('Southeastern New England AVA', '');
INSERT INTO area (name, description) VALUES ('Western Connecticut Highlands AVA', '');

INSERT INTO area (name, description) VALUES ('Delaware', '');

INSERT INTO area (name, description) VALUES ('Florida', '');

INSERT INTO area (name, description) VALUES ('Georgia', '');
INSERT INTO area (name, description) VALUES ('Dahlonega Plateau AVA', '');
-- (Shared with North Carolina)
INSERT INTO area (name, description) VALUES ('Upper Hiwassee Highlands AVA', '');

INSERT INTO area (name, description) VALUES ('Hawaii', '');

INSERT INTO area (name, description) VALUES ('Idaho', '');
INSERT INTO area (name, description) VALUES ('Eagle Foothills AVA', '');
-- (Shared with Washington)
INSERT INTO area (name, description) VALUES ('Lewis-Clark Valley AVA', '');
-- (Shared with Oregon)
INSERT INTO area (name, description) VALUES ('Snake River Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Illinois', '');
INSERT INTO area (name, description) VALUES ('Shawnee Hills AVA', '');
-- (Shared with Iowa, Minnesota, and Wisconsin)
INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Indiana', '');
INSERT INTO area (name, description) VALUES ('Indiana Uplands', '');
-- (Shared with Kentucky, Ohio, and West Virginia)
INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Iowa', '');
-- (Shared with Illinois, Minnesota, and Wisconsin)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Kansas', '');

INSERT INTO area (name, description) VALUES ('Kentucky', '');
-- (Shared with Indiana, Ohio, and West Virginia)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Louisiana', '');
-- (Shared with Mississippi and Tennessee)
INSERT INTO area (name, description) VALUES ('Mississippi Delta AVA', '');

INSERT INTO area (name, description) VALUES ('Maine', '');

INSERT INTO area (name, description) VALUES ('Maryland', '');
INSERT INTO area (name, description) VALUES ('Catoctin AVA', '');
-- (Shared with Pennsylvania)
INSERT INTO area (name, description) VALUES ('Cumberland Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Linganore AVA', '');

INSERT INTO area (name, description) VALUES ('Massachusetts', '');
INSERT INTO area (name, description) VALUES ('Marthas Vineyard AVA', '');
-- (Shared with Connecticut and Rhode Island)
-- INSERT INTO area (name, description) VALUES ('Southeastern New England AVA', '');

INSERT INTO area (name, description) VALUES ('Michigan', '');
INSERT INTO area (name, description) VALUES ('Fennville AVA', '');
INSERT INTO area (name, description) VALUES ('Lake Michigan Shore AVA', '');
INSERT INTO area (name, description) VALUES ('Leelanau Peninsula AVA', '');
INSERT INTO area (name, description) VALUES ('Old Mission Peninsula AVA', '');
INSERT INTO area (name, description) VALUES ('Tip of the Mitt AVA', '');

INSERT INTO area (name, description) VALUES ('Minnesota', '');
INSERT INTO area (name, description) VALUES ('Alexandria Lakes AVA', '');
-- (Shared with Illinois, Iowa, and Wisconsin)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Mississippi', '');
-- (Shared with Louisiana and Tennessee)
-- INSERT INTO area (name, description) VALUES ('Mississippi Delta AVA', '');

INSERT INTO area (name, description) VALUES ('Missouri', '');
INSERT INTO area (name, description) VALUES ('Augusta AVA', '');
INSERT INTO area (name, description) VALUES ('Hermann AVA', '');
INSERT INTO area (name, description) VALUES ('Ozark Highlands AVA', '');
-- (Shared with Arkansas and Oklahoma)
-- INSERT INTO area (name, description) VALUES ('Ozark Mountain AVA', '');

INSERT INTO area (name, description) VALUES ('Montana', '');

INSERT INTO area (name, description) VALUES ('Nebraska', '');

INSERT INTO area (name, description) VALUES ('Nevada', '');

INSERT INTO area (name, description) VALUES ('New Hampshire', '');

INSERT INTO area (name, description) VALUES ('New Jersey', '');
INSERT INTO area (name, description) VALUES ('Cape May Peninsula AVA', '');
-- (Shared with Pennsylvania)
INSERT INTO area (name, description) VALUES ('Central Delaware Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Outer Coastal Plain AVA', '');
INSERT INTO area (name, description) VALUES ('Warren Hills AVA', '');

INSERT INTO area (name, description) VALUES ('New Mexico', '');
-- (Shared with Texas)
INSERT INTO area (name, description) VALUES ('Mesilla Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Middle Rio Grande Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Mimbres Valley AVA', '');

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

INSERT INTO area (name, description) VALUES ('North Carolina', '');
-- (Shared with Tennessee and Virginia)
INSERT INTO area (name, description) VALUES ('Appalachian High Country AVA', '');
INSERT INTO area (name, description) VALUES ('Haw River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Swan Creek AVA', '');
-- (Shared with Georgia)
-- INSERT INTO area (name, description) VALUES ('Upper Hiwassee Highlands AVA', '');
INSERT INTO area (name, description) VALUES ('Yadkin Valley AVA', '');

INSERT INTO area (name, description) VALUES ('North Dakota', '');

INSERT INTO area (name, description) VALUES ('Ohio', '');
INSERT INTO area (name, description) VALUES ('Grand River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Isle St. George AVA', '');
-- (Shared with New York and Pennsylvania)
-- INSERT INTO area (name, description) VALUES ('Lake Erie AVA', '');
INSERT INTO area (name, description) VALUES ('Loramie Creek AVA', '');
-- (Shared with Indiana, Kentucky, and West Virginia)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Oklahoma', '');
-- (Shared with Arkansas and Missouri)
-- INSERT INTO area (name, description) VALUES ('Ozark Mountain AVA', '');

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

INSERT INTO area (name, description) VALUES ('Pennsylvania', '');
-- (Shared with New Jersey)
-- INSERT INTO area (name, description) VALUES ('Central Delaware Valley AVA', '');
-- (Shared with Maryland)
-- INSERT INTO area (name, description) VALUES ('Cumberland Valley AVA', '');
-- (Shared with New York and Ohio)
-- INSERT INTO area (name, description) VALUES ('Lake Erie AVA', '');
INSERT INTO area (name, description) VALUES ('Lancaster Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Lehigh Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Rhode Island', '');
-- (Shared with Connecticut and Massachusetts)
-- INSERT INTO area (name, description) VALUES ('Southeastern New England AVA', '');

INSERT INTO area (name, description) VALUES ('South Carolina', '');

INSERT INTO area (name, description) VALUES ('South Dakota', '');

INSERT INTO area (name, description) VALUES ('Tennessee', '');
-- (Shared with Louisiana and Mississippi)
-- INSERT INTO area (name, description) VALUES ('Mississippi Delta AVA', '');

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

INSERT INTO area (name, description) VALUES ('Utah', '');

INSERT INTO area (name, description) VALUES ('Vermont', '');

INSERT INTO area (name, description) VALUES ('Virginia', '');
INSERT INTO area (name, description) VALUES ('Middleburg AVA', '');
INSERT INTO area (name, description) VALUES ('Monticello AVA', '');
INSERT INTO area (name, description) VALUES ('North Fork of Roanoke AVA', '');
INSERT INTO area (name, description) VALUES ('Northern Neck George Washington Birthplace AVA', '');
INSERT INTO area (name, description) VALUES ('Rocky Knob AVA', '');
-- (Shared with West Virginia)
INSERT INTO area (name, description) VALUES ('Shenandoah Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Virginias Eastern Shore AVA', '');

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

INSERT INTO area (name, description) VALUES ('West Virginia', '');
INSERT INTO area (name, description) VALUES ('Kanawha River Valley AVA', '');
-- (Shared with Indiana, Kentucky, and Ohio)
-- INSERT INTO area (name, description) VALUES ('Ohio River Valley AVA', '');
-- (Shared with Virginia)
-- INSERT INTO area (name, description) VALUES ('Shenandoah Valley AVA', '');

INSERT INTO area (name, description) VALUES ('Wisconsin', '');
INSERT INTO area (name, description) VALUES ('Lake Wisconsin AVA', '');
-- (Shared with Illinois, Iowa, and Minnesota)
-- INSERT INTO area (name, description) VALUES ('Upper Mississippi River Valley AVA', '');
INSERT INTO area (name, description) VALUES ('Wisconsin Ledge AVA', '');

INSERT INTO area (name, description) VALUES ('Wyoming', '');
