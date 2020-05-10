package cn.sdut.controller;

import cn.sdut.domain.Category;
import cn.sdut.domain.Problem;
import cn.sdut.domain.ProblemExample;
import cn.sdut.entity.Result;
import cn.sdut.mapper.CategoryMapper;
import cn.sdut.mapper.ProblemMapper;
import cn.sdut.mapper.StudentMapper;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @RestController 返回的所有数据用 json返回
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ProblemMapper problemMapper;


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
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "查询分类失败");
        }

        return result;
    }
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
}
