package cn.sdut.controller;


import cn.sdut.domain.Teacher;
import cn.sdut.entity.Result;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 登陆成功面用的的 controller
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private TeacherService teacherService;

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

}
