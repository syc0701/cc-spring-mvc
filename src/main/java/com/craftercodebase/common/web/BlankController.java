package com.craftercodebase.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blank")
public class BlankController {

	@GetMapping(value = "/showBlankPage")
	public String showDataTables() {
		return "blank-page";
	}

}