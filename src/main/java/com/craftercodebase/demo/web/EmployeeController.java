package com.craftercodebase.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

//	@GetMapping(value = "/showDataTables")
//	public String showDataTables() {
//		return "data-tables.html";
//	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			EmployeeEntity entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {
			model.addAttribute("employee", new EmployeeEntity());
		}
		return "add-edit-employee";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteEmployeeById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(EmployeeEntity employee) {
		service.createOrUpdateEmployee(employee);
		return "redirect:/";
	}

}