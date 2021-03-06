package com.craftercodebase.stats.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_Location")
public class Tbl_Location {

	@Id
	private String countriesAndTerritories;
	private String location;
	private String continent;
	private int population_year;
	private long population;

	public String getCountriesAndTerritories() {
		return countriesAndTerritories;
	}

	public void setCountriesAndTerritories(String countriesAndTerritories) {
		this.countriesAndTerritories = countriesAndTerritories;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public int getPopulation_year() {
		return population_year;
	}

	public void setPopulation_year(int population_year) {
		this.population_year = population_year;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

}
