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
public class Covid19Data {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "iso_code", nullable = false)
	private String iso_code;

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

	@Column(name = "tests_units")
	private String tests_units;

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

	public String getTests_units() {
		return tests_units;
	}

	public void setTests_units(String tests_units) {
		this.tests_units = tests_units;
	}

	@Override
	public String toString() {
		return "Covid19Entity [id=" + id + ", iso_code=" + iso_code + ", location=" + location + ", reported_date="
				+ reported_date + ", total_cases=" + total_cases + ", new_cases=" + new_cases + ", total_deaths=" + total_deaths
				+ ", new_deaths=" + new_deaths + ", total_cases_per_million=" + total_cases_per_million + ", new_cases_per_million="
				+ new_cases_per_million + ", total_deaths_per_million=" + total_deaths_per_million + ", new_deaths_per_million="
				+ new_deaths_per_million + ", total_tests=" + total_tests + ", new_tests=" + new_tests + ", total_tests_per_thousand="
				+ total_tests_per_thousand + ", new_tests_per_thousand=" + new_tests_per_thousand + ", tests_units=" + tests_units + "]";
	}

}