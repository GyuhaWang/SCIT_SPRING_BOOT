package net.dsa.web1.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"","/"})
	public  String Home() {
		return "home";
	}
	@GetMapping("introduce")
	public  String Introduce() {
		return "introduce";
	}
	@GetMapping("notice")
	public  String Notice() {
		return "notice";
	}
	@GetMapping("inquiry")
	public  String Inquiry() {
		return "inquiry";
	}
}
