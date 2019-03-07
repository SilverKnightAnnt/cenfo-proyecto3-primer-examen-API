package com.firstexam.postnav.repository;

import com.firstexam.postnav.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByNickName(String nickName);
    User findUserById(long id);
}
