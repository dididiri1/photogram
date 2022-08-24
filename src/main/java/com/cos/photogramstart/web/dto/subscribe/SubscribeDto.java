package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {

    private Integer id;
    private String userid;
    private String profileImageUrl;
    private BigInteger subscribeState;
    private BigInteger equalUserState;

}
