INSERT INTO Director (first_name, last_name, birth_date)
VALUES ('Kiarra', 'Deziree', '1908-01-11'),
	('Linden', 'Ewart', '1997-05-11'),
	('Serenity', 'Gennadiya', '1031-11-11'),
	('Rostislav', 'Dimitri', '1976-10-11'),
	('Evpraksiya', 'Jervis', '1988-11-12');

INSERT INTO Film (director_id, name, release_date, genre)
VALUES (1, 'Film name1',  '2019-06-23', 'genre1'),
	(2, 'Film name2',  '2019-01-11', 'genre2'),
	(1, 'Film name3',  '2018-04-23', 'genre3'),
	(3, 'Film name4',  '2011-08-16', 'genre3'),
	(2, 'Film name5',  '2019-01-11', 'genre1')