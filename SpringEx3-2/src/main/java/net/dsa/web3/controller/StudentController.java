package net.dsa.web3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web3.dto.StudentDTO;
import net.dsa.web3.service.StudentService;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
	
	private final StudentService  ss;
	
	@GetMapping("enroll")
	public String enroll() {
		return "student/enroll";
	}
	@GetMapping("select")
	public String select(@RequestParam("id") int id,
			Model model) {
		log.debug("[home select] id: {}:",id);
		try {
			StudentDTO student= ss.getById(id);
			log.debug("[home select] student data: {}:",student);
			model.addAttribute("student",student);
			
		}
		catch(Exception e){
			log.error("[home select] error: {}:",e);
			model.addAttribute("error",e.getMessage());
			
		}
		return "student/select";
	}
	@GetMapping("update")
	public String update(@RequestParam("id") int id, Model model) {
		try {
			StudentDTO student = ss.getById(id);
			model.addAttribute("student",student);
		}
		catch(Exception e) {
			log.error("[student update] error:{}",e);
			model.addAttribute("error",e.getMessage());
			
		}
		return "student/updateForm";
	}
	
	@PostMapping("enroll")
	public String enrollStudent(StudentDTO student,Model model) {
		try {
			ss.save(student);
			return "redirect:/";
		}
		catch(Exception e) {
			log.debug("[student @post enroll] error : {}",e);
			model.addAttribute("error",e.getMessage());
			return "student/enroll";
		}
		
		
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("id") int id, Model model)
	{
		try {
			ss.delete(id);
			log.error("[student delete] id:{} delete succeed",id);
		}
		catch(Exception e) {
			model.addAttribute("error",e.getMessage());
			log.error("[student delete] error:{}",e);
		}
		
		return "redirect:/";
	}
	@PostMapping("update")
	public String updateStudent(StudentDTO student, Model model) {
		try {
			ss.updateStudent(student);
			return "redirect:/";
		}
		catch(Exception e){
			log.error("[student update] error :{}",e);
			return "student/update";
		}
	}
}
