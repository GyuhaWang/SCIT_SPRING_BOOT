package net.dsa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j// log 사용합니다.
@RequestMapping("cookie")
public class CookieController {

		/*
		 * [cookie]
		 * 클라이언트에 저장되는 데이터
		 
		 * 저장 위치: 클라이언트의 웹 브라우저에 저장
		 * 크기제한: 일반적으로 하나의 쿠키는 4kb 이하의 데이터 저장
		 * 유효기간:쿠키에는 만료기간이 표함됨 -> 자동 삭제
		 * 보아니 쿠키는 텍스트 형식으로 저장되므오 보안에 취약함
 
		 */
		@GetMapping("cookie1")
		public String cookie1(HttpServletResponse response) {
			Cookie c1 = new Cookie("str","abcd");
			Cookie c2 = new Cookie("num","12345");
			
			// 쿠키 수명 setMaxAge(seconds) 
			c1.setMaxAge(60*60*24*3);
			c2.setMaxAge(60*60*24*3);
			
			response.addCookie(c1);
			response.addCookie(c2);
			return "redirect:/";
		}
		@GetMapping("cookie2")
		public String cookie2(HttpServletResponse response) {
			Cookie c1 = new Cookie("str",null);
			Cookie c2 = new Cookie("num",null);
			
			c1.setMaxAge(0);
			c2.setMaxAge(0);
			response.addCookie(c1);
			response.addCookie(c2);
			return "redirect:/";
		}
		@GetMapping("cookie3")
		public String cookie3(@CookieValue(name="str",defaultValue="null")String str,
				@CookieValue(name="num",defaultValue="0")int num				
				) {
			log.debug("cookie value str:{},num:{}",str,num );
			return "redirect:/";
		}
}
