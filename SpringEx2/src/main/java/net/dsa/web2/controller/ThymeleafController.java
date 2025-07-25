package net.dsa.web2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.dsa.web2.dto.Person;

@Controller
@Slf4j
@RequestMapping("th")
public class ThymeleafController {

	@GetMapping("thymeleaf1")
	public String thymeleaf1(Model model) {
		
		String str ="문자열";
		int num = 300;
		Person p = new Person("kim",33,"010-1234-1234");
		String tag = "<marquee>html tag</marquee>";
		String url = "https://google.com";
		
		model.addAttribute("str",str);
		model.addAttribute("num",num);
		model.addAttribute("person",p);
		model.addAttribute("tag",tag);
		model.addAttribute("url",url);
		
		int n1 = 1000000;
		double n2 = 123.45678;
		double n3 = 0.5;
		Date d = new Date();
		
		model.addAttribute("inum",n1);
		model.addAttribute("dnum",n2);
		model.addAttribute("dnum2",n3);
		model.addAttribute("date",d);
		model.addAttribute("values","aaa,bbb,ccc");
		
		
		return "thymeleaf/thymeleaf1";
	}
	
	@GetMapping("thymeleaf2")
	public String thymeleaf2(Model model) {
		
		ArrayList<String> list = new ArrayList();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		
		HashMap<String,Object> map= new HashMap<>();
		map.put("name", "키보드");
		map.put("price", 10000);
		
		model.addAttribute("list",list);
		model.addAttribute("map",map);
		
		return "thymeleaf/thymeleaf2";
	}
	
}
