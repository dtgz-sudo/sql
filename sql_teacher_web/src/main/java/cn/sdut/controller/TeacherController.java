package cn.sdut.controller;

import cn.sdut.domain.Problem;
import cn.sdut.domain.Teacher;
import cn.sdut.entity.Result;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * @RestController 返回的所有数据用 json返回
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**
     * 老师登录的controller
     *
     * @param teacher
     * @return
     */
    @RequestMapping("/login")
    public Result login(@RequestBody Teacher teacher) {
        Result result = null;
        try {
            teacher = teacherService.login(teacher);

            result = new Result(true, "登录成功", teacher);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "登录失败");
        }
        return result;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Problem problem) {
        Result result = null;
        try {
           // teacherService.add(problem);
            problem.setCreatedate(new Date());
            problem.setLanguage("mysql");
            teacherService.add(problem);
            result = new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "添加失败");
        }
        return result;
    }

    @RequestMapping("/inputSql")
    public Result inputSql(@RequestBody Problem problem) {
        Result result = null;
        try {
            String output = teacherService.inputSql(problem);

            result = new Result(true, "输入正确", output);
        } catch (Exception e) {
            e.printStackTrace();
            if ( e instanceof NullPointerException ) {
                result = new Result(false, "数据库链接异常");
            } else if(e instanceof SQLException) {
                result = new Result(false, "sql语句异常");
            }

        }
        return result;
    }
    @RequestMapping("/test")
    public <body> Result test(@RequestBody Map map) {
        Result result = null;
        System.out.println(map);
        return result;
    }
}
