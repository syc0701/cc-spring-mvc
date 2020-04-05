package com.craftercodebase.demo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.craftercodebase.demo.model.EmployeeEntity;
import com.craftercodebase.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController2 {

	@Autowired
	EmployeeService service;

	@GetMapping(value = "/list")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		
		Page<EmployeeEntity> pagedResult = service.getAllEmployees(pageNo, pageSize, sortBy);
		if (pagedResult.hasContent()) {
			return new ResponseEntity<List<EmployeeEntity>>(pagedResult.getContent(), new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<EmployeeEntity>>(new ArrayList<EmployeeEntity>(), new HttpHeaders(),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/list2")
	public ResponseEntity<TableEntity> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		
		System.out.println("HIHII.............HIHIHI");
		
		Page<EmployeeEntity> pagedResult = service.getAllEmployees(pageNo, pageSize, "id");
		if (pagedResult.hasContent()) {
			
			TableEntity entity = new TableEntity(); 
			entity.setTotalNotFiltered(pagedResult.getTotalElements());
			entity.setTotal(pagedResult.getTotalElements());
			entity.setRows(pagedResult.getContent());
			
			//return new ResponseEntity<List<EmployeeEntity>>(pagedResult.getContent(), new HttpHeaders(), HttpStatus.OK);
			return new ResponseEntity<TableEntity>(entity, new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity<TableEntity>(new TableEntity(), new HttpHeaders(),
					HttpStatus.OK);
		}
	}

}
