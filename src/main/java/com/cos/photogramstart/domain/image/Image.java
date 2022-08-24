package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @JsonIgnoreProperties("{images}")
    @JoinColumn(name="userId")
    @ManyToOne(fetch = FetchType.EAGER) // 이미지를 select하면 조인해서 User정보를 같이 들고옴.
    private User user; // 1, 1

    // 이미지 좋아요
    @JsonIgnoreProperties("{image}")
    @OneToMany(mappedBy = "image")
    private List<Likes> likes;

    // 댓글

    private LocalDateTime creatDate;

    @Transient // DB에 칼럼이 만들어지지 않는다.
    private boolean likeState;

    @PrePersist
    public void creatDate() {
        this.creatDate = LocalDateTime.now();
    }

}
