package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.hanlder.ex.CustomApiException;
import com.cos.photogramstart.hanlder.ex.CustomException;
import com.cos.photogramstart.hanlder.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
        String imageFileName = System.nanoTime() + "_" + profileImageFile.getOriginalFilename(); // 1.jpg

        System.out.println("이미지 파일 이름" + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(principalId).orElseThrow(()->{
            throw new CustomApiException("유저를 찾을 수 없습니다.");
        });

        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;
    } // 더티체킹으로 업데이트 됨.

    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(int pageUserId, int principalId) {
        // SELECT * FROM image WHERE userId = :userId;

        UserProfileDto dto = new UserProfileDto();

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        //dto.setIsPageOwner(pageUserId == principalId ? 1 : -1); // 사막 연산자..
        dto.setPageOwnerState(pageUserId == principalId); // 1은 페이지 주인, -1은 주인이 아님
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeState(subscribeState == 1);
        dto.setSubscribeCount(subscribeCount);

        // 좋아요 카운트 추가하기
        userEntity.getImages().forEach((image) -> {
            image.setLikeCount(image.getLikes().size());
        });

        return dto;
    }

    @Transactional
    public User 회원수정(int id, User user) {
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
;