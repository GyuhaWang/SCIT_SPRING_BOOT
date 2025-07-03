package net.dsa.web2.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web2.dto.Human;
import net.dsa.web2.dto.HumanForm;
import net.dsa.web2.dto.Student;

@Controller
@Slf4j// log 사용합니다.
@RequestMapping("ex")
public class ExController {

	@GetMapping("info")
	public String info(Model model) 
	{
		model.addAttribute("human",new HumanForm());
		return "ex/input";
	}
	@GetMapping("cookie")
	public String cookie(@CookieValue(name="count",defaultValue="0")int count,HttpServletResponse response, Model model) 
	{
		log.debug("cookie info:{}",count);
		int currnet = count+1;
		String currentCount = String.valueOf(currnet);
		Cookie c = new Cookie("count",currentCount );
		response.addCookie(c);
		model.addAttribute("count",currentCount);
		return "ex/cookie";
	}
	
	@PostMapping("info")
	public String getCid(@Validated @ModelAttribute("human") HumanForm humanForm, BindingResult result,Model model) {
		
		
		if(result.hasErrors()) {
			return "ex/input";
		}
		if(!(humanForm.getCid().contains("-")&&humanForm.getCid().length()==14)) {
			result.reject("SyntaxError","주민번호 형식이 맞지 않습니다.");
			return "ex/input";
		}
		
		String cid= humanForm.getCid();
		String name = humanForm.getName();	    
		try {
			Human humanInfo = createHumanInfo(name,cid);
			model.addAttribute("info",humanInfo);
			return"ex/output";
		}
		catch(Exception e) {
			result.reject("SyntaxError",e.getMessage());
			return "ex/input";
		}
		
	}
	
	public Human createHumanInfo(String name, String cid) {
		Human human = new Human();
		human.setName(name);
		human.setCid(cid);
		
		 int gender =  Integer.parseInt(cid.substring(7, 8));
		 //gender에 따른 genderString set하기
		 human.setGenderNum(gender);
		 int hGenderNum = human.getGenderNum();
		 if(hGenderNum==1 || hGenderNum==3) {
			 human.setGenderString("남자");
		 }
		 else if(hGenderNum==2 || hGenderNum==4) {
			 human.setGenderString("여자");
		 }
		 else {
			 throw new IllegalArgumentException("존재하지 않는 성별입니다.");
		 }
		 
		 int year = Integer.parseInt(cid.substring(0,2));
		 int month = Integer.parseInt(cid.substring(2,4));
		 int day = Integer.parseInt(cid.substring(4,6));
		
		
		 // 태어난 연도 수정값
		 if(gender == 1 || gender ==2) {
			 human.setBYear(year+1900);
		 }
		 else if(gender ==3 || gender ==4) {
			 human.setBYear(year+2000);
		 }
		 else {
			 throw new IllegalArgumentException("잘못된 주민번호 형식입니다.");
		 }
		 
		 human.setBDay(day);
		 human.setBMonth(month);
		 
		 // 올바른 출생일인지 확인(오늘보다 후인지)
		 LocalDate now = LocalDate.now();
		 if(human.getBYear()>=now.getYear() && human.getBMonth()>=now.getMonthValue()&& human.getBDay()>now.getDayOfMonth()) {
			 throw new IllegalArgumentException("아직 태어나지 않은 사람입니다.");
		 }
		 
		 human.setAge( now.getYear()- human.getBYear()+1 );
		 
		return human;
	}
	
	@GetMapping("darkmode")
	public String darkmode() {
		return "ex/darkmode";
	}
	@GetMapping("temp")
	public String temp() {
		return "ex/temp";
	}
	@GetMapping("thymeleaf")
	public String thymeleaf(Model model) {
		
		List<Student> list = List.of(
				new Student("홍길동",20,"010-1234-1234",30),
				new Student("김길동",20,"010-1234-1234",70),
				new Student("최길동",20,"010-1234-1234",60),
				new Student("박길동",20,"010-1234-1234",30),
				new Student("유길동",20,"010-1234-1234",30),
				new Student("고길동",20,"010-1234-1234",30)
				);
		
		model.addAttribute("list",list);
		
		return "ex/print";
	}
}
