package com.cos.photogramstart.web.image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUpdateDto {

    private MultipartFile file;
    private String caption;
}
