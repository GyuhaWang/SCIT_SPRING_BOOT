package net.dsa.web3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

		@GetMapping({"","/"})
		public String home () {
			return "home";
		}
	
}
