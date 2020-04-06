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

	@GetMapping(value = "/search")
	public TableEntity searchEmployees(@RequestParam String search,
			@RequestParam(required = false, defaultValue = "id") String sort, @RequestParam String order,
			@RequestParam int offset, @RequestParam int limit) {

		System.out.println(
				"search=" + search + ", sort=" + sort + ", order=" + order + ", offset=" + offset + ", limit=" + limit);

		Page<EmployeeEntity> result = service.searchEmployees(search, sort, order, offset, limit);

		TableEntity entity = new TableEntity();
		entity.setTotalNotFiltered(result.getTotalElements());
		entity.setTotal(result.getTotalElements());
		entity.setRows(result.getContent());

		return entity;
	}

}
