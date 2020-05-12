package com.craftercodebase.stats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.craftercodebase.stats.json.CountryEntity;

@Repository
public interface ContryRepository extends PagingAndSortingRepository<CountryEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT distinct iso_code, location FROM tbl_covid19 WHERE location <> 'World' ORDER BY location")
	List<CountryEntity> findCountries();

	@Query(nativeQuery = true, value = "SELECT distinct iso_code, location FROM tbl_covid19 WHERE location <> 'World' AND total_cases > 10000 ORDER BY location")
	List<CountryEntity> findCountriesTop40();

}
