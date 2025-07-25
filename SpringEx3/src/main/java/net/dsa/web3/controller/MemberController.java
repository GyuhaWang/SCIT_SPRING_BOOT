package net.dsa.web3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web3.dto.MemberDTO;
import net.dsa.web3.service.MemberService;

@Controller
@Slf4j
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService  ms;
	/**
	 * 회원가입 페이지 이동
	 * @return "member/join";
	 * */
	@GetMapping("join")
	public String join () {
		return "member/join";
	}
	/**
	 * 회원정보 조회
	 * @return ID입력 폼
	 * */
	@GetMapping("select")
	public String select() {
		return "member/selectForm";
	}
	/**
	 * 업데이트 폼 페이지 이동
	 * @return "member/updateForm";
	 * */
	@GetMapping("updateForm")
	public String updateForm (HttpSession session, Model model) {
		try {
		MemberDTO userInfo = (MemberDTO) session.getAttribute("user_session");
		if(userInfo == null) throw new Exception("user not exist");
		log.debug("[member updateForm] user session info:{}",userInfo);
		model.addAttribute("user",userInfo);
		return "member/updateForm";
		}
		catch(Exception e) {
		return "redirect:/";
		}
	}
	/**
	 * 로그인 페이지 이동
	 * return loginForm.html
	 * */
	@GetMapping("login")
	public String login () {
		return "member/login";
	}
	/**
	 * 로그아웃
	 * */
	@GetMapping("logout")
	public String logout (HttpSession session) {
		session.removeAttribute("user_session");
		session.invalidate();
		log.debug("[member logout] user log out");
		return "redirect:/";
	}
	/**
	 * create user
	 * @return page: member/create 
	 * */
	@GetMapping("create")
	public String create () {
		log.debug("[member read] page navigate to read");
		return "member/create";
	}
	/**
	 * getAllUsers
	 * @model List<MemberDTO>
	 * @return page: member/read 
	 * */
	@GetMapping("read")
	public String read (Model model) {
		// get all user info
		List<MemberDTO> members = ms.selectAllData();
		log.debug("[member read] get members,{}",members);
		model.addAttribute("members",members);
		log.debug("[member read] page navigate to read");
		return "member/read";
	}
	/**
	 *updateUser
	 * @return page: member/update 
	 * */
	@GetMapping("update")
	public String update (MemberDTO member) {
			return "member/update";
	}
	/**
	 * delete user
	 * @param id
	 * @return page: "redirect:/member/read"
	 * */
	@GetMapping("delete")
	public String delete (@RequestParam("id") String id, Model model) {
		log.debug("[member delete] id:{}",id);
		ms.deleteData(id);
		return "redirect:/member/read";
		
	}
	
	/**@param id
	 * @parma model
	 * @return selectForm.html
	 * 
	 * */
	@GetMapping({"info"+"/{id}","info"})
	public String info(
			@PathVariable(name="id", required =false) String id,
			Model model
			) {
		/*
		 *@PathVariable
		 *URL 경로 자제체 포함된 값을 파라미터로 받아오는 방식
		 *@RequestParam /member?id=abc&pw=123
		 *@PathVariable /emeber/abc -> abc
		 */
		log.debug("path variable :{}",id);
		try {
			MemberDTO member = ms.select(id);
			log.debug("[member info] value :{}",member);
			model.addAttribute("member",member);
		}
		catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("id",id);
		return "member/selectForm";
		
	}
	
	/**
	 * signin
	 * @param MemberDTO
	 * @return redirect
	 * */
	@PostMapping("join")
	public String join(MemberDTO member) {
		log.debug("[member join] form data 확인 {}",member);
		
		ms.save(member);
	
		return "redirect:/";
	}
	
	/**
	 * login
	 * @param id 
	 * @param pw
	 * @param HttpSession
	 * @return redirect main || login
	 * */
	@PostMapping("login")
	public String login(@RequestParam("id")String id,@RequestParam("pw") String pw, HttpSession session) {
		
		log.debug("[*post member login] id:{}, pw:{}",id,pw);
		try {
		MemberDTO userInfo = ms.login(id,pw);
		session.setAttribute("user_session", userInfo);
		log.debug("[*post member login success] userInfo:{}",userInfo);
		return "redirect:/";
		}
		catch(Exception e) {
			log.debug("[*post member login fail] errorMessage:{}",e.getMessage());
			return "redirect:/member/login";
		}
	}
	
	/**
	 * @param MemberDTO
	 * @return "redirect:/:"member/updateForm"
	 * */
	@PostMapping("update")
	public String userUpdate (MemberDTO member) {
		try {
			ms.save(member);
			log.debug("[member update] member update success");
			return "redirect:/";
		}
		catch(Exception e) {
			log.debug("[member update] member update fail");
			return "member/updateForm";
		}
	}
	
	/**
	 * @param ID
	 * @param model
	 * @return "redirect:/selectForm.html"
	 * */
	@PostMapping("select")
	public String selectMember (@RequestParam("id") String id, Model model) {
		try {
			log.debug("[member select] value :{}",id);
			MemberDTO member = ms.select(id);
			log.debug("[member select] value :{}",member);
			model.addAttribute("member",member);
		}
		catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("id",id);
		return "member/selectForm";

	}
}
