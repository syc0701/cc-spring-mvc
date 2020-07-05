package com.craftercodebase.stats.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
import com.craftercodebase.mvc.web.RspDataTableRow;
import com.craftercodebase.stats.excel.Exl_Covid19;
import com.craftercodebase.stats.excel.Exl_Location;
import com.craftercodebase.stats.json.ChartCTData;
import com.craftercodebase.stats.json.ChartCTData.Datas;
import com.craftercodebase.stats.json.ChartData;
import com.craftercodebase.stats.json.ChartData2;
import com.craftercodebase.stats.json.ChartDataForMap;
import com.craftercodebase.stats.json.ChartDataForMap.DataForMap;
import com.craftercodebase.stats.json.Countries;
import com.craftercodebase.stats.model.CountryEntity;
import com.craftercodebase.stats.model.MapEntity;
import com.craftercodebase.stats.model.Tbl_Covid19;
import com.craftercodebase.stats.service.Covid19Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

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

		Page<Tbl_Covid19> result = service.searchCountries(search, sort, order, offset, limit);

		RspDataTable rsp = new RspDataTable();

		int startNo = (40 * result.getNumber()) + 1;

		rsp.setStartNo(startNo);
		rsp.setTotalNotFiltered(result.getTotalElements());
		rsp.setTotal(result.getTotalElements());

		ArrayList<RspDataTableRow> rows = new ArrayList<RspDataTableRow>();
		for (Tbl_Covid19 entity : result) {

			RspDataTableRow row = new RspDataTableRow();
			row.setRanking(startNo++);
			row.setIso_code(entity.getIso_code());
			row.setLocation(entity.getLocation());
			row.setReported_date(entity.getReported_date());
			row.setTotal_cases(entity.getTotal_cases());
			row.setNew_cases(entity.getNew_cases());
			row.setNew_deaths(entity.getNew_deaths());
			row.setTotal_deaths(entity.getTotal_deaths());

			rows.add(row);
		}

		rsp.setRows(rows);
		// rsp.setRows(result.getContent());

		rsp.setSortOrder(order);

		return rsp;
	}

	@GetMapping(value = "/casesByCountry")
	public String showPieChart() {
		return "casesByCountry.html";
	}

	@GetMapping(value = "/getCountries/{limit}")
	public @ResponseBody Countries getCountries(Model model, @PathVariable("limit") String limit) {

		Countries country = new Countries();

		ArrayList<String> iso_code = new ArrayList<String>();
		ArrayList<String> location = new ArrayList<String>();
		ArrayList<Boolean> included = new ArrayList<Boolean>();

		country.setIso_code(iso_code);
		country.setLocation(location);
		country.setIncluded(included);

		List<CountryEntity> allCountries = service.searchAllCountries();
		List<CountryEntity> limitedCountries = service.searchCountriesTopN(Integer.parseInt(limit));

		allCountries.forEach(a -> {
			iso_code.add(a.getIso_code());
			location.add(a.getLocation());

			if (limit.equals("ALL")) {
				included.add(false);
			} else {

				boolean isExist = false;
				
				for (CountryEntity b : limitedCountries) {
					if (a.getIso_code().equals(b.getIso_code())) {
						isExist = true;
						break;
					}
				}

				if (isExist) {
					included.add(true);
				} else {
					included.add(false);
				}
			}
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
		List<Tbl_Covid19> resultSet = service.searchCasesByIsoCode(search);

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

	@RequestMapping(path = "/getCumulativeTrendData/{chkBoxVal}")
	public @ResponseBody ChartCTData getCumulativeTrendData(Model model, @PathVariable("chkBoxVal") int chkBoxVal) {

		List<CountryEntity> list20 = service.searchCountriesTopN(20);

		StringBuffer buffer = new StringBuffer();
		boolean first = true;

		for (CountryEntity entity : list20) {
			if (!first) {
				buffer.append(",");
			}
			buffer.append(entity.getIso_code());
			first = false;
		}

		return getCumulativeTrendData(model, buffer.toString(), chkBoxVal);
	}

	@RequestMapping(path = "/getCumulativeTrendData/{iso_codes}/{chkBoxVal}")
	public @ResponseBody ChartCTData getCumulativeTrendData(Model model, @PathVariable("iso_codes") String iso_codes,
			@PathVariable("chkBoxVal") int chkBoxVal) {

		ChartCTData cdata = new ChartCTData();

		log.debug("\n[syc0701]\n iso_codes=" + iso_codes);

		// setting
		List<String> list_iso_codes = (List<String>) Arrays.asList(iso_codes.split(","));
		List<Tbl_Covid19> resultSet = service.searchCasesByIsoCode(list_iso_codes);

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

		// cdata.setLegends(arr_legends);

		Collections.sort(arr_data);

		ArrayList<String> tt = new ArrayList<String>();
		for (Datas a : arr_data) {
			tt.add(a.getLocation());
		}
		cdata.setLegends(tt);

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

		log.debug("\n[syc0701]\n" + listofOptions);

		List<Tbl_Covid19> resultSet = service.searchCasesByDate(listofOptions, selectedDate.get());

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
	public @ResponseBody ChartDataForMap getMapData(Model model) {
		ChartDataForMap cData = new ChartDataForMap();

		ArrayList<String> c = new ArrayList<String>();
		c.add("Country");
		c.add("Total Cases");
		c.add("Total Deaths");

		cData.setLabels(c);

		List<DataForMap> countries = new ArrayList<DataForMap>();

		List<Tbl_Covid19> resultSet = service.searchCasesForMap();
		resultSet.forEach(row -> {
			DataForMap d1 = cData.new DataForMap();
			d1.setCountryName(row.getLocation());
			d1.setValues(row.getTotal_cases(), row.getTotal_deaths());

			countries.add(d1);
		});

		cData.setCountries(countries);

		return cData;
	}

	@RequestMapping(path = "/getMapData2")
	public @ResponseBody ChartDataForMap getMapData2(Model model) {
		ChartDataForMap cData = new ChartDataForMap();

		ArrayList<String> c = new ArrayList<String>();
		c.add("Country");
		c.add("Population");
		c.add("Total Cases");
		c.add("Total Deaths");

		cData.setLabels(c);

		List<DataForMap> countries = new ArrayList<DataForMap>();
 
		List<MapEntity> resultSet = service.searchCasesForMap2();
		resultSet.forEach(row -> {
			DataForMap d1 = cData.new DataForMap();
			
			d1.setCountryName(row.getLocation());
			d1.setValues(row.getPopulation(), row.getTotal_cases(), row.getTotal_deaths());

			countries.add(d1);
		});

		cData.setCountries(countries);

		return cData;
	}

	@GetMapping(value = "/showBatch")
	public String showBatch() {
		return "batch";
	}

	@GetMapping("/batchCovid19")
	public @ResponseBody String batchCovid19() {
		long processingTime = 0L;
		String result = null;
		try {
			CsvMapper mapper = new CsvMapper();
			mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
			CsvSchema schema = CsvSchema.builder()
					.addColumn("iso_code", CsvSchema.ColumnType.STRING)
					.addColumn("location", CsvSchema.ColumnType.STRING)
					.addColumn("date", CsvSchema.ColumnType.STRING)
					.addColumn("total_cases", CsvSchema.ColumnType.NUMBER)
					.addColumn("new_cases", CsvSchema.ColumnType.NUMBER)
					.addColumn("total_deaths", CsvSchema.ColumnType.NUMBER)
					.addColumn("new_deaths", CsvSchema.ColumnType.NUMBER)
					.addColumn("total_cases_per_million", CsvSchema.ColumnType.NUMBER)
					.addColumn("new_cases_per_million", CsvSchema.ColumnType.NUMBER)
					.addColumn("total_deaths_per_million", CsvSchema.ColumnType.NUMBER)
					.addColumn("new_deaths_per_million", CsvSchema.ColumnType.NUMBER)
					.addColumn("total_tests", CsvSchema.ColumnType.NUMBER)
					.addColumn("new_tests", CsvSchema.ColumnType.NUMBER)
					.addColumn("total_tests_per_thousand", CsvSchema.ColumnType.NUMBER)
					.addColumn("new_tests_per_thousand", CsvSchema.ColumnType.NUMBER)
					.addColumn("new_tests_smoothed", CsvSchema.ColumnType.NUMBER)
					.addColumn("new_tests_smoothed_per_thousand", CsvSchema.ColumnType.NUMBER)
					.addColumn("tests_units", CsvSchema.ColumnType.STRING)
					.addColumn("stringency_index", CsvSchema.ColumnType.NUMBER)
					.addColumn("population", CsvSchema.ColumnType.NUMBER)
					.addColumn("population_density", CsvSchema.ColumnType.NUMBER)
					.addColumn("median_age", CsvSchema.ColumnType.NUMBER)
					.addColumn("aged_65_older", CsvSchema.ColumnType.NUMBER)
					.addColumn("aged_70_older", CsvSchema.ColumnType.NUMBER)
					.addColumn("gdp_per_capita", CsvSchema.ColumnType.NUMBER)
					.addColumn("extreme_poverty", CsvSchema.ColumnType.NUMBER)
					.addColumn("cvd_death_rate", CsvSchema.ColumnType.NUMBER)
					.addColumn("diabetes_prevalence", CsvSchema.ColumnType.NUMBER)
					.addColumn("female_smokers", CsvSchema.ColumnType.NUMBER)
					.addColumn("male_smokers", CsvSchema.ColumnType.NUMBER)
					.addColumn("handwashing_facilities", CsvSchema.ColumnType.NUMBER)
					.addColumn("hospital_beds_per_100k", CsvSchema.ColumnType.NUMBER)
					.build().withHeader();

			File csvFile = new File("D:\\canada\\covid-19-data\\public\\data\\owid-covid-data.csv");
			long start = new Date().getTime();

			MappingIterator<Exl_Covid19> excelData = mapper.readerFor(Exl_Covid19.class).with(schema).readValues(csvFile);
			result = service.batchCovid19(excelData);

			processingTime = new Date().getTime() - start;
			log.debug("processingTime=" + processingTime);

		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
		}

		return result;
	}
	
	@GetMapping("/batchLocationData")
	public @ResponseBody String batchLocationData() {
		long processingTime = 0L;
		String result = null;
		try {
			CsvMapper mapper = new CsvMapper();
			mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
			CsvSchema schema = CsvSchema.builder()
					.addColumn("countriesAndTerritories", CsvSchema.ColumnType.STRING)
					.addColumn("location", CsvSchema.ColumnType.STRING)
					.addColumn("continent", CsvSchema.ColumnType.STRING)
					.addColumn("population_year", CsvSchema.ColumnType.NUMBER)
					.addColumn("population", CsvSchema.ColumnType.NUMBER)
					.build().withHeader();

			File csvFile = new File("D:\\canada\\covid-19-data\\public\\data\\ecdc\\locations.csv");
			long start = new Date().getTime();

			MappingIterator<Exl_Location> excelData = mapper.readerFor(Exl_Location.class).with(schema).readValues(csvFile);
			result = service.batchLocation(excelData);

			processingTime = new Date().getTime() - start;
			log.debug("processingTime=" + processingTime);

		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
		}

		return result;
	}
	
	@GetMapping("/pupulation")
	public @ResponseBody String getPupulation() {
		
		return null;
	}

}
