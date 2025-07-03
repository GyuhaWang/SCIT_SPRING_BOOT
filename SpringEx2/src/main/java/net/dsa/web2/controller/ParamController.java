package net.dsa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.dsa.web2.dto.Person;
import net.dsa.web2.dto.PersonForm;

@Slf4j
@Controller
@RequestMapping("param")
public class ParamController {

	@GetMapping("view1")
	public String view1() {
		return "param/view1";
	}
	@GetMapping("view2")
	public String view2() {
		return "param/view2";
	}
	@GetMapping("view3")
	public String view3() {
		return "param/view3";
	}

	@GetMapping("view4")
	public String view4(Model model) {
		model.addAttribute("person",new PersonForm());
		return "param/view4";
	}
	@PostMapping("validation")
	public String validation(@Validated @ModelAttribute("person") PersonForm personForm,
			BindingResult result
			) {
		log.debug("validation log = personForm: {}", personForm);
		log.debug("validation log = result {}",result);
		
		//1. 어노테이션 기반 검증 후 에러가 하나라도 있으면 다시 view4로 감
		if(result.hasErrors()) {
			return "param/view4";
		}
		
		//2.추가적인 유효성 검사
		if(personForm.getPhone().contains("-")) {
			result.reject("SyntaxError","전화번호 형식이 맞지 않습니다.");
			return "param/view4";
		}
		log.debug("가입성공");
		return "redirect:/";
	}
	
	
	@GetMapping("param1")
	public String param1(@RequestParam(name="name") String name, @RequestParam(name="age")String age, @RequestParam(name="phone") String phone) {
		log.debug("params,{},{},{}",name,age,phone);
		return "redirect:/";
	}
	
	@PostMapping("param2")
	public String param2(@RequestParam(name="name") String name, @RequestParam(name="age")String age, @RequestParam(name="phone") String phone) {
		log.debug("params,{},{},{}",name,age,phone);
		return "redirect:/";
	}
	
	@PostMapping("param3")
	public String param3(Person p) {
		log.debug("params,{},",p);
		return "redirect:/";
	}
	
	@GetMapping("param4")
	public String param4(Person p) {
		log.debug("params,{},",p);
		return "redirect:/";
	}
	
	@GetMapping("param5")
	public String param5(@RequestParam(name="name",defaultValue ="아무개") String name, @RequestParam(name="age",defaultValue ="0") int age, @RequestParam(name="phone",defaultValue ="01012345678") String phone){
		log.debug("params,{},{},{}",name,age,phone);
		return "redirect:/";
	}
	
	// Model -> 컨트롤러에서  view로 데이터를 넘기는데 사용되는 객체이다.
	// 재요청시 저장된 데이터가 사라짐
	@GetMapping("model")
	public String model1(Model model){
		String str="문자열";
		int num = 100;
		Person p = new Person("홍길동",33,"01012341234");
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("person",p);
		
		return "param/model";
	}
	
	
}
