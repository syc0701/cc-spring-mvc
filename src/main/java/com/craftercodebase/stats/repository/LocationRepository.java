package com.craftercodebase.stats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.craftercodebase.stats.model.Tbl_Location;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Tbl_Location, Long> {


	@Query(nativeQuery = true, value="SELECT * FROM TBL_LOCATION")
	Page<Tbl_Location> findAll(Pageable page, @Param("loca") String search);
}
