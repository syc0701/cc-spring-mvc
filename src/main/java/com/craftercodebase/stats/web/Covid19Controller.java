package com.craftercodebase.stats.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.craftercodebase.mvc.web.RspDataTable;
import com.craftercodebase.stats.json.ChartCTData;
import com.craftercodebase.stats.json.ChartCTData.Datas;
import com.craftercodebase.stats.json.ChartData;
import com.craftercodebase.stats.json.ChartData2;
import com.craftercodebase.stats.json.ChatDataForMap;
import com.craftercodebase.stats.json.ChatDataForMap.DataForMap;
import com.craftercodebase.stats.json.Countries;
import com.craftercodebase.stats.json.CountryEntity;
import com.craftercodebase.stats.model.Covid19Entity;
import com.craftercodebase.stats.model.PieChartEntity;
import com.craftercodebase.stats.service.Covid19Service;

@Controller
@RequestMapping("/stats")
public class Covid19Controller {

	private static final Logger log = LoggerFactory.getLogger(Covid19Controller.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");

	@Autowired
	Covid19Service service;

	@GetMapping(value = "/globalTrend")
	public String showDataTables() {
		return "globalTrend";
	}

	@GetMapping(value = "/search")
	public @ResponseBody RspDataTable searchEmployees(@RequestParam String search,
			@RequestParam(required = false, defaultValue = "total_cases") String sort,
			@RequestParam(required = false, defaultValue = "desc") String order, @RequestParam int offset, @RequestParam int limit) {

		Page<Covid19Entity> result = service.searchCountries(search, sort, order, offset, limit);

		RspDataTable entity = new RspDataTable();
		entity.setTotalNotFiltered(result.getTotalElements());
		entity.setTotal(result.getTotalElements());
		entity.setRows(result.getContent());
		entity.setSortOrder(order);

		return entity;
	}

	@GetMapping(value = "/casesByCountry")
	public String showPieChart() {
		return "casesByCountry.html";
	}

	@GetMapping(value = "/getAllCountries")
	public @ResponseBody Countries getAllCountries() {

		Countries country = new Countries();

		ArrayList<String> iso_code = new ArrayList<String>();
		ArrayList<String> location = new ArrayList<String>();

		country.setIso_code(iso_code);
		country.setLocation(location);

		List<CountryEntity> aa = service.searchAllCountries();
		aa.forEach(a -> {
			iso_code.add(a.getIso_code());
			location.add(a.getLocation());
		});

		return country;
	}

	@GetMapping(value = "/getCountriesTop40")
	public @ResponseBody Countries getCountriesTop40() {

		Countries country = new Countries();

		ArrayList<String> iso_code = new ArrayList<String>();
		ArrayList<String> location = new ArrayList<String>();

		country.setIso_code(iso_code);
		country.setLocation(location);

		List<CountryEntity> resultSet = service.searchCountriesTop40();
		resultSet.forEach(row -> {
			iso_code.add(row.getIso_code());
			location.add(row.getLocation());
		});

		return country;
	}

	@RequestMapping(path = "/getCases/{iso_code}")
	public @ResponseBody ChartData getCases(Model model, @PathVariable("iso_code") String iso_code) {

		ChartData cdata = new ChartData();

		ArrayList<Integer> totalCases = new ArrayList<Integer>();
		ArrayList<Integer> totalDeaths = new ArrayList<Integer>();
		ArrayList<Integer> newCases = new ArrayList<Integer>();
		ArrayList<Integer> newDeaths = new ArrayList<Integer>();
		ArrayList<Integer> newTests = new ArrayList<Integer>();
		ArrayList<Object> theDate = new ArrayList<Object>();

		cdata.setTotalCases(totalCases);
		cdata.setTotalDeaths(totalDeaths);
		cdata.setNewCases(newCases);
		cdata.setNewDeaths(newDeaths);
		cdata.setNewTests(newTests);
		cdata.setNames(theDate);

		// setting
		String search = iso_code;
		List<PieChartEntity> resultSet = service.searchCasesByIsoCode(search);

		resultSet.forEach(a -> {
			totalCases.add(a.getTotal_cases());
			totalDeaths.add(a.getTotal_deaths());
			newCases.add(a.getNew_cases());
			newDeaths.add(a.getNew_deaths());
			newTests.add(a.getNew_tests());

			theDate.add(sdf.format(a.getReported_date()));
		});

		return cdata;
	}

	@GetMapping(value = "/cumulativeTrend")
	public String showCumulativeTrend() {
		return "cumulativeTrend";
	}

	@RequestMapping(path = "/getCumulativeTrendData/{iso_codes}/{chkBoxVal}")
	public @ResponseBody ChartCTData getCumulativeTrendData(Model model, @PathVariable("iso_codes") String iso_codes,
			@PathVariable("chkBoxVal") int chkBoxVal) {

		ChartCTData cdata = new ChartCTData();

		// setting
		List<String> list_iso_codes = (List<String>) Arrays.asList(iso_codes.split(","));
		List<PieChartEntity> resultSet = service.searchCasesByIsoCode(list_iso_codes);

		ArrayList<String> arr_legends = new ArrayList<String>();
		ArrayList<String> arr_reported_date = new ArrayList<String>(); // reported_date,
		ArrayList<ChartCTData.Datas> arr_data = new ArrayList<ChartCTData.Datas>();

		resultSet.forEach(row -> {
			if (!arr_legends.contains(row.getLocation())) {
				arr_legends.add(row.getLocation());

				ChartCTData.Datas dataRow = cdata.new Datas();
				dataRow.setLocation(row.getLocation());

				arr_data.add(dataRow);
			}

			String tempDate = sdf.format(row.getReported_date());
			if (!arr_reported_date.contains(tempDate)) {
				arr_reported_date.add(tempDate);
			}

			for (int i = 0; i < arr_data.size(); i++) {
				Datas data = (Datas) arr_data.get(i);
				if (data.getLocation().equals(row.getLocation())) {

					switch (chkBoxVal) {
					case 1:
						data.addEntity(row.getTotal_cases());
						break;
					case 2:
						data.addEntity(row.getTotal_deaths());
						break;
					case 3:
						data.addEntity(row.getNew_cases());
						break;
					case 4:
						data.addEntity(row.getNew_deaths());
						break;
					case 5:
						data.addEntity(row.getNew_tests());
						break;
					}
				}
			}
		});

		cdata.setLegends(arr_legends);
		cdata.setReported_date(arr_reported_date);
		cdata.setDatas(arr_data);

		return cdata;
	}

	@GetMapping(value = "/casesByDay")
	public String showCasesByDay() {
		return "casesByDay";
	}

	@RequestMapping(path = "/getDataByDate/{iso_codes}/{selectedDate}")
	public @ResponseBody ChartData2 getDataByDate(Model model, @PathVariable("iso_codes") String iso_codes,
			@PathVariable("selectedDate") Optional<String> selectedDate) {

		ChartData2 cdata = new ChartData2();

		// setting
		List<String> listofOptions = (List<String>) Arrays.asList(iso_codes.split(","));
		List<PieChartEntity> resultSet = service.searchCasesByDate(listofOptions, selectedDate.get());

		ArrayList<ChartData2.Datas3> arr_datas = new ArrayList<ChartData2.Datas3>();
		ArrayList<String> arr_countries = new ArrayList<String>();

		// for data
		ChartData2.Datas3 data_1 = cdata.new Datas3(ChartData2.TOTAL_CASES);
		ChartData2.Datas3 data_2 = cdata.new Datas3(ChartData2.TOTAL_DEATHS);
		ChartData2.Datas3 data_3 = cdata.new Datas3(ChartData2.NEW_CASES);
		ChartData2.Datas3 data_4 = cdata.new Datas3(ChartData2.NEW_DEATHS);

		ArrayList<Integer> data_1_1 = new ArrayList<Integer>();
		ArrayList<Integer> data_2_1 = new ArrayList<Integer>();
		ArrayList<Integer> data_3_1 = new ArrayList<Integer>();
		ArrayList<Integer> data_4_1 = new ArrayList<Integer>();

		resultSet.forEach(row -> {
			arr_countries.add(row.getLocation());

			data_1_1.add(row.getTotal_cases());
			data_2_1.add(row.getTotal_deaths());
			data_3_1.add(row.getNew_cases());
			data_4_1.add(row.getNew_deaths());
		});

		data_1.setEntity(data_1_1);
		data_2.setEntity(data_2_1);
		data_3.setEntity(data_3_1);
		data_4.setEntity(data_4_1);

		arr_datas.add(data_1);
		arr_datas.add(data_2);
		arr_datas.add(data_3);
		arr_datas.add(data_4);

		cdata.setDatas(arr_datas);
		cdata.setCountries(arr_countries);

		return cdata;
	}

	@GetMapping(value = "/onTheMap")
	public String showMap() {
		return "onTheMap";
	}

	@RequestMapping(path = "/getMapData")
	public @ResponseBody ChatDataForMap getMapData(Model model) {
		ChatDataForMap cData = new ChatDataForMap();

		
		
		
		ArrayList<String> c = new ArrayList<String>();
		c.add("Country");
		c.add("Total Cases");
		c.add("Total Deaths");

		cData.setLabels(c);

		List<DataForMap> countries = new ArrayList<DataForMap>();

		/*
		DataForMap d1 = cData.new DataForMap();
		d1.setCountryName("Germany");
		d1.setValues(10, 20);

		DataForMap d2 = cData.new DataForMap();
		d2.setCountryName("Brazil");
		d2.setValues(120, 20);
		*/
		
		List<PieChartEntity> resultSet = service.searchCasesForMap();
		resultSet.forEach(row -> {
			DataForMap d1 = cData.new DataForMap();
			d1.setCountryName(row.getLocation());
			d1.setValues(row.getTotal_cases(), row.getTotal_deaths());
			
			
			countries.add(d1);
		});
		
		

		cData.setCountries(countries);

		return cData;
	}
 
}

