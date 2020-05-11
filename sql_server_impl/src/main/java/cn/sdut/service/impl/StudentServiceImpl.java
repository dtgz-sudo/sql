package cn.sdut.service.impl;

import cn.sdut.domain.Answer;
import cn.sdut.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.DEFAULT)
@Service
public class StudentServiceImpl  implements StudentService {
    /**
     * 添加answer 并且打分
     *
     * @param answer
     */
    @Override
    public void submitAnswer(Answer answer) {
        System.out.println("存储并且打分");
    }
}
