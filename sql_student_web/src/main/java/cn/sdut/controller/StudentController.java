package cn.sdut.controller;

import cn.sdut.domain.Answer;
import cn.sdut.domain.Category;
import cn.sdut.domain.Problem;
import cn.sdut.domain.ProblemExample;
import cn.sdut.entity.Result;
import cn.sdut.mapper.AnswerMapper;
import cn.sdut.mapper.CategoryMapper;
import cn.sdut.mapper.ProblemMapper;
import cn.sdut.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
            System.out.println(category.getProblemsList());
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询分类失败");
        }

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
            result = new Result(true, "提交答案成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "提交答案成功");
        }
        return result;
    }
}

/**
 *
 * 老师 请问研招网的调剂信息 什么时候可以查看完毕啊
 */
