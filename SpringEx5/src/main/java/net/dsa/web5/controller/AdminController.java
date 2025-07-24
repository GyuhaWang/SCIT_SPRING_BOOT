package net.dsa.web5.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web5.dto.MemberDTO;
import net.dsa.web5.security.AuthenticatedUser;
import net.dsa.web5.service.MemberService;

@Controller
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
// 해당 클래스의 모든 메서드 실행 전에
// Spring Security가 hasRole('ADMIN') 조건 검색
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	
	private final MemberService ms;
	
	@GetMapping("page")
	public String admin() {
		return "admin/adminPage";
	}
	
	@GetMapping("list")
	public String list(Model model,@AuthenticationPrincipal AuthenticatedUser userDetail) {
		
		List<MemberDTO> members = ms.getMembers(userDetail);
		
		model.addAttribute("members",members);
		
		return "admin/memberList";
	}
	@GetMapping("authorize")
	public String changeAuthorize(@RequestParam("id") String id ) {
		
		ms.updateAutorize(id);
		return "redirect:/admin/list";
	}
	
	@GetMapping("status")
	public String changeStatus(@RequestParam("id") String id ) {
	
		ms.updateStatus(id);
		return "redirect:/admin/list";
	}
}
