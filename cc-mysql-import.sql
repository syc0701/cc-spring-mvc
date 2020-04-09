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