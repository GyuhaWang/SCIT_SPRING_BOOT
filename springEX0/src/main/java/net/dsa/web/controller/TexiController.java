package net.dsa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.dsa.web.service.TransportationService;


@Controller
public class TexiController {

	@Autowired
	@Qualifier("DeluxeTaxiServiceImpl")
	TransportationService ts;
	
	@GetMapping("/move")
	public String Move() {
		ts.move();
		return "redirect:/";
//		return "move";
	}
}
