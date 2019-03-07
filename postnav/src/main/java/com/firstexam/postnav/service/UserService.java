package com.firstexam.postnav.service;

import com.firstexam.postnav.domain.User;
import com.firstexam.postnav.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserByNickName(String nickName){
        return userRepository.findUserByNickName(nickName);
    }

    @Transactional(readOnly = true)
    public User findUserById(long id){
        return userRepository.findUserById(id);
    }

}
