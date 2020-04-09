package com.craftercodebase.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.craftercodebase.mvc.model.EmployeeEntity;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {

	/**
	 * Search by first name
	 * 
	 * @param firstname
	 * @param page
	 * @return
	 */
	Page<EmployeeEntity> findByFirstNameLike(String firstname, Pageable page);

}
