package com;

import com.entity.Course;
import com.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingJDBCApplicationTest {
    @Resource
    private CourseMapper mapper;

    @Test
    public void addCourseTest() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCourseName("java");
            course.setUserId(100L);
            course.setCourseStatus("1");

            mapper.insert(course);
        }
    }


}
