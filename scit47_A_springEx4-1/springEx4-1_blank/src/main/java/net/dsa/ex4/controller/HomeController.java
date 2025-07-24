package net.dsa.ex4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.ex4.dto.ChickenDTO;
import net.dsa.ex4.service.ChickenService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
	
	private final ChickenService cs;
	
	@GetMapping({"", "/"})
	public String home() {
		return "home";
	}
	
	@GetMapping("modal")
	public String modal() {
		return "modal";
	}
	
	@PostMapping("order")
	public String submitOrder(ChickenDTO dto) {
		
		log.debug("주문 정보: {}", dto);
		
		try {
			cs.order(dto);
			log.debug("주문 성공!");
		} catch (Exception e) {
			log.debug("[예외 발생] 주문 실패..");
		}
		
		return "redirect:/";
	}
	
}
