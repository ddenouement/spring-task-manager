package com.example.taskmanager;


import com.example.taskmanager.dao.IActivityRepository;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"/import_data.sql"})
public class ActivityRepositoryTest {
    @Autowired
    IActivityRepository repository;


    @Test
    public void whenCountByEnabledTrue_shouldReturnOne() {
        Assert.assertEquals(1, repository.countByEnabledTrue());

    }

    @Test
    public void whenCountByCategories_shouldReturnOne() {
        List<Integer> c = new ArrayList();
        c.add(1);
        Assert.assertEquals(1, repository.countByCategories_IdIn(c));
    }

    @Test
    public void whenCountByCategoriesAndEnabledTrue_shouldReturnOne() {
        List<Integer> c = new ArrayList();
        c.add(1);
        Assert.assertEquals(1, repository.countByEnabledTrueAndCategories_IdIn(c));
    }

    @Test
    public void whenFindByEnabledTrue_shouldReturnActivity() {
        Pageable pageable = PageRequest.of(0, 8);
        Assert.assertNotNull(repository.findByEnabledTrue(pageable));

    }

    @Test
    public void whenFindByCategories_shouldReturnActivity() {
        List<Integer> c = new ArrayList();
        c.add(1);
        Pageable pageable = PageRequest.of(0, 8);
        Assert.assertNotNull(repository.findByCategories_IdIn(c, pageable));
    }

    @Test
    public void whenFindByCategoriesAndEnabledTrue_shouldReturnActivity() {
        List<Integer> c = new ArrayList();
        c.add(1);
        Pageable pageable = PageRequest.of(0, 8);
        Assert.assertNotNull(repository.findByEnabledTrueAndCategories_IdIn(c, pageable));
    }


}
