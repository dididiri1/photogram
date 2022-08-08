package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.hanlder.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor // final 필드 DI 할때 사용 (final의 모든 생성자를 만들어줌)
@Controller // 1. IoC 2. 파일을 리턴하는 컨트롤러
public class AuthController {

    private final AuthService authService;

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
    public String signup(@ModelAttribute @Valid SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String,String> errorMap = new HashMap<>();
            for (FieldError error: bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패함",errorMap);
        } else {
            User user = signupDto.toEntity();
            User userEntity = authService.save(user);
            System.out.println(userEntity);

            return "auth/signin";
        }
    }
}
