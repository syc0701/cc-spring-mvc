package com.craftercodebase.stats.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.craftercodebase.common.exception.RecordNotFoundException;
import com.craftercodebase.stats.excel.ExcelData;
import com.craftercodebase.stats.json.CountryEntity;
import com.craftercodebase.stats.model.Covid19Entity;
import com.craftercodebase.stats.model.PieChartEntity;
import com.craftercodebase.stats.repository.ContryRepository;
import com.craftercodebase.stats.repository.Covid19Repository;
import com.craftercodebase.stats.repository.PieChartRepository;
import com.fasterxml.jackson.databind.MappingIterator;

@Service
public class Covid19Service {
	private static final Logger log = LoggerFactory.getLogger(Covid19Service.class);

	@Autowired
	Covid19Repository covidRepository;

	@Autowired
	PieChartRepository pieChartRepository;

	@Autowired
	ContryRepository contryRepository;

	public Page<Covid19Entity> searchCountries(String search, String sort, String order, int offset, int limit) {

		int pageNo = offset / limit;

		Sort.Direction direction = Sort.Direction.DESC;
		if (order.equals("asc")) {
			direction = Sort.Direction.ASC;
		}

		// Pageable paging = PageRequest.of(pageNo, limit, Sort.by(direction, sort));
		Pageable paging = PageRequest.of(pageNo, limit);

		Page<Covid19Entity> result = (Page<Covid19Entity>) covidRepository.findAll(paging, search);

		log.debug("\n[syc0701]\n number=" + result.getNumber() + ", ele=" + result.getNumberOfElements());

		/*
		 * List<Covid19Entity> list = result.getContent(); list.forEach(n ->
		 * System.out.println(n));
		 */
		return result;
	}

	public Covid19Entity getEmployeeById(Long id) throws RecordNotFoundException {
		Optional<Covid19Entity> employee = covidRepository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public List<PieChartEntity> searchCasesByIsoCode(String search) {

		List<PieChartEntity> result = null;
		result = (List<PieChartEntity>) pieChartRepository.findCasesByIsoCode(search);

		return result;
	}

	public List<PieChartEntity> searchCasesByIsoCode(List<String> iso_codes) {

		List<PieChartEntity> result = null;
		result = (List<PieChartEntity>) pieChartRepository.findCasesByIsoCodes(iso_codes);

		return result;
	}

	public List<PieChartEntity> searchCasesByDate(List<String> search, String selectedDate) {

		List<PieChartEntity> result = null;
		result = (List<PieChartEntity>) pieChartRepository.findCasesByDate(search, selectedDate);

		return result;
	}

	public List<PieChartEntity> searchCasesForMap() {

		List<PieChartEntity> result = null;
		result = (List<PieChartEntity>) pieChartRepository.findCasesForMap();

		return result;
	}

	public List<CountryEntity> searchAllCountries() {

		List<CountryEntity> result = null;
		result = (List<CountryEntity>) contryRepository.findCountries();

		return result;
	}

	public List<CountryEntity> searchCountriesTop40() {

		List<CountryEntity> result = null;
		result = (List<CountryEntity>) contryRepository.findCountriesTop40();

		return result;
	}

	public void batch(MappingIterator<ExcelData> excelData) {

		covidRepository.deleteAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Covid19Entity> entities = new ArrayList<Covid19Entity>();

		try {
			for (ExcelData d : excelData.readAll()) {
				Covid19Entity entity = new Covid19Entity();

				entity.setIso_code(d.getIso_code());   
				entity.setLocation(d.getLocation());
				entity.setReported_date(sdf.parse(d.getDate()));
				entity.setTotal_cases(d.getTotal_cases());
				entity.setNew_cases(d.getNew_cases());
				entity.setTotal_deaths(d.getTotal_deaths());
				entity.setNew_deaths(d.getNew_deaths());
				entity.setTotal_cases_per_million(d.getTotal_cases_per_million());
				entity.setNew_cases_per_million(d.getNew_cases_per_million());
				entity.setTotal_deaths_per_million(d.getTotal_deaths_per_million());
				entity.setNew_deaths_per_million(d.getNew_deaths_per_million());
				entity.setTotal_tests((int) d.getTotal_tests());
				entity.setNew_tests((int) d.getNew_tests());
				entity.setTotal_tests_per_thousand(d.getTotal_tests_per_thousand());
				entity.setNew_tests_per_thousand(d.getNew_tests_per_thousand());
				entity.setTests_units(d.getTests_units());

				entities.add(entity);
			}
		} catch (ParseException e) {
			log.error(e.getMessage(), e.getCause());
		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
		}
		covidRepository.saveAll(entities);

	}
}
