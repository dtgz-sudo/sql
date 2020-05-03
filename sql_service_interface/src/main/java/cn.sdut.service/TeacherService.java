package cn.sdut.service;

import cn.sdut.domain.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Teacher 服务层
 * @author 赵德锋
 * @Transactional 平台事务管理器管理本平台 该注解只能放在实现类和 方法上
 * @Service 添加到ioc容器
 */

public interface TeacherService {
    // 教室登录 根据用户名 密码
   public Teacher login(Teacher teacher);
}
