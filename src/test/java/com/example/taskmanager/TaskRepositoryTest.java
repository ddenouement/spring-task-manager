package com.example.taskmanager;
import com.example.taskmanager.dao.ITaskRepository;
import com.example.taskmanager.model.Status;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"/import_data.sql"})
public class TaskRepositoryTest {
    @Autowired
    ITaskRepository repository;


    @Test
    public void whenCountByStatusIn_shouldReturnOne() {
        Set<Status> st = new HashSet<>();
        st.add(Status.REQUESTED_ADD);
        Assert.assertEquals(1, (long) repository.countByStatusIn(st));
    }




}
