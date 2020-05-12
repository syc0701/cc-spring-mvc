package com.craftercodebase.mvc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.craftercodebase.common.exception.RecordNotFoundException;
import com.craftercodebase.mvc.model.EmployeeEntity;
import com.craftercodebase.mvc.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public Page<EmployeeEntity> searchEmployees(String search, String sort, String order, int offset, int limit) {

		int pageNo = offset / limit;

		Sort.Direction direction = Sort.Direction.DESC;
		if (order.equals("asc")) {
			direction = Sort.Direction.ASC;
		}

		Pageable paging = PageRequest.of(pageNo, limit, Sort.by(direction, sort));

		Page<EmployeeEntity> result = null;
		if (search == "") {
			result = (Page<EmployeeEntity>) repository.findAll(paging);
		} else {
			result = (Page<EmployeeEntity>) repository.findByFirstNameLike("%" + search + "%", paging);
		}

		return result;
	}

	public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
		Optional<EmployeeEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) {
		if (entity.getId() == null) {
			entity = repository.save(entity);

			return entity;
		} else {
			Optional<EmployeeEntity> employee = repository.findById(entity.getId());

			if (employee.isPresent()) {
				EmployeeEntity newEntity = employee.get();
				newEntity.setEmail(entity.getEmail());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void deleteEmployeeById(Long id) throws RecordNotFoundException {
		Optional<EmployeeEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}
}
