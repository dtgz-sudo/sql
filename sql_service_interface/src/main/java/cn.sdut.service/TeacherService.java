package cn.sdut.service;

import cn.sdut.domain.Answer;
import cn.sdut.domain.Problem;
import cn.sdut.domain.Teacher;
import cn.sdut.entity.Piedata;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
     * 查询指定老师未评论的问题
     * @param tid
     * @return
     */
   public List<Map> findCommontAnswer(Integer tid);

    /**
     * 提交评论
     * @param list
     */
 public    void updateComment(List<Answer> list);
    //    查询学生部分完成的人数 select count(*) as num from (select sid from (select count(*) as snum,sid from (select MAX(score),sid,pid from answer GROUP BY sid,pid)A GROUP BY sid)B where snum < (select count(*) as allnum from problem where tid = 1) and snum > 0)C
//  查询学生全部完成的人数select count(*) as num from (select sid from (select count(*) as snum,sid from (select MAX(score),sid,pid from answer GROUP BY sid,pid)A GROUP BY sid)B where snum >= (select count(*) as allnum from problem where tid = 1))C

    public Piedata findpiedata(int tid) throws SQLException;
}
