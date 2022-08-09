package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.image.ImageUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public void 사진업로드(ImageUpdateDto imageUpdateDto, PrincipalDetails principalDetails) {
        String imageFileName = System.nanoTime()+"_"+imageUpdateDto.getFile().getOriginalFilename(); // 1.jpg

        System.out.println("이미지 파일 이름"+imageFileName);
        

    }
}
