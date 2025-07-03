package net.dsa.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping({"","/"})
	public String home() {
		return "home";
	}
	
	@GetMapping("exception500")
	public String exception500() {
		throw new RuntimeException();
	}
}
