package com.craftercodebase.stats.json;

import java.util.ArrayList;

public class ChartData {

	private ArrayList<Integer> totalCases = new ArrayList<Integer>();
	private ArrayList<Integer> totalDeaths = new ArrayList<Integer>();
	private ArrayList<Integer> newCases = new ArrayList<Integer>();
	private ArrayList<Integer> newDeaths = new ArrayList<Integer>();
	private ArrayList<Integer> newTests = new ArrayList<Integer>();
	private ArrayList<Object> names = new ArrayList<Object>();

	public ArrayList<Integer> getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(ArrayList<Integer> totalCases) {
		this.totalCases = totalCases;
	}

	public ArrayList<Integer> getTotalDeaths() {
		return totalDeaths;
	}

	public void setTotalDeaths(ArrayList<Integer> totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	public ArrayList<Integer> getNewCases() {
		return newCases;
	}

	public void setNewCases(ArrayList<Integer> newCases) {
		this.newCases = newCases;
	}

	public ArrayList<Integer> getNewDeaths() {
		return newDeaths;
	}

	public void setNewDeaths(ArrayList<Integer> newDeaths) {
		this.newDeaths = newDeaths;
	}

	public ArrayList<Integer> getNewTests() {
		return newTests;
	}

	public void setNewTests(ArrayList<Integer> newTests) {
		this.newTests = newTests;
	}

	public ArrayList<Object> getNames() {
		return names;
	}

	public void setNames(ArrayList<Object> name) {
		this.names = name;
	}

}
