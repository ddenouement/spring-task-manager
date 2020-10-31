package com.example.taskmanager;

import com.example.taskmanager.dao.IUserRepository;
import com.example.taskmanager.model.Role;
import com.example.taskmanager.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    IUserRepository repository;


    User data;

    @Before
    public void insertData(){
        data =   new User();
        data.setEmail("testuser@gamil.com");
        data.setFirstName("Testname");
        data.setLastName("Testlastname");
        data.setPassword("testpassword");
        data.setLogin("testlogin");
        data.setRole(Role.USER);
        repository.save(data);
    }
    @After
    public void clearData(){
       repository.delete(data);
    }

    @Test
    public void whenFindByLogin_shouldReturnUserWithSameLogin(){
        User user = repository.findByLogin(data.getLogin());
        Assert.assertEquals(user.getLogin(), data.getLogin());
    }



}
