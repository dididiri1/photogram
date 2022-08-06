package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service // Ioc
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 1. 패스워드는 알아서 체킹하니깐 신경쓸 필요 없다.
    // 2. 리턴이 잘되면 자동으로 세션을 만든다.


    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUserid(userid);

        if(userEntity == null){
            return null;
        } else {
            return new PrincipalDetails(userEntity);
        }

    }
}
