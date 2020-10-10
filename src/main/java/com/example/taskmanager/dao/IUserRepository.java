package com.example.taskmanager.dao;

import com.example.taskmanager.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface IUserRepository  extends CrudRepository<User, Integer> {
User findByLogin(String login);

//    List<User> getAllUsers();
 //      User insertUser(User user);
   // List<User> findByRoleIdNot(int i);
}
