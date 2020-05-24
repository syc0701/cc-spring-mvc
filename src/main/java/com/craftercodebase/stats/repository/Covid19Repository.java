package com.craftercodebase.stats.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.craftercodebase.stats.model.Covid19Data;

@Repository
public interface Covid19Repository extends PagingAndSortingRepository<Covid19Data, Long> {

	@Query(nativeQuery = true, value = " SELECT * FROM tbl_covid19 tb" +
			"    WHERE" +
			"        tb.location <> 'World'" +
			"            AND tb.reported_date = (SELECT MAX(reported_date) FROM tbl_covid19) and tb.location like %:loca%" +
			"    ORDER BY tb.total_cases DESC", countQuery = " SELECT " +
					"       count(*)" +
					"    FROM" +
					"        tbl_covid19 tb" +
					"    WHERE" +
					"        tb.location <> 'World'" +
					"            AND tb.reported_date = (SELECT " +
					"                MAX(reported_date)" +
					"            FROM" +
					"                tbl_covid19) and tb.location like %:loca%" +
					"    ORDER BY tb.total_cases DESC" +
					"")
	Page<Covid19Data> findAll(Pageable page, @Param("loca") String search);

	@Query(nativeQuery = true, value = "SELECT * FROM " +
			"    tbl_covid19 " +
			"WHERE" +
			"    iso_code = :iso_code " +
			"ORDER BY reported_date")
	List<Covid19Data> findCasesByIsoCode(@Param("iso_code") String search);

	@Query(nativeQuery = true, value = "SELECT * FROM " +
			"    tbl_covid19 " +
			"WHERE" +
			"    iso_code in (:iso_codes) " +
			"ORDER BY iso_code, reported_date")
	List<Covid19Data> findCasesByIsoCodes(@Param("iso_codes") List<String> iso_codes);

	@Query(nativeQuery = true, value = "SELECT * FROM " +
			"    tbl_covid19 " +
			"WHERE" +
			"    iso_code in (:location) " +
			"AND date_format(reported_date, '%Y-%m-%d') = :selectedDate " +
			"ORDER BY location, reported_date")
	List<Covid19Data> findCasesByDate(@Param("location") List<String> search, String selectedDate);

	@Query(nativeQuery = true, value="SELECT * FROM " + 
			"    tbl_covid19 " + 
			"WHERE date_format(reported_date, '%Y-%m-%d') = (SELECT DISTINCT date_format(MAX(reported_date), '%Y-%m-%d') FROM tbl_covid19) " +
			"AND location <> 'World' " +
			"ORDER BY location, reported_date")
	List<Covid19Data> findCasesForMap();

}
