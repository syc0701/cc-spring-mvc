package com.craftercodebase.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.craftercodebase.stats.excel.Exl_Covid19;
import com.craftercodebase.stats.web.Covid19Controller;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Controller
@RequestMapping("/test")
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(Covid19Controller.class);

	@GetMapping("/case1")
	public @ResponseBody String testCase1() {
		log.debug("[syc0701] " + "Hello");

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
					.addColumn("tests_units", CsvSchema.ColumnType.STRING)
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

			File csvFile = new File("D:\\canada\\cc-ref\\covid-19-data\\public\\data\\owid-covid-data.csv");
			long start = new Date().getTime();

			MappingIterator<Exl_Covid19> it = mapper.readerFor(Exl_Covid19.class).with(schema).readValues(csvFile);

			long a = 0L;
			for (Exl_Covid19 e : it.readAll()) {
				a++;
			}

			long end = new Date().getTime();

			log.debug("\n[syc0701]\n" + a);
			System.out.println("**** " + (end - start) + " ms");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Test";
	}

}
