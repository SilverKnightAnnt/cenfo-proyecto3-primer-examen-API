package com.firstexam.postnav.repository;

import com.firstexam.postnav.domain.Post;
import com.firstexam.postnav.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserOrderByTimeStampDesc(User user);
    List<Post> findByOrderByTimeStampDesc();
}
