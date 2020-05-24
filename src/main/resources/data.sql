INSERT INTO category (id, name) VALUES (1, 'Dom i Ogród');
INSERT INTO category (id, name) VALUES (2, 'Telefony');
INSERT INTO category (id, name) VALUES (3, 'Uroda');
INSERT INTO category (id, name) VALUES (4, 'Dziecko');

INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units, is_active)
VALUES (9239393940, 'SAMSUNG GALAXY S10 SM-G973F DUAL SIM 128GB KOLORY', 3239.01, 3100.00, false, true , 2,10,true);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units, is_active)
VALUES (8858730448, 'Nokia 8800 Gold Arte PL (18k złoto, oryginał)', 1900.00, 1950.00, true, false, 2, 1111,true);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units, is_active)
VALUES (7795805859, 'SAMSUNG S8 64GB - WYBÓR KOLORÓW + GRATISY - KL A+', 1249.00, 1250.00, true, true, 2, 1111, true);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units, is_active)
VALUES (8143520520, 'BRAMY GARAŻOWE ROLOWANE Brama Rolowana SZYBKO 2DNI', 1635, 1500, false, false , 1, 9, true);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units, is_active)
VALUES (9269640410, 'Nacomi - Oczyszczająca maseczka do twarzy', 34.90, 30.00, false, true, 3, 1000, true);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units, is_active)
VALUES (9153310416, 'PAMPERS Pieluchy Active Baby 6 36szt', 43, 45, true, true, 4, 9, true);

INSERT INTO sorting_parameter (id, name) VALUES (1, 'productPrice');
INSERT INTO sorting_parameter (id, name) VALUES (2, 'expectedPrice');
INSERT INTO sorting_parameter (id, name) VALUES (3, 'offerName');