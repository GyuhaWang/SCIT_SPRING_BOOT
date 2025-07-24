package net.dsa.web5.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web5.dto.MemberDTO;
import net.dsa.web5.security.AuthenticatedUser;
import net.dsa.web5.service.MemberService;

/**
 * 회원 관련 컨트롤러
 * */
@Controller
@Slf4j
@RequestMapping("member")
@RequiredArgsConstructor

public class MemberController {
	
	private final MemberService ms;
	
	/**
	 * 회원가입 페이지 이동
	 * */
	@GetMapping("join")
	public String join() {
		log.debug("[member join]");
		return "member/joinForm";
	}
	/**
	 * 회원가입 정보를 받아 회원가입 처리
	 * @param memberDTO
	 * @return home.html
	 * */
	@PostMapping("join")
	public String join(MemberDTO member) {
		log.debug("[member join] member 정보 :{}",member);
		try {
			ms.join(member);
			log.debug("가입 성공");
		}
		catch(Exception e) {
			log.debug("가입 실패:{}",e);
		}
		return "redirect:/";
	}
	/**
	 * 회원가입 페이지에서 "ID 중복확인" 클릭시 
	 * 새창으로 보여줄 검색 페이지 이동 
	 * @return idCHeck.html
	 * */
	@GetMapping("idCheck")
	public String idCheck() {
		return "member/idCheck";
	}
	
	/**
	 * 아이디 중복확인 페이지에서 검색 요청 사례
	 * @param searchId 검색할 아이디
	 * @Param model
	 * @return idCheck.html
	 * */
	@PostMapping("idCheck")
	public String idCheck(@RequestParam("searchId") String searchId,
						Model model) {
		log.debug("[member idcheck] searchId:{}",searchId);
		
		// 검색할 아이디를 서비스에서 사용가능한지 조회
		try {
		boolean result = ms.idCheck(searchId);
		
		//검색할 아이디와 결과를 저장
		model.addAttribute("searchId", searchId);
		model.addAttribute("result",result);
		}
		catch(Exception e) {
			
		}
		return "member/idCheck";
	}
	/**
	 * 로그인 페이지 이동
	 * @return loginForm.html
	 * */
	@GetMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	
	/**
	 * 수정 페이지 이동
	 * @param model`
	 * @return updateForm.html
	 * */
	@GetMapping("info")
	public String info(Model model,@AuthenticationPrincipal AuthenticatedUser authentication ) {
		try {
		  String userId = authentication.getUsername();
		  MemberDTO member = ms.getMember(userId);
		  model.addAttribute("member", member);
		return "member/updateForm";
		}
		catch(Exception e) {
			log.debug("[member info] user not found :{}",e);
		}
		return "redirect:/";
	}
	/**
	 * 회원가입 정보를 받아 회원가입 처리
	 * @param memberDTO
	 * @return home.html
	 * */
	@PostMapping("update")
	public String update(MemberDTO member,@AuthenticationPrincipal AuthenticatedUser authentication) {
		log.debug("[member update] member 정보 :{}",member);
		try {
			member.setMemberId(authentication.getUsername());
			ms.update(member);
			log.debug("업데이트 성공");
		}
		catch(Exception e) {
			log.debug("업데이트 실패:{}",e);
		}
		return "redirect:/member/info";
	}
}
