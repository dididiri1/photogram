package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service // 1. loC 2. 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional // Write (insert, Update, Delete)
    public User save(User user) {
        // 회원가입 진행
        String rawPassword = user.getPassword();
        String encPassowrd = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassowrd);
        user.setRole("ROLE_USER");
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
