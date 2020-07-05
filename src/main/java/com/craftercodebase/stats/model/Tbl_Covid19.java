package com.craftercodebase.stats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_COVID19", indexes = { @Index(columnList = "iso_code"), @Index(columnList = "location") })
public class Tbl_Covid19 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "iso_code", nullable = false)
	private String iso_code;

	@Column(name = "continent", nullable = false)
	private String continent;

	@Column(name = "location")
	private String location;

	@Column(name = "reported_date", nullable = false)
	private Date reported_date;

	@Column(name = "total_cases")
	private int total_cases;

	@Column(name = "new_cases")
	private int new_cases;

	@Column(name = "total_deaths")
	private int total_deaths;

	@Column(name = "new_deaths")
	private int new_deaths;

	@Column(name = "total_cases_per_million")
	private float total_cases_per_million;

	@Column(name = "new_cases_per_million")
	private float new_cases_per_million;

	@Column(name = "total_deaths_per_million")
	private float total_deaths_per_million;

	@Column(name = "new_deaths_per_million")
	private float new_deaths_per_million;

	@Column(name = "total_tests")
	private int total_tests;

	@Column(name = "new_tests")
	private int new_tests;

	@Column(name = "total_tests_per_thousand")
	private float total_tests_per_thousand;

	@Column(name = "new_tests_per_thousand")
	private float new_tests_per_thousand;

	@Column(name = "new_tests_smoothed")
	private float new_tests_smoothed;

	@Column(name = "new_tests_smoothed_per_thousand")
	private float new_tests_smoothed_per_thousand;

	@Column(name = "tests_units")
	private String tests_units;

	@Column(name = "stringency_index")
	private float stringency_index;

	@Column(name = "population")
	private int population;

	@Column(name = "population_density")
	private float population_density;

	@Column(name = "median_age")
	private float median_age;

	@Column(name = "aged_65_older")
	private float aged_65_older;

	@Column(name = "aged_70_older")
	private float aged_70_older;

	@Column(name = "gdp_per_capita")
	private float gdp_per_capita;

	@Column(name = "extreme_poverty")
	private float extreme_poverty;

	@Column(name = "cvd_death_rate")
	private float cvd_death_rate;

	@Column(name = "diabetes_prevalence")
	private float diabetes_prevalence;

	@Column(name = "female_smokers")
	private float female_smokers;

	@Column(name = "male_smokers")
	private float male_smokers;

	@Column(name = "handwashing_facilities")
	private float handwashing_facilities;

	@Column(name = "hospital_beds_per_thousand")
	private float hospital_beds_per_thousand;

	@Column(name = "life_expectancy")
	private float life_expectancy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIso_code() {
		return iso_code;
	}

	public void setIso_code(String iso_code) {
		this.iso_code = iso_code;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getReported_date() {
		return reported_date;
	}

	public void setReported_date(Date reported_date) {
		this.reported_date = reported_date;
	}

	public int getTotal_cases() {
		return total_cases;
	}

	public void setTotal_cases(int total_cases) {
		this.total_cases = total_cases;
	}

	public int getNew_cases() {
		return new_cases;
	}

	public void setNew_cases(int new_cases) {
		this.new_cases = new_cases;
	}

	public int getTotal_deaths() {
		return total_deaths;
	}

	public void setTotal_deaths(int total_deaths) {
		this.total_deaths = total_deaths;
	}

	public int getNew_deaths() {
		return new_deaths;
	}

	public void setNew_deaths(int new_deaths) {
		this.new_deaths = new_deaths;
	}

	public float getTotal_cases_per_million() {
		return total_cases_per_million;
	}

	public void setTotal_cases_per_million(float total_cases_per_million) {
		this.total_cases_per_million = total_cases_per_million;
	}

	public float getNew_cases_per_million() {
		return new_cases_per_million;
	}

	public void setNew_cases_per_million(float new_cases_per_million) {
		this.new_cases_per_million = new_cases_per_million;
	}

	public float getTotal_deaths_per_million() {
		return total_deaths_per_million;
	}

	public void setTotal_deaths_per_million(float total_deaths_per_million) {
		this.total_deaths_per_million = total_deaths_per_million;
	}

	public float getNew_deaths_per_million() {
		return new_deaths_per_million;
	}

	public void setNew_deaths_per_million(float new_deaths_per_million) {
		this.new_deaths_per_million = new_deaths_per_million;
	}

	public int getTotal_tests() {
		return total_tests;
	}

	public void setTotal_tests(int total_tests) {
		this.total_tests = total_tests;
	}

	public int getNew_tests() {
		return new_tests;
	}

	public void setNew_tests(int new_tests) {
		this.new_tests = new_tests;
	}

	public float getTotal_tests_per_thousand() {
		return total_tests_per_thousand;
	}

	public void setTotal_tests_per_thousand(float total_tests_per_thousand) {
		this.total_tests_per_thousand = total_tests_per_thousand;
	}

	public float getNew_tests_per_thousand() {
		return new_tests_per_thousand;
	}

	public void setNew_tests_per_thousand(float new_tests_per_thousand) {
		this.new_tests_per_thousand = new_tests_per_thousand;
	}

	public float getNew_tests_smoothed() {
		return new_tests_smoothed;
	}

	public void setNew_tests_smoothed(float new_tests_smoothed) {
		this.new_tests_smoothed = new_tests_smoothed;
	}

	public float getNew_tests_smoothed_per_thousand() {
		return new_tests_smoothed_per_thousand;
	}

	public void setNew_tests_smoothed_per_thousand(float new_tests_smoothed_per_thousand) {
		this.new_tests_smoothed_per_thousand = new_tests_smoothed_per_thousand;
	}

	public String getTests_units() {
		return tests_units;
	}

	public void setTests_units(String tests_units) {
		this.tests_units = tests_units;
	}

	public float getStringency_index() {
		return stringency_index;
	}

	public void setStringency_index(float stringency_index) {
		this.stringency_index = stringency_index;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public float getPopulation_density() {
		return population_density;
	}

	public void setPopulation_density(float population_density) {
		this.population_density = population_density;
	}

	public float getMedian_age() {
		return median_age;
	}

	public void setMedian_age(float median_age) {
		this.median_age = median_age;
	}

	public float getAged_65_older() {
		return aged_65_older;
	}

	public void setAged_65_older(float aged_65_older) {
		this.aged_65_older = aged_65_older;
	}

	public float getAged_70_older() {
		return aged_70_older;
	}

	public void setAged_70_older(float aged_70_older) {
		this.aged_70_older = aged_70_older;
	}

	public float getGdp_per_capita() {
		return gdp_per_capita;
	}

	public void setGdp_per_capita(float gdp_per_capita) {
		this.gdp_per_capita = gdp_per_capita;
	}

	public float getExtreme_poverty() {
		return extreme_poverty;
	}

	public void setExtreme_poverty(float extreme_poverty) {
		this.extreme_poverty = extreme_poverty;
	}

	public float getCvd_death_rate() {
		return cvd_death_rate;
	}

	public void setCvd_death_rate(float cvd_death_rate) {
		this.cvd_death_rate = cvd_death_rate;
	}

	public float getDiabetes_prevalence() {
		return diabetes_prevalence;
	}

	public void setDiabetes_prevalence(float diabetes_prevalence) {
		this.diabetes_prevalence = diabetes_prevalence;
	}

	public float getFemale_smokers() {
		return female_smokers;
	}

	public void setFemale_smokers(float female_smokers) {
		this.female_smokers = female_smokers;
	}

	public float getMale_smokers() {
		return male_smokers;
	}

	public void setMale_smokers(float male_smokers) {
		this.male_smokers = male_smokers;
	}

	public float getHandwashing_facilities() {
		return handwashing_facilities;
	}

	public void setHandwashing_facilities(float handwashing_facilities) {
		this.handwashing_facilities = handwashing_facilities;
	}

	public float getHospital_beds_per_thousand() {
		return hospital_beds_per_thousand;
	}

	public void setHospital_beds_per_thousand(float hospital_beds_per_thousand) {
		this.hospital_beds_per_thousand = hospital_beds_per_thousand;
	}

	public float getLife_expectancy() {
		return life_expectancy;
	}

	public void setLife_expectancy(float life_expectancy) {
		this.life_expectancy = life_expectancy;
	}

	@Override
	public String toString() {
		return "Tbl_Covid19 [id=" + id + ", iso_code=" + iso_code + ", continent=" + continent + ", location=" + location
				+ ", reported_date=" + reported_date + ", total_cases=" + total_cases + ", new_cases=" + new_cases + ", total_deaths="
				+ total_deaths + ", new_deaths=" + new_deaths + ", total_cases_per_million=" + total_cases_per_million
				+ ", new_cases_per_million=" + new_cases_per_million + ", total_deaths_per_million=" + total_deaths_per_million
				+ ", new_deaths_per_million=" + new_deaths_per_million + ", total_tests=" + total_tests + ", new_tests=" + new_tests
				+ ", total_tests_per_thousand=" + total_tests_per_thousand + ", new_tests_per_thousand=" + new_tests_per_thousand
				+ ", new_tests_smoothed=" + new_tests_smoothed + ", new_tests_smoothed_per_thousand=" + new_tests_smoothed_per_thousand
				+ ", tests_units=" + tests_units + ", stringency_index=" + stringency_index + ", population=" + population
				+ ", population_density=" + population_density + ", median_age=" + median_age + ", aged_65_older=" + aged_65_older
				+ ", aged_70_older=" + aged_70_older + ", gdp_per_capita=" + gdp_per_capita + ", extreme_poverty=" + extreme_poverty
				+ ", cvd_death_rate=" + cvd_death_rate + ", diabetes_prevalence=" + diabetes_prevalence + ", female_smokers="
				+ female_smokers + ", male_smokers=" + male_smokers + ", handwashing_facilities=" + handwashing_facilities
				+ ", hospital_beds_per_thousand=" + hospital_beds_per_thousand + ", life_expectancy=" + life_expectancy + "]";
	}

}