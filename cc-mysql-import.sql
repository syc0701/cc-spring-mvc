/*
 * Table schema that employees information.
 */
USE db_craftercodebase;

DROP TABLE IF EXISTS TBL_EMPLOYEES;
 
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

INSERT INTO TBL_EMPLOYEES (first_name, last_name, email) 
VALUES ('Sample 1', 'Data', 'sample1@cc.ca'),
('Sample 2', 'Data', 'sample2@cc.ca'),
('Sample 3', 'Data', 'sample3@cc.ca'),
('Sample 4', 'Data', 'sample4@cc.ca'),
('Sample 5', 'Data', 'sample5@cc.ca');


DROP TABLE IF EXISTS TBL_COVID19;
 
CREATE TABLE TBL_COVID19 (
	id int AUTO_INCREMENT primary key,
    iso_code VARCHAR(250),
    location VARCHAR(250) NOT NULL,
    reported_date DATE NOT NULL,
    total_cases INT,
    new_cases INT,
    total_deaths INT,
    new_deaths INT,
    total_cases_per_million FLOAT,
    new_cases_per_million FLOAT,
    total_deaths_per_million FLOAT,
    new_deaths_per_million FLOAT,
    total_tests INT,
    new_tests INT,
    total_tests_per_thousand FLOAT,
    new_tests_per_thousand FLOAT,
    tests_units VARCHAR(250)
);

ALTER TABLE tbl_covid19 ADD INDEX idx_iso ( iso_code );