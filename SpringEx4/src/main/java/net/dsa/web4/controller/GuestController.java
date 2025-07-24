package net.dsa.web4.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web4.dto.GuestBookDTO;
import net.dsa.web4.entity.GuestBookEntity;
import net.dsa.web4.service.GuestService;


@Controller
@Slf4j
@RequestMapping("guest")
@RequiredArgsConstructor
public class GuestController {
	
	private final GuestService gs;
	
	@GetMapping("write")
	public String write() {
		return "/guest/write";
	}
	@GetMapping("guestList")
	public String guestList(Model model) {
		try {
			List<GuestBookDTO> guests=  gs.getAll();
			guests.sort((a,b)-> b.getNum()-a.getNum());
			model.addAttribute("guests", guests);
		}
		catch(Exception e) {
			
		}
		return "/guest/guestList";
	}
	@PostMapping("write")
	public String writeForm(@RequestParam(name="name") String name,@RequestParam(name="password") String pw,@RequestParam(name="content") String content) {
		try {
		
			GuestBookDTO dto = GuestBookDTO.builder()
		            .name(name)
		            .password(pw)
		            .message(content)
		            .build();
		
			GuestBookEntity entity = dto.toEntity();
			
			 gs.createGuestBook(entity);
		
		log.debug("[guest controller - write success] : guestBook :{}", entity);
		}
		catch(Exception e) {
			log.debug("[guest controller - write] : error :{}",e);
		
		}
		return "/guest/write";
	}
	
	@PostMapping("delete")
	public String delete( @RequestParam(name="password") String pw,  @RequestParam(name="id") int id ) {
		try {
			log.debug("[guest controller] delete id:{}",id);
			gs.deleteById(id);
		}
		catch(Exception e) {
			log.debug("[guest controller] error");
		}
		return "redirect:/guest/guestList";
	}
}
