INSERT INTO category (id, name) VALUES (1, 'Dom i Ogród');
INSERT INTO category (id, name) VALUES (2, 'Telefony');
INSERT INTO category (id, name) VALUES (3, 'Uroda');
INSERT INTO category (id, name) VALUES (4, 'Dziecko');

INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units)
VALUES (9080891298, 'Apple iPhone 11 64 GB Dual SIM Czarny', 3239.01, 3100.00, false, true , 2,10);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units)
VALUES (8900206445, 'Smartfon Apple iPhone X 3/64GB Silver MQAD2PM/A', 1949.00, 1950.00, true, false, 2, 1111);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units)
VALUES (8529206445, 'Apple iPhone 7 3/64GB Black MQAD2PM/A', 1249.00, 1250.00, true, true, 2, 1111);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units)
VALUES (8143520520, 'BRAMY GARAŻOWE ROLOWANE Brama Rolowana SZYBKO 2DNI', 1635, 1500, false, false , 1, 9);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units)
VALUES (9269640410, 'Nacomi - Oczyszczająca maseczka do twarzy', 34.90, 30.00, false, true, 3, 1000);
INSERT INTO offer (id, offer_name, product_price, expected_price, is_good_price, is_favourite, category_id, number_of_available_units)
VALUES (9153310416, 'PAMPERS Pieluchy Active Baby 6 36szt', 43, 45, true, true, 4, 9);

INSERT INTO sorting_parameter (id, name) VALUES (1, 'productPrice');
INSERT INTO sorting_parameter (id, name) VALUES (2, 'expectedPrice');
INSERT INTO sorting_parameter (id, name) VALUES (3, 'offerName');
INSERT INTO sorting_parameter (id, name) VALUES (4, 'isFavourite');
INSERT INTO sorting_parameter (id, name) VALUES (5, 'isGoodPrice');