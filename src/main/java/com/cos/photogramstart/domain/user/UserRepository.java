package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

// 어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자등으로 된다.
public interface UserRepository extends JpaRepositoryImplementation<User,Integer> {

}
