package com.craftercodebase.stats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.craftercodebase.stats.model.PieChartEntity;

@Repository
public interface PieChartRepository extends PagingAndSortingRepository<PieChartEntity, Long> {
	
	
	@Query(nativeQuery = true, value="SELECT id, iso_code, location, total_cases, total_deaths, new_cases, new_deaths, new_tests, reported_date " + 
			"FROM " + 
			"    tbl_covid19 " + 
			"WHERE" + 
			"    iso_code = :iso_code " + 
			"ORDER BY reported_date")
	List<PieChartEntity> findCasesByIsoCode(@Param("iso_code") String search);
	
	
	@Query(nativeQuery = true, value="SELECT id, iso_code, location, total_cases, total_deaths, new_cases, new_deaths, new_tests, reported_date " + 
			"FROM " + 
			"    tbl_covid19 " + 
			"WHERE" + 
			"    iso_code in (:iso_codes) " + 
			"ORDER BY iso_code, reported_date")
	List<PieChartEntity> findCasesByIsoCodes(@Param("iso_codes") List<String> iso_codes);
	
	
	@Query(nativeQuery = true, value="SELECT id, iso_code, location, total_cases, total_deaths, new_cases, new_deaths, new_tests, reported_date " + 
			"FROM " + 
			"    tbl_covid19 " + 
			"WHERE" + 
			"    iso_code in (:location) " + 
			"AND date_format(reported_date, '%Y-%m-%d') = :selectedDate " +
			"ORDER BY location, reported_date")
	List<PieChartEntity> findCasesByDate(@Param("location") List<String> search, String selectedDate);
	
	
	@Query(nativeQuery = true, value="SELECT id, iso_code, location, total_cases, total_deaths, new_cases, new_deaths, new_tests, reported_date " + 
			"FROM " + 
			"    tbl_covid19 " + 
			"WHERE date_format(reported_date, '%Y-%m-%d') = (SELECT DISTINCT date_format(MAX(reported_date), '%Y-%m-%d') FROM tbl_covid19) " +
			"AND location <> 'World' " +
			"ORDER BY location, reported_date")
	List<PieChartEntity> findCasesForMap( );
	
}
