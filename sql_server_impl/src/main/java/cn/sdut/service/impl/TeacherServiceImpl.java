package cn.sdut.service.impl;

import cn.sdut.domain.Problem;
import cn.sdut.domain.Teacher;
import cn.sdut.domain.TeacherExample;
import cn.sdut.mapper.ProblemMapper;
import cn.sdut.mapper.TeacherMapper;
import cn.sdut.service.TeacherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Teacher 服务层
 * @author 赵德锋
 * @Transactional 平台事务管理器管理本平台
 * @Service 添加到ioc容器
 */

@Transactional(isolation = Isolation.DEFAULT)
@Service
public class TeacherServiceImpl  implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    DataSource dataSource;
    @Autowired
    ProblemMapper problemMapper;

    /**
     * 登录
     * @param teacher
     */
    @Override
    public Teacher login(Teacher teacher) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andNicknameEqualTo(teacher.getNickname());
        criteria.andPasswordEqualTo(teacher.getPassword());
        List<Teacher> list = teacherMapper.selectByExample(teacherExample);
        if(list ==null || list.size() == 0)
        {
            throw new  NullPointerException();
        }
        else
        {
            return  list.get(0);
        }
    }

    /**
     * 异步的检查老师输入的SQL语句是否正确
     *
     * @param problem
     * @return
     */
    @Override
    public String inputSql(Problem problem) throws SQLException {
        String sql = problem.getInput();
        Connection connection = null;
        String output = "";
        try {
            connection= dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(sql.toLowerCase().contains("select"))
            {
                //查询
                final ResultSet resultSet = preparedStatement.executeQuery();
                // 讲结果集封装到list集合中 并别返回到数据中
                List list = this.convertList(resultSet);
               output=  JSONArray.toJSONString(list);

            }else
            {
                // 增删改
                Integer num  = preparedStatement.executeUpdate();
                output = num.toString();
            }

            } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  throwables;
        }
        finally {
            try {
                // 回滚操作
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw  throwables;
            }
        }
        System.out.println(output);
        return output;
    }

    /**
     * 数据库添加 一个新的题目
     *
     * @param problem
     */
    @Override
    public void add(Problem problem) {
        System.out.println(problem);
        problemMapper.insert(problem);

    }
    private  List convertList(ResultSet rs) throws SQLException{
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }
}

