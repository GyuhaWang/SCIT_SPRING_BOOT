package net.dsa.ex2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import net.dsa.ex2.dto.Member;
import net.dsa.ex2.dto.MemberForm;
import net.dsa.ex2.dto.loginForm;
import net.dsa.ex2.service.MemberService;

@Controller
@Slf4j
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberService ms;
	
	@GetMapping("join")
	public String join() {
	
		return "member/join";
	}
	@GetMapping("login")
	public String login() {

		return "member/login";
	}
	@GetMapping("logOut")
	public String loginOut(HttpSession session) {
		
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return "redirect:/";
	}
	
	
	@GetMapping("info")
	public String info(Model model) {
	
		
		List<Member> members = ms.getAllUsers();
		model.addAttribute("members", members);
		return "member/info";
	}
	
	@PostMapping("join")
	public String joinUser(MemberForm memberForm,
			BindingResult result) {
		try {
			String id = memberForm.getId();
			String pw = memberForm.getPw();
			String name = memberForm.getName();
			String phone = memberForm.getPhone();
			Member member = new Member(id,pw,name,phone);
			ms.signIn(member);
			return "redirect:/";
		}
		catch(Exception e) {
			result.reject("SyntaxError",e.toString());
			return "member/join";
		}
	}
	
	@PostMapping("login")
	public String loginUser(HttpSession session,loginForm loginForm,
			BindingResult result) {
		
		String id = loginForm.getId();
		String pw = loginForm.getPw();
		
		try {
			Member currentUser = ms.login(id, pw);
			
			session.setAttribute("user", currentUser);
			log.debug("login success");
			return "redirect:/";
		}
		catch(Exception e) {
			log.debug("login fail");
			result.reject("SyntaxError",e.toString());
			return "member/login";
		}
	
		
	}
}
