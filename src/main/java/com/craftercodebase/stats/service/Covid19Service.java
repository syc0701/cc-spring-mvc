package com.craftercodebase.stats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.craftercodebase.common.exception.RecordNotFoundException;
import com.craftercodebase.stats.json.CountryEntity;
import com.craftercodebase.stats.model.Covid19Entity;
import com.craftercodebase.stats.model.PieChartEntity;
import com.craftercodebase.stats.repository.ContryRepository;
import com.craftercodebase.stats.repository.Covid19Repository;
import com.craftercodebase.stats.repository.PieChartRepository;

@Service
public class Covid19Service {

	@Autowired
	Covid19Repository repository;

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

		Page<Covid19Entity> result = null;
		result = (Page<Covid19Entity>) repository.findAll(paging, search);

		List<Covid19Entity> list = result.getContent();

		list.forEach(n -> System.out.println(n));

		return result;
	}

	public Covid19Entity getEmployeeById(Long id) throws RecordNotFoundException {
		Optional<Covid19Entity> employee = repository.findById(id);

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
}
