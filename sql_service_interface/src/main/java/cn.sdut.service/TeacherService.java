package cn.sdut.service;

import cn.sdut.domain.Teacher;

public interface TeacherService {
    // 教室登录 根据用户名 密码
   public Teacher login(Teacher teacher);
}
