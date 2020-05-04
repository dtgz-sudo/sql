package cn.sdut.controller;

import cn.sdut.domain.Teacher;
import cn.sdut.domain.TeacherExample;
import cn.sdut.mapper.TeacherMapper;
import cn.sdut.service.TeacherService;

import com.alibaba.dubbo.config.annotation.Reference;
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
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TeacherMapper tetacherMapper;
    private TeacherService teacherService;

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
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andNicknameEqualTo(username);
        List<Teacher> teachers = tetacherMapper.selectByExample(teacherExample);
        System.out.println(teachers);
        if(teachers == null || teachers.size() ==0 )
        {
            return  null;
        }
        else
        {
            Teacher teacher = teachers.get(0);
//            添加权限
            List<GrantedAuthority> list = new ArrayList<>();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(teacher.getPermission());
            list.add(simpleGrantedAuthority);
            user = new User(username, teacher.getPassword(), list);

        }
        System.out.println("user :" + user);
        return user;
    }
}
