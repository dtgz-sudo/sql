import cn.sdut.mapper.StudentMapper;
import cn.sdut.mapper.TeacherMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")

public class mybatistest {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    DataSource dataSource;

    @Test
    public void testInit() throws SQLException {
        System.out.println(dataSource);
        System.out.println(dataSource.getConnection());
        System.out.println(studentMapper.selectByExample(null));

       // System.out.println(teacherMapper.selectByExample(null)    );
    }
}
