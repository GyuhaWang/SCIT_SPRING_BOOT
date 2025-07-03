package net.dsa.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@GetMapping("user")
	@ResponseBody
	public Map<String, String> hello() {
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Hello, JSON!");
	    return response;
	}
}

