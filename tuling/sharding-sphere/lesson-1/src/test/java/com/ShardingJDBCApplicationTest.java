package com;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.Course;
import com.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingJDBCApplicationTest {
    @Resource
    private CourseMapper mapper;

    @Test
    public void singleDatabaseMultiTableAddCourseTest() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCourseName("java");
            course.setUserId(100L);
            course.setCourseStatus("1");

            mapper.insert(course);
        }
    }

    @Test
    public void singleDatabaseMultiTableQueryCourseTest() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1614815867432894468L);
        for (Course course : mapper.selectList(wrapper)) {
            System.out.println(course);
        }
    }

    @Test
    public void multiDatabaseMultiTableAddCourseTest() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCourseName("java");
            course.setUserId(100L);
            course.setCourseStatus("1");

            mapper.insert(course);
        }
    }

    @Test
    public void multiDatabaseMultiTableRangeQueryCourseTest() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("id",1615594499545833473L,1615594500963508228L);
        List<Course> courses = mapper.selectList(wrapper);
        courses.forEach(course -> System.out.println(course));
    }
}
