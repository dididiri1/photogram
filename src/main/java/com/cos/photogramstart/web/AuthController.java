package com.cos.photogramstart.web;

import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller // 1. loC 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	@GetMapping("/auth/signin")
	public String signinForm() {
		
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {

		return "auth/signup";
	}

	// 회원가입버튼 -> /auth/signup -> /auth/signin
	@PostMapping("/auth/signup")
	public String signup(@ModelAttribute SignupDto signupDto) {
		log.info(signupDto.toString());
		return "auth/signin";
	}
}
