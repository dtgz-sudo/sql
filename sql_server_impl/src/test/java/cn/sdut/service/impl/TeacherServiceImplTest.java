package cn.sdut.service.impl;

import cn.sdut.domain.Teacher;
import cn.sdut.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")

public class TeacherServiceImplTest {

    @Autowired
    TeacherService teacherService;

    @Test
    public void testInit()
    {
        System.out.println(teacherService);
    }
    @Test
    public void Login()
    {
        Teacher teacher = new Teacher();
        teacher.setNickname("admin");
        teacher.setPassword("admin");
        Teacher login = teacherService.login(teacher);
        System.out.println(login);

    }
}