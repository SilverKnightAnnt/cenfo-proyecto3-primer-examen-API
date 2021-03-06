package com.firstexam.postnav.repository;

import com.firstexam.postnav.domain.Comment;
import com.firstexam.postnav.domain.Post;
import com.firstexam.postnav.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentByPost(Post post);
    List<Comment> findCommentByUser(User user);
}
