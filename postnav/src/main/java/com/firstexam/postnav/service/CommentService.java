package com.firstexam.postnav.service;

import com.firstexam.postnav.domain.Comment;
import com.firstexam.postnav.domain.Post;
import com.firstexam.postnav.domain.User;
import com.firstexam.postnav.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Comment> findCommentByPost(Post post) {
        return commentRepository.findCommentByPost(post);
    }

    @Transactional(readOnly = true)
    public List<Comment> findCommentByUser(User user) {
        return commentRepository.findCommentByUser(user);
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }
}
