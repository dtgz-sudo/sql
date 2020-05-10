package cn.sdut.controller;

import cn.sdut.domain.Category;
import cn.sdut.domain.CategoryExample;
import cn.sdut.domain.Problem;
import cn.sdut.domain.Teacher;
import cn.sdut.entity.Result;
import cn.sdut.mapper.CategoryMapper;
import cn.sdut.mapper.StudentMapper;
import cn.sdut.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @RestController 返回的所有数据用 json返回
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    StudentMapper studentMapper;


    @RequestMapping("/findCatogy")
    public  Result findAllcategory(@RequestBody Integer cid) {
        System.out.println("findCatogy");
        Result result = null;
        try {
            Category category = categoryMapper.selectByPrimaryKey(cid);
            result = new Result(true, "查询分类成功", category);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(false, "查询分类失败");
        }

        return result;
    }
}
