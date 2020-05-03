package cn.sdut.service.impl;

import cn.sdut.domain.Teacher;
import cn.sdut.domain.TeacherExample;
import cn.sdut.mapper.TeacherMapper;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Teacher 服务层
 * @author 赵德锋
 * @Transactional 平台事务管理器管理本平台
 * @Service 添加到ioc容器
 */

@Transactional(isolation = Isolation.DEFAULT)
@Service
public class TeacherServiceImpl  implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 登录
     * @param teacher
     */
    @Override
    public Teacher login(Teacher teacher) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andNicknameEqualTo(teacher.getNickname());
        criteria.andPasswordEqualTo(teacher.getPassword());
        List<Teacher> list = teacherMapper.selectByExample(teacherExample);
        if(list ==null || list.size() == 0)
        {
            throw new  NullPointerException();

        }
        else
        {
            return  list.get(0);
        }
    }
}
/**

 org.springframework.beans.factory.BeanCreationException: Error creating bean with name
 'cn.sdut.service.impl.TeacherServiceImplTest': Injection of autowired dependencies failed;
 nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field:
 cn.sdut.service.impl.TeacherServiceImpl cn.sdut.service.impl.TeacherServiceImplTest.teacherService;
 nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
 No qualifying bean of type [cn.sdut.service.impl.TeacherServiceImpl] found for dependency:
 expected at least 1 bean which qualifies as autowire candidate for this dependency. D
 ependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}


 **/