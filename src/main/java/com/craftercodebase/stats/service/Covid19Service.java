package com.craftercodebase.stats.service;

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
import com.craftercodebase.stats.excel.Exl_Covid19;
import com.craftercodebase.stats.excel.Exl_Location;
import com.craftercodebase.stats.model.CountryEntity;
import com.craftercodebase.stats.model.MapEntity;
import com.craftercodebase.stats.model.Tbl_Covid19;
import com.craftercodebase.stats.model.Tbl_Location;
import com.craftercodebase.stats.repository.ContryRepository;
import com.craftercodebase.stats.repository.Covid19Repository;
import com.craftercodebase.stats.repository.LocationRepository;
import com.craftercodebase.stats.repository.MapRepository;
import com.fasterxml.jackson.databind.MappingIterator;

@Service
public class Covid19Service {
	private static final Logger log = LoggerFactory.getLogger(Covid19Service.class);

	@Autowired
	Covid19Repository covidRepository;

	@Autowired
	ContryRepository contryRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	MapRepository mapRepository;

	public Page<Tbl_Covid19> searchCountries(String search, String sort, String order, int offset, int limit) {

		int pageNo = offset / limit;

		Sort.Direction direction = Sort.Direction.DESC;
		if (order.equals("asc")) {
			direction = Sort.Direction.ASC;
		}

		// Pageable paging = PageRequest.of(pageNo, limit, Sort.by(direction, sort));
		Pageable paging = PageRequest.of(pageNo, limit);

		Page<Tbl_Covid19> result = (Page<Tbl_Covid19>) covidRepository.findAll(paging, search);

		log.debug("\n[syc0701]\n number=" + result.getNumber() + ", ele=" + result.getNumberOfElements());

		/*
		 * List<Covid19Entity> list = result.getContent(); list.forEach(n ->
		 * System.out.println(n));
		 */
		return result;
	}

	public Tbl_Covid19 getEmployeeById(Long id) throws RecordNotFoundException {
		Optional<Tbl_Covid19> employee = covidRepository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public List<Tbl_Covid19> searchCasesByIsoCode(String search) {

		List<Tbl_Covid19> result = null;
		result = (List<Tbl_Covid19>) covidRepository.findCasesByIsoCode(search);

		return result;
	}

	public List<Tbl_Covid19> searchCasesByIsoCode(List<String> iso_codes) {

		List<Tbl_Covid19> result = null;
		result = (List<Tbl_Covid19>) covidRepository.findCasesByIsoCodes(iso_codes);

		return result;
	}

	public List<Tbl_Covid19> searchCasesByDate(List<String> search, String selectedDate) {

		List<Tbl_Covid19> result = null;
		result = (List<Tbl_Covid19>) covidRepository.findCasesByDate(search, selectedDate);

		return result;
	}

	public List<Tbl_Covid19> searchCasesForMap() {

		List<Tbl_Covid19> result = null;
		result = (List<Tbl_Covid19>) covidRepository.findCasesForMap();

		return result;
	}

	public List<MapEntity> searchCasesForMap2() {

		List<MapEntity> result = (List<MapEntity>) mapRepository.findAll();

		return result;
	}

	public List<CountryEntity> searchAllCountries() {

		List<CountryEntity> result = null;
		result = (List<CountryEntity>) contryRepository.findCountries();

		return result;
	}

	public List<CountryEntity> searchCountriesTopN(int limit) {

		List<CountryEntity> result = null;
		result = (List<CountryEntity>) contryRepository.findCountriesTopN(limit);

		return result;
	}

	public String batchCovid19(MappingIterator<Exl_Covid19> excelData) {

		String result = "Success";

		covidRepository.deleteAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Tbl_Covid19> entities = new ArrayList<Tbl_Covid19>();

		try {

			while (excelData.hasNextValue()) {
				Exl_Covid19 d = excelData.next();

				Tbl_Covid19 entity = new Tbl_Covid19();

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
			result = e.getMessage();
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			result = e.getMessage();
		}
		covidRepository.saveAll(entities);

		return result;
	}

	public String batchLocation(MappingIterator<Exl_Location> excelData) {

		String result = "Success";

		locationRepository.deleteAll();

		ArrayList<Tbl_Location> entities = new ArrayList<Tbl_Location>();

		try {

			while (excelData.hasNextValue()) {
				Exl_Location d = excelData.next();

				Tbl_Location entity = new Tbl_Location();

				entity.setCountriesAndTerritories(d.getCountriesAndTerritories());
				entity.setLocation(d.getLocation());
				entity.setContinent(d.getContinent());
				entity.setPopulation_year(d.getPopulation_year());
				entity.setPopulation(d.getPopulation());

				entities.add(entity);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			result = e.getMessage();
		}
		locationRepository.saveAll(entities);

		return result;
	}
}
