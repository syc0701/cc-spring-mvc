# Create some COVID-19 charts

Some charts developed with COVID-19 data published on [Ourworldindata] (https://ourworldindata.org/coronavirus-source-data) have been implemented.
 

## How to import a csv file into MySQL

Turn on local_infile

>$ mysql -h 172.16.1.10 --local-infile=1 -u root -p

>mysql> show variables like "local_infile";
>mysql> SET GLOBAL local_infile=1;
>mysql> quit

Log in again.

>$ mysql --local-infile=1 -u root -p
>mysql> SET GLOBAL local_infile=1;

~~~
USE db_craftercodebase;
DELETE FROM TBL_COVID19;

LOAD DATA LOCAL INFILE 'D:\\canada\\covid-19-data\\public\\data\\owid-covid-data.csv' 
INTO TABLE TBL_COVID19 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(
   iso_code,
	continent,
	location,
	reported_date,
	total_cases,
	new_cases,
	total_deaths,
	new_deaths,
	total_cases_per_million,
	new_cases_per_million,
	total_deaths_per_million,
	new_deaths_per_million,
	total_tests,
	new_tests,
	total_tests_per_thousand,
	new_tests_per_thousand,
	new_tests_smoothed,
	new_tests_smoothed_per_thousand,
	tests_units,
	stringency_index,
	population,
	population_density,
	median_age,
	aged_65_older,
	aged_70_older,
	gdp_per_capita,
	extreme_poverty,
	cvd_death_rate,
	diabetes_prevalence,
	female_smokers,
	male_smokers,
	handwashing_facilities,
	hospital_beds_per_thousand,
	life_expectancy
);
~~~


~~~
USE db_craftercodebase;
DELETE FROM TBL_LOCATION;

LOAD DATA LOCAL INFILE 'D:\\canada\\covid-19-data\\public\\data\\ecdc\\locations.csv' 
INTO TABLE TBL_LOCATION 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(
    countries_and_territories,
    location,
    continent,
    population_year,
    population    
);
~~~

Restart the server to see if the chart is visible.