USE db_craftercodebase;

DROP TABLE IF EXISTS TBL_EMPLOYEES;
 
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

INSERT INTO TBL_EMPLOYEES (first_name, last_name, email) 
VALUES ('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('Lokesh', 'Gupta', 'howtodoinjava@gmail.com'),
  	('John', 'Doe', 'xyz@email.com');