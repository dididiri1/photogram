package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.hanlder.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User update(int id, User user) {
        // 1. 영속화
        // 1-1 1. 무조건 찾았다 2. 못찾아서 익센션 발동 시킴 orElseThrow()

        //User userEntity = userRepository.findById(id).get();

        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            return new CustomValidationApiException("찾을수 없는 id입니다.");
        });

        userEntity.setName(user.getName());
        //String bCryptPasswordEncoder.encode(user.getPassword());

        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setBio(user.getBio());
        userEntity.setWebSite(user.getWebSite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        // 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)

        return userEntity;
    }
}
