package net.dsa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j// log 사용합니다.
@RequestMapping("session")
public class SessionController {
	
	/*
	 [세션]
	 세션은 서버 측에서 사용자의 상태를 유지하는 메커니즘
	 서버는 각 클라이언트에 대한 고유한 세션 ID를 생성하고 클라이언트가 해당 세션 ID를 통해 서버에 상태를 유지.
	 저장 휘치: 서버 측 (메모리나 DB)에 저장
	 유효기간 : 세션에는 만료 시간이 있으며, 일정 기간 활동이 없으면 세션 만료
	 보안: 서버측에 저장되므로 비교적 안전
	 범위: 서버 내에서만 유효
	 */
	
	@GetMapping("session1")
	public String session1(HttpSession session) {
		log.debug(session.getId());
		session.setAttribute("name", "abc");


		return "redirect:/";
	}
	@GetMapping("session2")
	public String session2(HttpSession session) {
		session.removeAttribute("name");
		//		session.removeAttribute(session.getId());
		//		log.debug(session.toString());
		return "redirect:/";
	}
	@GetMapping("session3")
	public String session3(HttpSession session) {
		String name = (String) session.getAttribute("name");
//		String name = (String) session.getAttribute(session.getId());
		log.debug("session info:{}",name);
//		log.debug(session.toString());
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String login() {
		return "session/login";
	}
	
	@PostMapping("login")
	public String login2(HttpSession session, @RequestParam("id") String id, @RequestParam("password") String pw) {
		log.debug("form login info:{}/{}",id,pw);
		if(id.equals("abc")&&pw.equals("123")) {
			session.setAttribute("loginId", id);
			return "redirect:/";
//			return "loginTest";
		}
		else {
			log.debug("fail login");
			return "session/login";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("loginTest")
	public String loginTest(HttpSession session) {
		boolean isLogin = session.getAttribute("loginId") !=null;
		if(isLogin) {
			return "session/loginTest";
		}
		else {
			return "redirect:/";
		}
	}
}
