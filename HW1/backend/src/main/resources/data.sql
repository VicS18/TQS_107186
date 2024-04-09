INSERT INTO bus (id, max_seats) VALUES (10,50);
INSERT INTO bus (id, max_seats) VALUES (25,40);
INSERT INTO bus (id, max_seats) VALUES (30,30);

INSERT INTO trip (id, departure, destination, start_day_of_week, start_time, end_time, price, bus_id) VALUES (10,'CityA', 'CityB', 'MONDAY', '08:00', '12:00', 100.0, 10);
INSERT INTO trip (id, departure, destination, start_day_of_week, start_time, end_time, price, bus_id) VALUES (11,'CityB', 'CityC', 'TUESDAY', '09:00', '13:00', 120.0, 25);
INSERT INTO trip (id, departure, destination, start_day_of_week, start_time, end_time, price, bus_id) VALUES (12,'CityC', 'CityD', 'WEDNESDAY', '10:00', '14:00', 150.0, 30);

INSERT INTO ticket (id, bus_id, trip_id, status, ticket_date, price, start_time, end_time, citizen_card, email) VALUES (12, 10, 10, 'PENDING', '2024-04-15', 100.0, '08:00', '12:00', '000000000ZZ4', 'test25@example.com');
INSERT INTO ticket (id, bus_id, trip_id, status, ticket_date, price, start_time, end_time, citizen_card, email) VALUES (13, 25, 11, 'PENDING', '2024-04-16', 120.0, '09:00', '13:00', '000000000ZZ4', 'test10@example.com');
INSERT INTO ticket (id, bus_id, trip_id, status, ticket_date, price, start_time, end_time, citizen_card, email) VALUES (16, 25, 12, 'PENDING', '2024-04-17', 150.0, '10:00', '14:00', '000000000ZZ4', 'test09@example.com');
INSERT INTO ticket (id, bus_id, trip_id, status, ticket_date, price, start_time, end_time, citizen_card, email) VALUES (17, 30, 10, 'PENDING', '2024-04-22', 100.0, '08:00', '12:00', '000000000ZZ4', 'test1@example.com');
INSERT INTO ticket (id, bus_id, trip_id, status, ticket_date, price, start_time, end_time, citizen_card, email) VALUES (11, 10, 10, 'DONE', '2024-04-09', 120.0, '09:00', '13:00', '000000000ZZ4', 'test4@example.com');

