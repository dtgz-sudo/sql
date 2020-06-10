package cn.sdut.controller;


import cn.sdut.domain.*;
import cn.sdut.entity.Result;
import cn.sdut.mapper.ClassesMapper;
import cn.sdut.mapper.HeadMapper;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * 登陆成功面用的的 controller
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private HeadMapper headMapper;
    @Autowired
    private ClassesMapper classesMapper;
    /**
     * 返回当前用户的用户名
     *
     * @return
     */
    @RequestMapping("/findLoginUser")
    public Result findLoginname() {
        Result result = null;

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);
            if ( !( authentication instanceof AnonymousAuthenticationToken ) ) {
                String currentUserName = authentication.getName();
                System.out.println(authentication);


                String name = authentication.getName();
                // 根据用户名获取到老师的全部数据
                Teacher teacher = teacherService.findByName(name);
                result = new Result(true, "获取用户成功",teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();

            result = new Result(false, "获取用户失败");
        }
        return result;
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        System.out.println(principal);
        return principal.getName();
    }
    @RequestMapping("/findallstudent")
    public Result findallstudent(@RequestParam(value = "class_id")String class_id) {
        Result result = null;
        System.out.println("findallstudent");
        System.out.println(class_id);
        int int_class_id = Integer.parseInt(class_id);
        List findallstudent = teacherService.findallstudent(int_class_id);
        for (Object o : findallstudent) {
            System.out.println(o);
        }
        result = new Result(true, "获取成功",findallstudent);
        return result;
    }
    //获取该教师所有头次
    @RequestMapping("/findallhead")
    public Result findallhead(@RequestParam(value = "tid")String tid) {
        Result result = null;
        System.out.println(tid);
        int int_tid = Integer.parseInt(tid);
        HeadExample headExample = new HeadExample();
        HeadExample.Criteria criteria = headExample.createCriteria();
        criteria.andTidEqualTo(int_tid);
        List<Head> heads = headMapper.selectByExample(headExample);
        result = new Result(true, "获取成功",heads);
        return result;
    }
    //获取头次下所有班级
    @RequestMapping("/findallclasses")
    public Result findallclasses(@RequestParam(value = "hid")String hid) {
        Result result = null;
        System.out.println(hid);
        int int_hid = Integer.parseInt(hid);
        ClassesExample classesExample = new ClassesExample();
        ClassesExample.Criteria criteria = classesExample.createCriteria();
        criteria.andHidEqualTo(int_hid);
        List<Classes> classes = classesMapper.selectByExample(classesExample);
        for (Classes aClass : classes) {
            System.out.println(aClass);
        }
        result = new Result(true, "获取成功",classes);
        return result;
    }
}
