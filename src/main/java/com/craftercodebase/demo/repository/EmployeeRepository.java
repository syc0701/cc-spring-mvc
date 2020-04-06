package com.craftercodebase.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.craftercodebase.demo.model.EmployeeEntity;
 
@Repository
public interface EmployeeRepository
        extends PagingAndSortingRepository<EmployeeEntity, Long> {
 
	Page<EmployeeEntity> findByFirstNameLike(String firstname, Pageable page);
	
}
