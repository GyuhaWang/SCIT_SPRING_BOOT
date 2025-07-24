package net.dsa.web5.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping({"","/"})
	public String Home() {
		return "home";
	}
	//application.properties 에서 설정한 value
	@Value("${board.uploadPath}")
	String uploadPath;
	
	@GetMapping("file")
	public String File() {
		
		/*
		 * file
		 * exist()
		 * isFile()
		 * isDirectory()
		 * mkdir()
		 * createNewFile()
		 * delete()
		 * getName()
		 * getPath,getAbsolutePath()
		 * */
		log.debug("uploadPath : {}",uploadPath);
		File file = new File(uploadPath+"/"+"example.txt");
		File dir = new File(uploadPath+"/"+"myfolder");
		try {
			if(!file.exists()) {
				boolean created = file.createNewFile();
				log.debug("파일 생성 여부 :{}",created);
			}
			else {
				log.debug("파일이 이미 존재합니다.");
				
			}
			// create dir
			if(!dir.exists()) {
				boolean dirCreated = dir.mkdir();
				log.debug("폴더 생성 여부:",dirCreated);
			}
			else {
				log.debug("폴더가 이미 존재합니다.");
			}
			// 4. 파일 이름과 경로 출력
			log.debug("파일 이름: {}",file.getName());
			log.debug("파일 경로: {}",file.getAbsolutePath());
		}
		catch(IOException e) {
		e.printStackTrace();
		}
		return "redirect:/";
	}
	
}
