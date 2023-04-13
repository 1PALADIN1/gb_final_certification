/*
7. В подключенном MySQL репозитории создать базу данных “Друзья человека”
 */

DROP DATABASE IF EXISTS human_friends;
CREATE DATABASE human_friends;
USE human_friends;

/*
8. Создать таблицы с иерархией из диаграммы в БД
 */

DROP TABLE IF EXISTS animals;
CREATE TABLE animals (
	id SERIAL PRIMARY KEY,
	type VARCHAR(50) NOT NULL
);

INSERT INTO animals (type) VALUES
('Домашние'),
('Вьючерные');

-- domestic animals

DROP TABLE IF EXISTS cats;
CREATE TABLE cats (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	commands VARCHAR(250) NULL,
	birthday DATE,
	type_id BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (type_id) REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS dogs;
CREATE TABLE dogs (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	commands VARCHAR(250) NULL,
	birthday DATE,
	type_id BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (type_id) REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS humsters;
CREATE TABLE humsters (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	commands VARCHAR(250) NULL,
	birthday DATE,
	type_id BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (type_id) REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- pack animals

DROP TABLE IF EXISTS horses;
CREATE TABLE horses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	commands VARCHAR(250) NULL,
	birthday DATE,
	type_id BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (type_id) REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS camels;
CREATE TABLE camels (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	commands VARCHAR(250) NULL,
	birthday DATE,
	type_id BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (type_id) REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS donkeys;
CREATE TABLE donkeys (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	commands VARCHAR(250) NULL,
	birthday DATE,
	type_id BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (type_id) REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
 */

INSERT INTO dogs (name, commands, birthday, type_id) VALUES
('Альф', 'Сидеть, лапу, стоять', '2022-02-01', 1),
('Бакс', 'Сидеть, лежать', '2021-03-21', 1),
('Молли', 'Лапу, сидеть', '2012-04-11', 1),
('Бумер', 'Лежать', '2020-04-10', 1);

INSERT INTO cats (name, commands, birthday, type_id) VALUES
('Рыжик', '', '2020-01-02', 1),
('Феня', 'Мурлычь', '2020-05-21', 1),
('Барсик', '', '2015-01-10', 1);

INSERT INTO humsters (name, commands, birthday, type_id) VALUES
('Марс', '', '2018-05-25', 1),
('Элвин', '', '2022-02-01', 1);

INSERT INTO horses (name, commands, birthday, type_id) VALUES
('Сникерс', 'Рысь', '2018-05-19', 2),
('Оскар', 'Рысь', '2020-02-01', 2);

INSERT INTO donkeys (name, commands, birthday, type_id) VALUES
('Монти', '', '2018-05-19', 2),
('Отис', '', '2020-02-01', 2),
('Персик', '', '2016-01-02', 2);

INSERT INTO camels (name, commands, birthday, type_id) VALUES
('Пыжик', '', '2018-05-19', 2),
('Перчик', '', '2020-02-01', 2);

/*
10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу
 */

TRUNCATE TABLE camels;

DROP TABLE IF EXISTS donkeys_horses;
CREATE TABLE donkeys_horses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	commands VARCHAR(250) NULL,
	birthday DATE,
	type_id BIGINT UNSIGNED NOT NULL,
	last_table VARCHAR(50),
	FOREIGN KEY (type_id) REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO donkeys_horses (name, commands, birthday, type_id, last_table)
SELECT name, commands, birthday, type_id, 'donkeys' FROM donkeys;

INSERT INTO donkeys_horses (name, commands, birthday, type_id, last_table)
SELECT name, commands, birthday, type_id, 'horses' FROM horses;

DROP TABLE IF EXISTS donkeys;
DROP TABLE IF EXISTS houses;
DROP TABLE IF EXISTS camels;

/*
11. Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице
 */

DROP TABLE IF EXISTS young_animals;
CREATE TABLE young_animals (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	type_id BIGINT UNSIGNED NOT NULL,
	last_table VARCHAR(50),
	age INT NOT NULL
);

INSERT INTO young_animals (name, type_id, last_table, age)
SELECT name, type_id, 'cats', TIMESTAMPDIFF(YEAR, birthday, CURDATE()) FROM cats
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3
	UNION ALL
SELECT name, type_id, 'dogs', TIMESTAMPDIFF(YEAR, birthday, CURDATE()) FROM dogs
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3
	UNION ALL
SELECT name, type_id, 'humsters', TIMESTAMPDIFF(YEAR, birthday, CURDATE()) FROM humsters
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3
	UNION ALL
SELECT name, type_id, last_table, TIMESTAMPDIFF(YEAR, birthday, CURDATE()) FROM donkeys_horses
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3;

/*
12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам.
 */

DROP TABLE IF EXISTS all_animals;
CREATE TABLE all_animals (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	commands VARCHAR(250) NULL,
	birthday DATE,
	type_id BIGINT UNSIGNED NOT NULL,
	last_table VARCHAR(50),
	FOREIGN KEY (type_id) REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO all_animals (name, commands, birthday, type_id, last_table)
SELECT name, commands, birthday, type_id, 'cats' FROM cats
	UNION ALL
SELECT name, commands, birthday, type_id, 'dogs' FROM dogs
	UNION ALL
SELECT name, commands, birthday, type_id, 'humsters' FROM humsters
	UNION ALL
SELECT name, commands, birthday, type_id, last_table FROM donkeys_horses;

-- SELECT * FROM all_animals;
