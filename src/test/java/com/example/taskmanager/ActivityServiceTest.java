package com.example.taskmanager;


import com.example.taskmanager.dao.IActivityRepository;
import com.example.taskmanager.service.impl.ActivityServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest {

    @Mock
    private IActivityRepository activityRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;



}
