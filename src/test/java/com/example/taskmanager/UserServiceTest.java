package com.example.taskmanager;

import com.example.taskmanager.dao.IUserRepository;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.IUserService;
import com.example.taskmanager.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test(expected= UsernameNotFoundException.class)
    public void whenFindUserByLogin_shouldThrowException(){
         given(userRepository.findByLogin("testvalue")).willReturn(null);
          userService.findUserByLogin("testvalue");
    }
    @Test
    public void whenFindUserByLogin_shouldReturnUser(){
        User user = new User();
        given(userRepository.findByLogin("testvalue")).willReturn(user);
        assertThat(userService.findUserByLogin("testvalue")).isNotNull();
    }
    @Test
    public void whenFindUsers_shouldReturnList(){
        User user = new User();
        List<User> list = new ArrayList<>();
        list.add(user);
        given(userRepository.findAll()).willReturn(list);
        assertThat(userService.findAllUsers()).isNotNull();
    }

}
