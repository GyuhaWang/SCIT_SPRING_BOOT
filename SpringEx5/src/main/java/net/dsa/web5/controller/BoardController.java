package net.dsa.web5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web5.dto.BoardDTO;
import net.dsa.web5.service.BoardService;


@Controller
@Slf4j
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;
	
	@Value("${board.uploadPath}")
	String uploadPath;
	
	@GetMapping("write")
	public String write() {
		return "board/writeForm";
	}
	@GetMapping("list")
	public String list(Model model) {
		try {
		List<BoardDTO> lists = bs.getAll(); 
		model.addAttribute("lists",lists);
		model.addAttribute("total",lists.size());
		log.debug("controller  board list lists:{}",lists );
		}
		catch(Exception e) {
			log.debug("controller error board list :{}",e );
		}
		return "board/list";
	}
	
	@GetMapping("read")
	public String read(@RequestParam(name="id") Integer id, Model model) {
		try {
//			bs.updateViewCount(id);
			BoardDTO board =bs.getById(id,true);
			model.addAttribute("board",board);
			return "board/read";
		}
		catch(Exception e) {
			log.debug("[error exception] read board failed");
			return "redirect:list";
		}
	}
	
	@GetMapping("like")
	public String like(@RequestParam(name="id") Integer id) {
		
		try {
			bs.addRecommend(id);
		}
		catch(Exception e) {
			log.debug("fail to update like count");
		}
		return "redirect:/board/read?id="+id.toString();
	}
	@GetMapping("delete")
	public String delete(@RequestParam(name="id") Integer id) {
		
		try {
			bs.delete(id);
		}
		catch(Exception e) {
			log.debug("fail to update like count");
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("update")
	public String update(@RequestParam(name="id") Integer id, 
			@AuthenticationPrincipal UserDetails user,
			Model model) {
		try {
			BoardDTO board =bs.getById(id, false);
			if(!board.getMemberId().equals(user.getUsername())) throw new Exception("user unQaulified");
			model.addAttribute("board",board);
			return "board/update";
		}
		catch(Exception e) {
			return "redirect:/board/list?id="+id.toString();
		}
		
	}
	@PostMapping("update")
	public String update(BoardDTO boardDTO, 
			@RequestParam(name="upload", required = false) MultipartFile upload,
			@AuthenticationPrincipal UserDetails user) {
		try {
			log.debug("update log:{}",boardDTO);
			bs.update(boardDTO,uploadPath,upload);
		}
		catch(Exception e) {
			log.debug("update filed");
		}
		return "redirect:/board/list";
	}
	/**
	 * @param boardNum
	 * @return file
	 * */
	
	@GetMapping("download")
	public void download(@RequestParam(name="boardNum") int boardNum,
			HttpServletResponse response
			) {
		try {
			bs.download(boardNum, response,uploadPath);
			log.debug("download success");
		}
		catch(Exception e) {
			log.debug("download fail..");
			
		}
	}
	/**
	 * 글 저장
	 * @param boardDTO 작성한 글 정보(제목, 내용)
	 * @param upload 업로드 파일
	 * @param user 로그인 사용자 정보
	 * @return 게시판 글 목록
	 * MultipartFile
	 *  - 스프링에서 파일 업로드를 처리할 때 사용하는 인터페이스
	 *  -HTTP 요청으로 전송으로 요청되는 업로드 파일을 스프링에서 객체로 매핑
	 * */
	@PostMapping("write")
	public String write(BoardDTO boardDTO, 
			@RequestParam(name="upload", required = false) MultipartFile upload,
			@AuthenticationPrincipal UserDetails user
			) {
		boardDTO.setMemberId(user.getUsername());
		log.debug("board info:{}",boardDTO);
		log.debug("upload info :{}", upload.getOriginalFilename());
		log.debug("file size:{}",upload.getSize());
		log.debug("is fileEmpty:{}",upload.isEmpty());
		
		try {
			bs.write(boardDTO,uploadPath,upload);
			return "redirect:list";
		}
		catch(Exception e) {
			log.debug("[exception] fail to upload]");
			return "board/writeForm";
		}
	
	}
}
