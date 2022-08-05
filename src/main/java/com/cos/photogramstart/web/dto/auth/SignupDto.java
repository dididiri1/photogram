package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

@Data // Getter, Setter
public class SignupDto {

    private String userid;
    private String password;
    private String email;
    private String name;

    public User toEntity() {

        return User.builder()
                .userid(userid)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
