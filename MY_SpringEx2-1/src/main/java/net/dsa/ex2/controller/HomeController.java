package net.dsa.ex2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {

	@GetMapping({"","/"})	
	public String home() {
	
		return "home";
	}
}
