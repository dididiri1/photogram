package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data // Getter, Setter
public class SignupDto {

    @Size(min = 2, max = 20)
    @NotBlank
    private String userid;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
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
