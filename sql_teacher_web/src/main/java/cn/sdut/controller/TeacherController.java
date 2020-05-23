package cn.sdut.controller;

import cn.sdut.domain.Category;
import cn.sdut.domain.Problem;
import cn.sdut.domain.Teacher;
import cn.sdut.entity.Alldata;
import cn.sdut.entity.Result;
import cn.sdut.mapper.CategoryMapper;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @RestController 返回的所有数据用 json返回
 * http://localhost:8080/teacher/findAllcategory
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CategoryMapper categoryMapper;

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
        System.out.println(problem);
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
        System.out.println(problem);
        Result result = null;
        System.out.println(problem);
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

    @RequestMapping("/findAllcategory")
    public <body> Result findAllcategory() {
        System.out.println("*****************************");
        System.out.println("findAllcategory");
        Result result = null;
        try {
            List<Category> categories = categoryMapper.selectByExample(null);
            System.out.println("category :" + categories);
            result = new Result(true, "查询分类成功", categories);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false, "查询分类失败");
        }

        return result;
    }

    /**
     *  根据excel表格 导入学生的数据
     * @param mFile
     * @return
     */
    @RequestMapping("/importStudent")
    public ModelAndView importStudent(@RequestParam(value = "file1") MultipartFile mFile,@RequestParam(value = "tid") String  tid) {
        System.out.println(tid  );
        System.out.println("*****************************");
        System.out.println("importStudent");
        Result result = null;
        int int_tid = Integer.parseInt(tid);
        try {
            teacherService.importstudent(mFile,int_tid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("importStudent");
        return mv;
    }



    @RequestMapping("/findalldata")
    public <body> Result finddata(@RequestParam(value = "tid")String tid) {
        System.out.println("*****************************");
        System.out.println("findalldata");
        Result result = null;
        System.out.println(tid);
        int int_tid = Integer.parseInt(tid);
        try {
            List<Alldata> findalldata = teacherService.findalldata(int_tid);
            result = new Result(true, "查询成功", findalldata);
        } catch (SQLException e) {
            e.printStackTrace();
            result = new Result(false, "查询失败");
        }
        return result;
    }
}
