package cn.sdut.service.impl;

import cn.sdut.EditDistance;
import cn.sdut.domain.Teacher;
import cn.sdut.service.TeacherService;

import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.sql.SQLException;

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
    @Test
    public void testfindalldata() throws SQLException {
        teacherService.findalldata(1);
    }
    @Test
    public void testfindpiedata() throws SQLException {
     //   teacherService.findpiedata(1);
    }
    @Test
    public void testfindpersonaldata() throws SQLException {
        //   teacherService.findpiedata(1);
        teacherService.findpersonaldata(1);
    }
    @Test
    public void test(){
        teacherService.findCommontAnswer(1);

    }
    public void testTestLogin() {
    }

    public void testInputSql() {
    }

    public void testAdd() {
    }

    public void testFindByName() {
    }
    @Test
    public void testSql()
    {
        String sql = " select * from  music l;jfdkl;aj;sfdkl  where id = 1 ";
        String[] sqlArray = sql.split("from");
        sql = sqlArray[1].trim();
        sqlArray= sql.split(" ");

        System.out.println(sqlArray[0]);

    }

}