package cn.sdut.controller;

import cn.sdut.domain.*;
import cn.sdut.entity.Result;
import cn.sdut.mapper.AnswerMapper;
import cn.sdut.mapper.CategoryMapper;
import cn.sdut.mapper.ProblemMapper;
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
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private CategoryMapper categoryMapper;


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
            Category category = categoryMapper.selectByPrimaryKey(cid);
            ProblemExample problemExample = new ProblemExample();
            ProblemExample.Criteria criteria = problemExample.createCriteria();
            criteria.andCidEqualTo(cid);
            List<Problem> problems = problemMapper.selectByExample(problemExample);
            category.setProblemList(problems);
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
    public Result findCatogyByTeacher(@PathVariable("cid") Integer cid,@PathVariable("tid") Integer tid) {
        System.out.println("findCatogyByTeacheran");
        Result result = null;
        try {
            Category category = categoryMapper.selectByPrimaryKey(cid);
            ProblemExample problemExample = new ProblemExample();
            ProblemExample.Criteria criteria = problemExample.createCriteria();
            criteria.andCidEqualTo(cid);
            criteria.andTidEqualTo(tid);
            List<Problem> problems = problemMapper.selectByExample(problemExample);
            category.setProblemList(problems);
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
            AnswerExample answerExample = new AnswerExample();
            AnswerExample.Criteria criteria = answerExample.createCriteria();
            criteria.andSidEqualTo(sid);
            List<Answer> answers = answerMapper.selectByExample(answerExample);
            for (Answer answer : answers) {
                Integer pid = answer.getPid();
                Problem problem = problemMapper.selectByPrimaryKey(pid);
                answer.setProblem(problem);
            }
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

            Problem problem = problemMapper.selectByPrimaryKey(pid);
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
            result = new Result(true, "本题得分:"+answer.getScore());
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "提交答案失败");
        }
        return result;
    }
}

