package com.craftercodebase.stats.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartDataForMap {

	private ArrayList<String> labels = new ArrayList<String>();
	private List<DataForMap> countries = new ArrayList<DataForMap>();

	public ArrayList<String> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}

	public List<DataForMap> getCountries() {
		return countries;
	}

	public void setCountries(List<DataForMap> countries) {
		this.countries = countries;
	}

	public class DataForMap {
		String countryName;
		List<Integer> values;

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public List<Integer> getValues() {
			return values;
		}

		public void setValues(Integer... values) {
			this.values = Arrays.asList(values);
		}

	}
}
