CREATE DATABASE project DEFAULT CHARACTER SET utf8;

USE project;

CREATE TABLE user(
		id SERIAL,
		login_id varchar(255) UNIQUE NOT NULL,
		name varchar(255) NOT NULL,
		birth_date DATE NOT NULL,
		password varchar(255) NOT NULL,
		create_date DATETIME NOT NULL,
		update_date DATETIME NOT NULL
		);

INSERT INTO user
(login_id,name,birth_date,password,create_date,update_date)
VALUES
('admin','ä«óùé“','2000-09-23','password','2018-08-10 15:30:07','2018-08-23 16:01:00');
