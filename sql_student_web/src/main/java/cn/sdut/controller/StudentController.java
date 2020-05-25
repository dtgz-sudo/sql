package cn.sdut.controller;

import cn.sdut.domain.Answer;
import cn.sdut.domain.Category;
import cn.sdut.domain.Problem;
import cn.sdut.domain.Student;
import cn.sdut.entity.Result;
import cn.sdut.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @RestController 返回的所有数据用 json返回
 * @author 赵德锋
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;



    /**
     * 获取学生的实验分类
     * @param cid
     * @return
     */
    @RequestMapping("/findCatogy")
    public Result findAllcategory(@RequestBody Integer cid) {
        System.out.println("findCatogy");
        Result result = null;
        try {
            Category category =  studentService.findAllcategory(cid);
            result = new Result(true, "查询分类成功", category);
            System.out.println(category.getProblemList());
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询分类失败");
        }

        return result;
    }
    /**
     * 获取学生的实验分类
     * 限制只能查询自己老师的题目
     * @param cid
     * @return
     */
    @RequestMapping("/findCatogyByTeacher/{cid}/{tid}")
    public Result findCatogyByTeacher(@PathVariable("cid") Integer cid, @PathVariable("tid") Integer tid) {
        System.out.println("findCatogyByTeacheran");
        Result result = null;
        try {
            Category category = studentService.findCatogyByCidAndTid(cid,tid);
            result = new Result(true, "查询分类成功", category);
            System.out.println(category.getProblemList());
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询分类失败");
        }
        return result;
    }
    /**
     * 查看学生历史答题情况
     * @param sid
     * @return
     */
    @RequestMapping("/findStudentAnswer/{sid}")
    public Result findStudentAnswer(@PathVariable("sid") Integer sid) {
        System.out.println("findStudentAnswer");
        Result result = null;
        try {
            List<Answer> answers =    studentService.findByAnswerBySid(sid);

            result = new Result(true, "查询历史答案", answers);
            System.out.println(answers);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询历史答案失败");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        return result;
    }
    /**
     * 根据前端传过来的pid查询对应的problem
     * @param pid
     * @return
     */
    @RequestMapping("/findByPid")
    public Result findByPid(@RequestBody Integer pid) {
        System.out.println("findCatogy");
        Result result = null;
        try {

            Problem problem = studentService.selectProblemByPid(pid);
            result = new Result(true, "查询分类成功", problem);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询分类失败");
        }

        return result;
    }


    /**
     * 学生提交答案
     * 后台分数判断
     * @param answer
     * @return
     */
    @RequestMapping("/sudmit")
    public Result sudmit(@RequestBody Answer answer) {
        System.out.println("sudmit");
        Result result = null;
        try {
            answer.setCreatedate(new Date());
            studentService.submitAnswer(answer);
            result = new Result(true, "本题得分:" + answer.getScore());
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "提交答案失败");
        }
        return result;
    }


    @RequestMapping("/verifyPassword")
    public Result verifyPassword(@RequestBody Student oldStudent) {
        System.out.println("verifyPassword");
        System.out.println(oldStudent);
        Result result = null;
        try {
            Integer sid = oldStudent.getSid();
            String password = oldStudent.getPassword();
            Student student = studentService.findStudentBySid(sid);
            if ( student.getPassword().equals(password) ) {
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

    @RequestMapping("/updatePassword")
    public Result updatePassword(@RequestBody Student student) {
        System.out.println("verifyPassword");
        System.out.println(student);
        Result result = null;
        try {
            String password = student.getPassword();
            student = studentService.findStudentBySid(student.getSid());
            student.setPassword(password);
            studentService.updateStudent(student);
            result = new Result(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "更新失败");
        }
        return result;
    }
}