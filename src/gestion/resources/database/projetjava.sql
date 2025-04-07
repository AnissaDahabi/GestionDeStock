CREATE DATABASE projetjava;
USE projetjava;

CREATE TABLE Suppliers (
    id_supplier INT NOT NULL PRIMARY KEY,
    name_supplier VARCHAR(50),
    phone_supplier VARCHAR(50),
    address_supplier VARCHAR(50),
    email_supplier VARCHAR(50)
);

CREATE TABLE Products (
    id_product INT NOT NULL,
    id_supplier INT NOT NULL,
    name_product VARCHAR(50),
    price_product DOUBLE,
    quantity_product INT,
    PRIMARY KEY (id_product),
    FOREIGN KEY (id_supplier) REFERENCES Suppliers(id_supplier)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Sales (
    id_sales INT NOT NULL,
    id_product int,
    id_supplier int,
	quantity_sales int,
    price_sales double,
    date_sales VARCHAR(50),
    PRIMARY KEY (id_sales),
    foreign key (id_product) references Products(id_product)
		ON DELETE CASCADE 
        ON UPDATE CASCADE, 
	foreign key (id_supplier) references Suppliers(id_supplier)
		ON DELETE CASCADE 
        ON UPDATE CASCADE
);

CREATE TABLE Reports (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    total_sales DOUBLE,
    total_quantity INT,
    id_supplier INT,
    id_product INT,
    FOREIGN KEY (id_supplier) REFERENCES Suppliers(id_supplier)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (id_product) REFERENCES Products(id_product)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


INSERT INTO Suppliers (id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier) VALUES
(2000, "Café des Hauts Plateaux", 0123456789, "10 Rue de la Culture, 75002 Paris", "contact@cafedeshautsplateaux.com"),
(2001, "Grains et Torréfaction", 0234567890, "25 Avenue des Grands Vins, 75008 Paris", "info@grainstorer.fr"),
(2002, "Production Café du Terroir", 0345678901, "35 Boulevard de la Prospérité, 13001 Marseille", "ventes@productioncafeterroir.com"),
(2003, "Café Excellence", 0456789012, "47 Rue du Marché des Grains, 31000 Toulouse", "support@cafeexcellence.fr"),
(2004, "Grands Producteurs de Café", 0567890123, "123 Route des Plantes, 59000 Lille", "contact@grandsproducteursdecafe.com"),
(2005, "Café des Champs", 0678901234, "12 Boulevard des Arômes, 38000 Grenoble", "info@cafedeschamps.com"),
(2006, "Café Savoir-Faire", 0789012345, "9 Rue des Agriculteurs, 67000 Strasbourg", "contact@cafesavoirfaire.com"),
(2007, "Les Grains du Pays", 0890123456, "56 Rue de la Torréfaction, 75012 Paris", "ventes@lesgrainsdupays.com"),
(2008, "Café Naturel de France", 0901234567, "22 Rue des Fermes, 33000 Bordeaux", "info@cafenatureldefrance.com"),
(2009, "Café d'Origine", 0123409876, "101 Rue des Producteurs, 44000 Nantes", "contact@cafedorigine.com"),
(2010, "Café Artisan", 0234509876, "89 Rue de l'Exploitation, 75015 Paris", "support@cafeartisan.com"),
(2011, "Café Cultivé", 0345609876, "34 Rue de la Ferme, 69004 Lyon", "ventes@cafecultive.com"),
(2012, "Café des Grands Terroirs", 0456709876, "16 Rue des Arômes, 80000 Amiens", "info@cafedesgrandsterroirs.com"),
(2013, "Café des Montagnes", 0567809876, "23 Rue de la Culture, 75002 Paris", "bonjour@cafedesmontagnes.com"),
(2014, "Café d'Altitude", 0678909876, "5 Rue des Alpages, 67000 Strasbourg", "contact@cafedaltitude.com"),
(2015, "La Compagnie des Cafés", 0789009876, "77 Rue des Torréfacteurs, 21000 Dijon", "support@compagniedescafes.com"),
(2016, "Fournisseur de Café de Qualité", 0899009876, "13 Rue des Plantations, 31000 Toulouse", "ventes@fournisseurdecafedequalite.com"),
(2017, "Café de Plantation", 0909009876, "8 Rue des Arômes, 69005 Lyon", "info@cafedeplantation.com"),
(2018, "Café des Producteurs Associés", 0123450987, "4 Avenue des Grains, 13006 Marseille", "contact@cafedesproducteursassocies.com"),
(2019, "Grains de Café de France", 0234560987, "10 Rue de la Ferme, 67000 Strasbourg", "contact@grainsdecafedefrance.com"),
(2020, "Café Espagnol", 0987654321, "50 Calle de la Café, 28001 Madrid", "info@cafespagnol.com"),
(2021, "Café Italiano", 0876543210, "60 Via della Torréfazione, 50123 Florence", "contact@cafeitaliano.it"),
(2022, "Kaffee aus Deutschland", 0765432109, "20 Strasse des Kaffees, 10115 Berlin", "info@kaffeeausdeutschland.de"),
(2023, "Café Português", 0654321098, "30 Rua do Café, 1100-123 Lisbonne", "support@cafeportugues.pt"),
(2024, "Caffè del Sud", 0543210987, "40 Via dei Torrefattori, 90123 Palermo", "contact@caffedelsud.it"),
(2025, "Café Brasil", 0432109876, "11 Avenida dos Cafés, 11000 São Paulo", "info@cafebrasil.com"),
(2026, "Café de l'Europe", 0321098765, "70 Rue de la Culture, 75004 Paris", "info@cafedeurope.com"),
(2027, "Café de la Montagne Verte", 0210987654, "100 Rue des Épicuriens, 34000 Montpellier", "contact@cafedelamontagneverte.com"),
(2028, "Kaffee Gourmet", 0109876543, "15 Kaiserstrasse, 60311 Frankfurt", "contact@kaffeegourmet.de"),
(2029, "Café Nordic", 0198765432, "25 Gatan de Kaffe, 11356 Stockholm", "info@cafenordic.se");

INSERT INTO Products (id_product, name_product, price_product, quantity_product, id_supplier) VALUES
(1000, "Café Parisien", 5.99, 50, 2000),
(1001, "Grains Mont-Blanc", 7.50, 60, 2000),
(1002, "Espresso Paris", 8.20, 45, 2001),
(1003, "Café du Terroir", 9.00, 55, 2002),
(1004, "Café Provence", 7.40, 65, 2002),
(1005, "Café Toulouse Tradition", 6.90, 52, 2003),
(1006, "Café du Sud-Ouest", 6.80, 62, 2003),
(1007, "Grains de Lille", 7.30, 48, 2004),
(1008, "Café des Flandres", 6.60, 60, 2004),
(1009, "Grains des Alpes", 8.00, 35, 2005),
(1010, "Café des Arômes", 7.80, 50, 2005),
(1011, "Café de la Meinau", 6.50, 58, 2006),
(1012, "Grains Alsaciens", 8.10, 64, 2006),
(1013, "Café du Pays Basque", 6.90, 45, 2007),
(1014, "Café Parisien Doux", 7.20, 70, 2007),
(1015, "Grains Bordelais", 9.50, 50, 2008),
(1016, "Café de Bordeaux", 8.20, 55, 2008),
(1017, "Café Nantais", 6.40, 50, 2009),
(1018, "Grains de Loire", 7.70, 30, 2009),
(1019, "Café Artisanal", 7.80, 40, 2010),
(1020, "Espresso Parisienne", 8.30, 52, 2010),
(1021, "Café Lyonnais", 7.10, 60, 2011),
(1022, "Grains de la Croix-Rousse", 6.90, 55, 2011),
(1023, "Café des Champs", 6.20, 63, 2012),
(1024, "Café du Terroir", 7.50, 47, 2012),
(1025, "Café Montagne", 7.80, 30, 2013),
(1026, "Grains de la Montagne Verte", 8.40, 60, 2013),
(1027, "Café du Jura", 8.00, 48, 2014),
(1028, "Grains Provençaux", 7.20, 54, 2014),
(1029, "Café Robusta Toulouse", 9.00, 49, 2015),
(1030, "Espresso des Hautes-Alpes", 7.50, 65, 2015),
(1031, "Café Fougères", 8.10, 40, 2016),
(1032, "Grains de la Vallée", 6.70, 60, 2016),
(1033, "Café Lyon Tradition", 7.40, 50, 2017),
(1034, "Café de la Côte d'Azur", 8.30, 45, 2017),
(1035, "Grains Savoyards", 7.80, 50, 2018),
(1036, "Café du Mont Blanc", 9.00, 70, 2018),
(1037, "Café Parisien Élégant", 7.90, 60, 2019),
(1038, "Grains Lorrains", 6.90, 55, 2019),
(1039, "Café Espagnol", 7.50, 50, 2020),
(1040, "Café Andalou", 8.00, 63, 2020),
(1041, "Café Italien", 8.20, 68, 2021),
(1042, "Grains de Toscane", 7.40, 60, 2021),
(1043, "Café Berlinois", 9.00, 56, 2022),
(1044, "Café Alémanique", 7.80, 65, 2022),
(1045, "Café Portugais", 6.90, 70, 2023),
(1046, "Grains de Lisbonne", 7.50, 62, 2023),
(1047, "Café Sicilien", 7.60, 49, 2024),
(1048, "Café Palermitan", 8.00, 59, 2024),
(1049, "Café São Paulo", 8.50, 53, 2025),
(1050, "Grains Brésiliens", 7.80, 64, 2025),
(1051, "Café Paris-Rio", 9.20, 55, 2026),
(1052, "Café de la Côte d'Ivoire", 6.80, 60, 2026),
(1053, "Grains de Francfort", 7.50, 58, 2027),
(1054, "Café Gourmet Allemand", 8.20, 45, 2027),
(1055, "Café Suédois", 8.00, 60, 2028),
(1056, "Café Stockholm", 7.40, 50, 2028),
(1057, "Grains Scandinaves", 8.30, 70, 2029),
(1058, "Café du Nord", 7.90, 55, 2029);


INSERT INTO Sales (id_sales, id_product, id_supplier, date_sales, price_sales, quantity_sales) VALUES
(3000, 1000, 2000, '10-01-2025', 179.70, 30),
(3001, 1001, 2000, '20-01-2025', 337.50, 45),
(3002, 1002, 2001, '02-02-2025', 205.00, 25),
(3003, 1003, 2002, '15-02-2025', 360.00, 40),
(3004, 1004, 2002, '03-03-2025', 370.00, 50),
(3005, 1005, 2003, '08-03-2025', 414.00, 60),
(3006, 1006, 2003, '25-03-2025', 136.00, 20),
(3007, 1007, 2004, '01-04-2025', 219.00, 30),
(3008, 1008, 2004, '15-04-2025', 363.00, 55),
(3009, 1009, 2005, '20-04-2025', 320.00, 40),
(3010, 1010, 2005, '02-05-2025', 390.00, 50),
(3011, 1011, 2006, '05-05-2025', 162.50, 25),
(3012, 1012, 2006, '18-05-2025', 364.50, 45),
(3013, 1013, 2007, '01-06-2025', 207.00, 30),
(3014, 1014, 2007, '10-06-2025', 252.00, 35),
(3015, 1015, 2008, '15-06-2025', 380.00, 40),
(3016, 1016, 2008, '01-07-2025', 246.00, 30),
(3017, 1017, 2009, '05-07-2025', 320.00, 50),
(3018, 1018, 2009, '12-07-2025', 154.00, 20),
(3019, 1019, 2010, '18-07-2025', 468.00, 60),
(3020, 1020, 2010, '01-08-2025', 332.00, 40),
(3021, 1021, 2011, '10-08-2025', 213.00, 30),
(3022, 1022, 2011, '15-08-2025', 241.50, 35),
(3023, 1023, 2012, '01-09-2025', 279.00, 45),
(3024, 1024, 2012, '07-09-2025', 300.00, 40),
(3025, 1025, 2013, '20-09-2025', 390.00, 50),
(3026, 1026, 2013, '01-10-2025', 252.00, 30),
(3027, 1027, 2014, '05-10-2025', 360.00, 45),
(3028, 1028, 2014, '15-10-2025', 144.00, 20),
(3029, 1029, 2015, '01-11-2025', 360.00, 40),
(3030, 1030, 2015, '12-11-2025', 450.00, 60),
(3031, 1031, 2016, '15-11-2025', 243.00, 30),
(3032, 1032, 2016, '01-12-2025', 234.50, 35),
(3033, 1033, 2017, '05-12-2025', 222.00, 30),
(3034, 1034, 2017, '10-12-2025', 207.50, 25),
(3035, 1035, 2018, '15-12-2025', 312.00, 40),
(3036, 1036, 2018, '18-12-2025', 495.00, 55),
(3037, 1037, 2019, '20-12-2025', 474.00, 60),
(3038, 1038, 2019, '22-12-2025', 172.50, 25),
(3039, 1039, 2020, '25-12-2025', 262.50, 35);



