package cn.sdut.controller;

import cn.sdut.domain.Answer;
import cn.sdut.domain.Category;
import cn.sdut.domain.Problem;
import cn.sdut.domain.Teacher;
import cn.sdut.entity.Alldata;
import cn.sdut.entity.Piedata;
import cn.sdut.entity.Result;
import cn.sdut.mapper.CategoryMapper;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据老师的id查询此老师全部未回答的学生问题
     *
     * @param tid
     * @param
     * @return
     */
    @RequestMapping("/findCommontAnswer/{tid}")
    public Result findCommontAnswer(@PathVariable(value = "tid") Integer tid) {
        System.out.println("*****************************");
        Result result = null;

        try {
            List<Map> list = teacherService.findCommontAnswer(tid);
            result = new Result(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询失败");
        }
        return result;
    }

    /**
     * 提交 老师输入的评论 并且返回下一次评论的数据
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping("/submitComment/{tid}")
    public Result submitComment(@RequestBody List<Answer> list, @PathVariable("tid") Integer tid) {
        System.out.println("*****************************");
        System.out.println(list);
        Result result = null;

        try {
            teacherService.updateComment(list);
            List<Map> commontAnswer = teacherService.findCommontAnswer(tid);
            result = new Result(true, "查询成功", commontAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询失败");
        }
        return result;
    }

    @RequestMapping("/findpiedata")
    public <body> Result findpiedata(@RequestParam(value = "tid")String tid) {
        System.out.println("*****************************");
        System.out.println("findpiedata");
        Result result = null;
        System.out.println(tid);
        int int_tid = Integer.parseInt(tid);
        try {
            Piedata piedata = teacherService.findpiedata(int_tid);
            result = new Result(true, "查询成功", piedata);
        } catch (SQLException e) {
            e.printStackTrace();
            result = new Result(false, "查询失败");
        }

        return result;
    }
    @RequestMapping("/findpersonaldata")
    public <body> Result findpersonaldata(@RequestParam(value = "sid")String sid){
        System.out.println("findpersonaldata");
        System.out.println(sid);
        int int_sid = Integer.parseInt(sid);
        List listdata = null;
        Result result = null;
        try {
            listdata = teacherService.findpersonaldata(int_sid);
            result = new Result(true, "查询成功", listdata);
        } catch (SQLException e) {
            e.printStackTrace();
            result = new Result(false, "查询失败");
        }
        return result;
    }
    /**
     * 驗證輸入的密碼是否正確
     * @param obj
     * @return
     */
    @RequestMapping("/verifyPassword")
    public Result verifyPassword(@RequestBody Teacher obj) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("verifyPassword");
        System.out.println(obj);
        Result result = null;
        try {
            Integer sid = obj.getTid();
            String password = obj.getPassword();
            Teacher teacher = teacherService.findTeacherByTid(sid);
            String password1 = teacher.getPassword();
            if ( encoder.matches(password,password1) ) {
                result = new Result(true, "密码校验成功");
            } else {
                result = new Result(false, "密码校验失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "密码校验失败");
        }
        return result;
    }

    /**
     * 修改密码
     * @param obj
     * @return
     */
    @RequestMapping("/updatePassword")
    public Result updatePassword(@RequestBody Teacher obj) {
        System.out.println("verifyPassword");
        System.out.println(obj);
        Result result = null;
        try {
            String password = obj.getPassword();
            Integer tid = obj.getTid();
            Teacher teachher = teacherService.findTeacherByTid(tid);
            // 密码加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            password= encoder.encode(password);
            teachher.setPassword(password);
            teacherService.update(teachher);
            result = new Result(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "更新失败");
        }
        return result;
    }

    /**
     * 次老师全部历史题目
     *
     * @param tid
     * @param
     * @return
     */
    @RequestMapping("/findAllProblem/{tid}")
    public Result findAllProblem(@PathVariable(value = "tid") Integer tid) {

        System.out.println("*****************************");
        System.out.println("*********findAllProblem*********");
        Result result = null;

        try {
            List<Problem> list = teacherService.findAllProblemByTid(tid);
            result = new Result(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询失败");
        }
        return result;
    }

    @RequestMapping("/deleteProblem/{pid}")
    public Result deleteProblem(@PathVariable(value = "pid") Integer pid) {
        System.out.println("*****************************");
        System.out.println("*********deleteProblem*********");
        Result result = null;

        try {
            teacherService.deleteProblemByPid(pid);
            result = new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "删除失败");
        }
        return result;
    }



}
