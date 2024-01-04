insert into users (user_id, login, password) VALUES (1, 'admin', '$2a$12$QbLeHcMqII.JxrAEG9PnV.WjoJaYAdf274IxgzVnqb/xdsJ6WIiZa');
insert into departments (department_id, parent_id, name) values (1, 0, 'Луганський національний університет імені Тараса Шевченка');
insert into passports (series, date_of_birth, place_of_birth, date_of_issue, date_of_expiring, place_of_issue) VALUES ('AH', '2021-06-15', 'Rubizhne', '2021-06-15', '2031-06-15', '4436');
insert into workplaces (position, day_of_start) VALUES ('Teacher', '2021-06-15');
insert into titles (title_name, speciality, serial_number, date_of_issue, name_of_place) VALUES ('PhD', 121, 'AH', '2021-06-15', 1);
insert into educations (degree, diploma_number, diploma_series, university_name) VALUES ('Bachelor', 123321, 'AH', 1);
insert into employers (full_name, photo_url, phone, address, rntrc, passport_id, workplace_id, titles_id, education_id, department_id) VALUES ('Dmytro Klopov', 'url:localhost:8080', '+380950000000', 'Poltava, Kovalya 5','02125131', 1, 1, 1, 1, 1);
