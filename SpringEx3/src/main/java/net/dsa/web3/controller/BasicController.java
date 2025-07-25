package net.dsa.web3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web3.dto.MemberDTO;
import net.dsa.web3.service.MemberService;

@Controller
@Slf4j
@RequestMapping("basic")

@RequiredArgsConstructor
public class BasicController {
	
	
//	@Autowired
//	MemberService ms;

//	private MemberService ms;
//	@Autowired
//	public BasicController(MemberService ms) {
//		this.ms = ms;
//	}
	
	// lombok은 final 이 붙은 필드를 대상으로 자동으로 생성자 매개변수를 만듦
	private final MemberService ms;
	
	
	@GetMapping("insertData")
	public String insertData() {
		log.debug("[controller-insert] insertData 접근");
		ms.insertData();
		return "redirect:/";
	}
	
	/*
	 * 데이터 조회 (임의의 id로 회원 정보 조회)
	 * @return 메인화면으로 이동
	 * */
	@GetMapping("selectData")
	public String selectData() {
		log.debug("[controller-select] selectData 접근");
		MemberDTO memberDTO = ms.selectData();
		log.debug("[controller-select] selectData :{}",memberDTO);
		return "redirect:/";
	}
	
	@GetMapping("updateData")
	public String updateData() {
		log.debug("[controller-update] updateData 접근");
		MemberDTO m = MemberDTO.builder()
					.id("eee")
					.pw("123")
					.name("변경됨")
					.phone("010-1234-1234")
					.address("서울특별시 강남구")
					.build();
		ms.updateData(m);
		log.debug("[controller-update] updateData success");
		return "redirect:/";
	}
	@GetMapping("deleteData")
	public String deleteData() {
		log.debug("[controller-delete] deleteData 접근");
		
		boolean result = ms.deleteData("eee");
		if(result) {
			log.debug("[controller-delete] deleteData 삭제 완료");
		}
		else {
			log.debug("[controller-delete] deleteData 삭제 실패");
		}
		
		
		return "redirect:/";
	}
	
	@GetMapping("selectAllData")
	public String selectAllData() {
		log.debug("[controller-selectAllData] selectAllData 접근");
		
		List<MemberDTO> members = ms.selectAllData();
		for(MemberDTO dto : members) {
			log.debug("[controller-selectAllData] dto :{}",dto);
		}
		
		
		return "redirect:/";
	}
}
