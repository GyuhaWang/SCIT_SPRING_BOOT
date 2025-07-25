package net.dsa.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
	
	@GetMapping("image")
	public String image() {
		System.out.println("run when image path requested");
		return "image";
	}
	@GetMapping("sub/image2")
	public String image2(){
		return "image2";
		}
	
	@GetMapping("css")
	public String css() {
		return "css";
	}
	
	@GetMapping("js")
	public String js() {
		return "js";
	}
	
}
