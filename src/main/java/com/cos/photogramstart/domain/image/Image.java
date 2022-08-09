package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image { // N, 1

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption; // 오늘 나 너무 피곤해!!!!
    private String postImageUrl; // 사진을 전송받아서 그 사진을 서버에 트겆ㅇ 폴더에 저장 - DB에 그 저장된 경로를 insert

    @JoinColumn(name="userId")
    @ManyToOne
    private User user; // 1, 1

    // 이미지 좋아요

    // 댓글

    private LocalDateTime creatDate;

    @PrePersist
    public void creatDate() {
        this.creatDate = LocalDateTime.now();
    }

}
