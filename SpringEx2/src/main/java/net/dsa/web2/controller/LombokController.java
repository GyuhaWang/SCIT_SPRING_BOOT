package net.dsa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.dsa.web2.dto.Person;

@Slf4j
@Controller
public class LombokController {

	
	@GetMapping("lombok/lombok1")
	public String lombok1() {
		Person p = new Person();
		p.setAge(0);
		p.setName("hong");
		p.setPhone("01012345678");
		System.out.println(p);
		Person ps = Person.builder()
				.name("hong")
				.age(0)
				.phone("01099999999")
				.build();
		System.out.println(ps);
		
	return "lombok/lombok1";
	}
	
	@GetMapping("lombok/lombok2")
	public String lombok2() {
		
		log.error("error");
		log.warn("warn");
		log.info("info");
		log.debug("debug");
		log.trace("trace");
		
		String str = "log";
		log.debug("data {}",str);
	return "lombok/lombok2";
	}
	
	
	
}
