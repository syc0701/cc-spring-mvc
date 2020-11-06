package com.craftercodebase.stats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.craftercodebase.stats.model.CountryEntity;

@Repository
public interface ContryRepository extends PagingAndSortingRepository<CountryEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT distinct iso_code, location FROM tbl_covid19 WHERE location <> 'World' ORDER BY location")
	List<CountryEntity> findCountries();

	@Query(nativeQuery = true, value = "SELECT iso_code, location " +
			"FROM" +
			"    tbl_covid19 b " +
			"WHERE" +
			"    location <> 'World'" +
			"        AND reported_date = (SELECT " +
			"            MAX(reported_date)" +
			"        FROM" +
			"            tbl_covid19) " +
			"GROUP BY iso_code , location " +
			"ORDER BY iso_code DESC " +
			"LIMIT :limit")
	List<CountryEntity> findCountriesTopN(@Param("limit") int limit);

	@Query(nativeQuery = true, value = "SELECT distinct iso_code, location FROM tbl_covid19 WHERE location <> 'World' AND total_cases > 10000 ORDER BY location")
	List<CountryEntity> findCountriesTop40();

}
