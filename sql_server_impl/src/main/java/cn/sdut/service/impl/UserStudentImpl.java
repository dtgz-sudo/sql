package cn.sdut.service.impl;

import cn.sdut.domain.Student;
import cn.sdut.domain.StudentExample;
import cn.sdut.domain.Teacher;
import cn.sdut.domain.TeacherExample;
import cn.sdut.mapper.StudentMapper;
import cn.sdut.mapper.TeacherMapper;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限校验
 */

@Service
public class UserStudentImpl implements UserDetailsService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     *
     *   实现SpringSecurity 的接口UserDetailsService
     *  从数据库查询数据封装到UserDetails 返回给委派过滤器 验证
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        // 利用username组合查询
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andNicknameEqualTo(username);
        List<Student> students = studentMapper.selectByExample(studentExample);
        System.out.println(students);
        if(students == null || students.size() ==0 )
        {
            return  null;
        }
        else
        {
            Student student = students.get(0);
//            添加权限
            List<GrantedAuthority> list = new ArrayList<>();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(student.getPermission());
            list.add(simpleGrantedAuthority);
            user = new User(username, student.getPassword(), list);

        }
        System.out.println("user :" + user);
        return user;
    }
}
