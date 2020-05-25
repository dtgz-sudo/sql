package cn.sdut.controller;

import cn.sdut.domain.Student;
import cn.sdut.domain.StudentExample;
import cn.sdut.domain.Teacher;
import cn.sdut.entity.Result;
import cn.sdut.mapper.StudentMapper;
import cn.sdut.mapper.TeacherMapper;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ListIterator;

/**
 * 登陆成功面用的的 controller
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 返回当前用户的用户名
     *
     * @return
     */
    @RequestMapping("/findLoginUser")
    public Result findLoginname() {
        Result result = null;

        try {
            // SecurityContextHolder 维护 SecurityContext
            // 调用静态方法 getContext 返回springSecurity 静态对象
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication =context.getAuthentication();
            System.out.println(authentication);
            if(authentication == null ) {
                throw  new   NullPointerException("authentication == null");
            } else {
                String name = authentication.getName();
                // 根据用户名获取到老师的全部数据
                Teacher teacher = teacherService.findByName(name);
                result = new Result(true, "获取用户成功", teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();

            result = new Result(false, "获取用户失败");
        }
        return result;
    }

    /**
     * 返回当前用户的用户名
     *
     * @return
     */
    @RequestMapping("/findLoginTeacher")
    public Result findLoginTeacher() {
        Result result = null;

        try {
            // SecurityContextHolder 维护 SecurityContext
            // 调用静态方法 getContext 返回springSecurity 静态对象
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            System.out.println(authentication);
            if ( authentication == null ) {
                throw new NullPointerException("authentication == null");
            } else {
                String name = authentication.getName();
                // 根据用户名获取到老师的全部数据

                Teacher teacher = teacherService.findByName(name);
                result = new Result(true, "获取用户成功", teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();

            result = new Result(false, "获取用户失败");
        }
        return result;
    }

    /**
     * 返回当前用户的用户名
     *
     * @return
     */
    @RequestMapping("/findLoginStudent")
    public Result findLoginStudent() {
        Result result = null;

        try {
            // SecurityContextHolder 维护 SecurityContext
            // 调用静态方法 getContext 返回springSecurity 静态对象
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            System.out.println(authentication);
            if ( authentication == null ) {
                throw new NullPointerException("authentication == null");
            } else {
                String name = authentication.getName();
                // 根据用户名获取到老师的全部数据
                StudentExample studentExample = new StudentExample();
                StudentExample.Criteria criteria = studentExample.createCriteria();
                criteria.andNicknameEqualTo(name);
                List<Student> students = studentMapper.selectByExample(studentExample);
                ListIterator<Student> studentListIterator = students.listIterator();
                result = new Result(true, "获取用户成功", students.get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();

            result = new Result(false, "获取用户失败");
        }
        return result;
    }
}
