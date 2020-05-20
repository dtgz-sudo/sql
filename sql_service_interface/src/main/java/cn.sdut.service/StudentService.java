package cn.sdut.service;

import cn.sdut.domain.Answer;
import cn.sdut.domain.Category;

import java.sql.SQLException;

/**
 * Teacher 服务层
 * @author 赵德锋
 * @Transactional 平台事务管理器管理本平台 该注解只能放在实现类和 方法上
 * @Service 添加到ioc容器
 */

public interface StudentService {

    /**
     * 添加answer 并且打分
     * @param answer
     */
   public  void submitAnswer(Answer answer) throws SQLException;
}
