package com.craftercodebase.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.craftercodebase.demo.exception.RecordNotFoundException;
import com.craftercodebase.demo.model.EmployeeEntity;
import com.craftercodebase.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping(value = "/searchAll")
	public String searchAllEmployees(Model model) {

		List<EmployeeEntity> result = service.searchAllEmployees();
		model.addAttribute("employees", result);

		return "list-employees";
	}

	@GetMapping(value = "/showDataTables")
	public String showDataTables() {
		return "data-tables";
	}

	@GetMapping(value = "/search")
	public @ResponseBody RspDataTable searchEmployees(@RequestParam String search,
			@RequestParam(required = false, defaultValue = "id") String sort, @RequestParam String order,
			@RequestParam int offset, @RequestParam int limit) {

		Page<EmployeeEntity> result = service.searchEmployees(search, sort, order, offset, limit);

		RspDataTable entity = new RspDataTable();
		entity.setTotalNotFiltered(result.getTotalElements());
		entity.setTotal(result.getTotalElements());
		entity.setRows(result.getContent());

		return entity;
	}

	@RequestMapping(value = "/add")
	public String addEmployeeById(Model model) throws RecordNotFoundException {
		model.addAttribute("employee", new EmployeeEntity());
		model.addAttribute("pageNumber", 1);
		return "add-edit-employee";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}/pageNumber/{pageNumber}" })
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id,
			@PathVariable("pageNumber") Optional<Long> pageNumber) throws RecordNotFoundException {

		if (id.isPresent()) {
			EmployeeEntity entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
			model.addAttribute("pageNumber", pageNumber.get());
		} else {
			model.addAttribute("employee", new EmployeeEntity());
			model.addAttribute("pageNumber", pageNumber.get());
		}

		return "add-edit-employee";
	}

	@RequestMapping(path = "/delete/{id}")
	public @ResponseBody String deleteEmployeeById(Model model, @PathVariable("id") Long id)
			throws RecordNotFoundException {

		service.deleteEmployeeById(id);
		return "Success";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(Model model, EmployeeEntity employee,
			@RequestParam("pageNumber") Optional<Long> pageNumber) {

		service.createOrUpdateEmployee(employee);
		model.addAttribute("pageNumber", pageNumber.get());

		return "data-tables";
	}

}