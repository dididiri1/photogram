package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"imageId", "userId"}
                )
        }
)
public class Likes { // N, N

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 무한참조됨.
    @JoinColumn(name = "imageId")
    @ManyToOne
    private Image image; // 1

    // 오류가 터지고 나서 잡아봅시다.
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user; // 1

    private LocalDateTime createDate;

    @PrePersist
    public void creatDate() {
        this.createDate = LocalDateTime.now();
    }
}
