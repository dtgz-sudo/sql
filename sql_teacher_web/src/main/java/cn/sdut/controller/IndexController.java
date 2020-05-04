package cn.sdut.controller;

import cn.sdut.domain.Teacher;
import cn.sdut.entity.Result;
import cn.sdut.mapper.TeacherMapper;
import cn.sdut.service.TeacherService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 登陆成功面用的的 controller
 */
@RestController
@RequestMapping("index")
public class IndexController {

    private TeacherService teacherService;
    /**
     * 返回当前用户的用户名
     *
     * @return
     */
    @RequestMapping("findLoginUser")
    public Result findLoginname() {
        Result result = null;

        try {



            // SecurityContextHolder 维护 SecurityContext
            // 调用静态方法 getContext 返回springSecurity 静态对象
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            String name = authentication.getName();
            // 根据用户名获取到老师的全部数据
           Teacher teacher =  teacherService.findByName(name);
            result = new Result(true, "获取用户失败", teacher);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "获取用户失败");
        }
        return result;
    }
}
