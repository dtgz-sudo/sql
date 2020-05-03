package cn.sdut.controller;

import cn.sdut.domain.Teacher;
import cn.sdut.entity.Result;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 返回的所有数据用 json返回
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**
     * 教室登录的controller
     * @param teacher
     * @return
     */
    @RequestMapping("/login")
    public Result login(@RequestBody Teacher teacher)
    {
        Result result = null;
        try {
             teacher  = teacherService.login(teacher);

            result = new Result(true,"登录成功",teacher);
        } catch (Exception e) {
            e.printStackTrace();
             result = new Result(false,"登录失败");
        }
        return result;
    }
}
