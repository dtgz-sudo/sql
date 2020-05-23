package cn.sdut.service;

import cn.sdut.domain.Answer;
import cn.sdut.domain.Category;
import cn.sdut.domain.Problem;

import java.sql.SQLException;
import java.util.List;

/**
 * Teacher 服务层
 * @author 赵德锋
 * @Transactional 平台事务管理器管理本平台 该注解只能放在实现类和 方法上
 * @Service 添加到ioc容器
 */

public interface StudentService {

    /**
     * 添加answer 并且打分
     *
     * @param answer
     */
    public void submitAnswer(Answer answer) throws SQLException;

    /**
     * 查询cid对应的分类
     *
     * @param cid
     * @return
     */
    public Category findAllcategory(Integer cid);

    /**
     * 根据tid 和tid查询对应的问题分裂
     *
     * @param cid
     * @param tid
     * @return
     */
    public Category findCatogyByCidAndTid(Integer cid, Integer tid);

    /**
     * 查询学生id为CId 的学生回答的所有问题
     *
     * @param sid
     * @return
     */
    public List<Answer> findByAnswerBySid(Integer sid);

    /**
     * 根据问题ID PID 查询对应的problem
     *
     * @param pid
     * @return
     */
    public Problem selectProblemByPid(Integer pid);
}
