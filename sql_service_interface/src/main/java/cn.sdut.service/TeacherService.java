package cn.sdut.service;

import cn.sdut.domain.Category;
import cn.sdut.domain.Problem;
import cn.sdut.domain.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Teacher 服务层
 * @author 赵德锋
 * @Transactional 平台事务管理器管理本平台 该注解只能放在实现类和 方法上
 * @Service 添加到ioc容器
 */

public interface TeacherService {
    // 教室登录 根据用户名 密码
   public Teacher login(Teacher teacher);

    /**
     * 异步的检查老师输入的SQL语句是否正确
     * @param problem
     * @return
     */
   public String inputSql(Problem problem) throws SQLException;

    /**
     * 数据库添加 一个新的题目
     * @param problem
     */
   public void add(Problem problem);

    /**
     * 根据用户名获取队形
     * @return
     */
    public Teacher findByName(String nickName);
    // 导入学生的名单
     public void importstudent(MultipartFile mFile,int tid) throws Exception;
//     获取学生答题整体情况
    public List findalldata(int tid) throws SQLException;

    /**
     * 获取全部分类
     * @return
     */
  public   List<Category> findAllCategory();
}
