import cn.sdut.mapper.TeacherMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")

public class mybatistest {

    @Autowired
    TeacherMapper teacherMapper;

    @Test
    public void testInit()
    {
        System.out.println(teacherMapper    );
    }
}
