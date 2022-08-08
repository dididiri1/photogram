package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface SubscribeRepository extends JpaRepositoryImplementation<Subscribe, Integer> {

    @Modifying // INSERT, DELETE , UPDATE 를 네이트브 쿼리로 작성하려면 해당 어노테이션 필요!
    @Query(value = "INSERT INTO Subscribe(fromUserId, toUserId,creatDate) VALUES (:fromUserId,:toUserId,now())", nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId); // 성공 : 1(변경된 행의 개수가 리턴됨, 실패 -1 , 0 변경된게 없을때..

    @Modifying
    @Query(value = "DELETE FROM Subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);


}
