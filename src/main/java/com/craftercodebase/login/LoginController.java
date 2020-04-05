package com.craftercodebase.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.craftercodebase.demo.model.EmployeeEntity;
import com.craftercodebase.demo.service.EmployeeService;

@Controller
public class LoginController {

	@Autowired
	EmployeeService service;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/greeting")
	public String greeting() {
		return "greeting";
	} 

	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public String hello(HttpServletRequest request, Model model) {

		String value = (String) request.getParameter("name");

		System.out.println("value=" + value);

		if (value == null) {
			value = "World";
		}

		model.addAttribute("name", value);
		model.addAttribute("title", "Hello, Spring response");

		return "/greeting";
	}

	@RequestMapping(value = "/hello_input")
	public String hello2(HttpServletRequest request, Model model) {
		return "/greeting_frm";
	}
}
