package test.craftercodebase.mvc.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.craftercodebase.mvc.web.EmployeeController;

@SpringBootTest
public class TestEmployeeController {

	@Autowired
	private EmployeeController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();

//		RspDataTable responseBody = controller.searchEmployees("", "firstname", "desc", 1, 10);
//		System.out.println(responseBody);
	}
}
