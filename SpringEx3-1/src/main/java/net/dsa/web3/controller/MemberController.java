package net.dsa.web3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web3.service.MemberService;
import net.dsa.web3dto.MemberDTO;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
	
	private final MemberService ms;
	//------------------------- router -------------------------------------------
	/**
	 * 회원가입 페이지 이동
	 * @return "member/signin"
	 */
	@GetMapping("signin")
	public String signin() {
		return "member/signin";
	}
	/**
	 * 로그인 페이지 이동
	 * @return "member/login"
	 */
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	/**
	 * 회원 정보 조회 페이지
	 * @return "member/read"
	 */
	@GetMapping("read")
	public String read() {
		return "member/read";
	}
	/**
	 * 회원 정보 업데이트 페이지
	 * @return "member/update"
	 */
	@GetMapping("update")
	public String update() {
		return "member/update";
	}
	/**
	 * 로그아웃 로직 실행
	 * @return "redirect:/"
	 */
	@GetMapping("logout")
	public String logout(HttpSession session) {
		return "redirect:/";
	}
	
	//-------------------------------logic ---------------------------------------
	
	/**
	 * 회원가입 
	 * @param MemberDTO
	 * @return "redirect:/" || "member/signin"
	 */
	@PostMapping("signin")
	public String member_signin(MemberDTO member) {
	
		log.debug("[controller_member_member_signin]: formInfo:{}",member);
		try {
		// user sign action
			ms.signIn(member);
			return "redirect:/";
		}
		catch(Exception e){
			return "member/signin";
		}
	}
	
}
