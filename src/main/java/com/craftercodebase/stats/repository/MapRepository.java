package com.craftercodebase.stats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.craftercodebase.stats.model.MapEntity;

@Repository
public interface MapRepository extends PagingAndSortingRepository<MapEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT " +
			"    iso_code," +
			"    a.location," +
			"    a.total_cases," +
			"    a.total_deaths," +
			"    b.population " +
			"FROM" +
			"    tbl_covid19 a," +
			"    tbl_location b " +
			"WHERE" +
			"    a.location = b.location" +
			"        AND DATE_FORMAT(reported_date, '%Y-%m-%d') = (SELECT DISTINCT" +
			"            DATE_FORMAT(MAX(reported_date), '%Y-%m-%d')" +
			"        FROM" +
			"            tbl_covid19)" +
			"        AND a.location <> 'World' " +
			"ORDER BY a.location , reported_date LIMIT 2")
	List<MapEntity> findAll();

}
