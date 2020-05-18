package com.craftercodebase.stats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.craftercodebase.stats.model.Covid19Entity;

@Repository
public interface Covid19Repository extends PagingAndSortingRepository<Covid19Entity, Long> {
	
	@Query(nativeQuery = true, value=" SELECT * FROM tbl_covid19 tb" + 
			"    WHERE" + 
			"        tb.location <> 'World'" + 
			"            AND tb.reported_date = (SELECT MAX(reported_date) FROM tbl_covid19) and tb.location like %:loca%" + 
			"    ORDER BY tb.total_cases DESC",
			countQuery = " SELECT " + 
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
	Page<Covid19Entity> findAll(Pageable page, @Param("loca") String search);

	 
}
