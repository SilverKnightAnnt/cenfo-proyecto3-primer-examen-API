package com.firstexam.postnav.repository;

import com.firstexam.postnav.domain.Likes;
import com.firstexam.postnav.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {
    List<Likes> findByUserOrderByIdDesc(User user);
}
