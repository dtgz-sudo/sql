package cn.sdut.service.impl;

import cn.sdut.ImportExcel;
import cn.sdut.domain.Problem;
import cn.sdut.domain.Student;
import cn.sdut.domain.Teacher;
import cn.sdut.domain.TeacherExample;
import cn.sdut.entity.Result;
import cn.sdut.mapper.ProblemMapper;
import cn.sdut.mapper.StudentMapper;
import cn.sdut.mapper.TeacherMapper;
import cn.sdut.service.TeacherService;
import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
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
    @Autowired
    StudentMapper studentMapper;
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
        System.out.println("problem" + problem);
        String sql = problem.getInput();
        Connection connection = null;
        connection= dataSource.getConnection();
        connection.setAutoCommit(false);
        String output = "";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(sql.toLowerCase().contains("select"))
            {
                //查询
                final ResultSet resultSet = preparedStatement.executeQuery();
                // 讲结果集封装到list集合中 并别返回到数据中
                List list = this.convertList(resultSet);
                //
               output=  JSONArray.toJSONString(list);

            }else
            {
                /**
                 * 增删改的策略：
                 *           保存sql语句的执行状态
                 *           并且保存执行sql语句之后数据库的状态
                 */
                Integer num  = preparedStatement.executeUpdate();
                String  qurry = "SELECT * FROM " ;
                // 获得需要操作的数据库 根据from拆分
                String[] sqlArray = sql.toLowerCase().split("from");
                // 获取到form以后的内容并且去除空格
                sql = sqlArray[1].trim();
                sqlArray= sql.split(" ");
                qurry += sqlArray[0];
                ResultSet resultSet = preparedStatement.executeQuery(qurry);
                List list = this.convertList(resultSet);
                Map<String ,Object> map = new HashMap();
                map.put("resultSet",list);
                map.put("num",num);
                output=  JSON.toJSONString(map);

            }

            } catch (SQLException throwables) {
                // 回滚操作
                connection.rollback();
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

    /**
     * 根据用户名获取队形
     *
     * @return
     */
    @Override
    public Teacher findByName(String  nickname) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andNicknameEqualTo(nickname);
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
        System.out.println("******************************************");
        System.out.println(teachers);
        System.out.println("******************************************");
        if(teachers == null || teachers.size() == 0)
        {
            return null;
        }
        else
        {
            return  teachers.get(0);
        }

    }


    /**
     * 封装结果集
     * @param rs
     * @return
     * @throws SQLException
     */
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
    //学生名单导入
    @Override
    public void importstudent(MultipartFile mFile) throws Exception {
        String fileName = mFile.getOriginalFilename();
        // 获取上传文件的输入流
        InputStream inputStream = null;
        inputStream = mFile.getInputStream();
        // 调用工具类中方法，读取excel文件中数据
        List<Map<String, Object>> sourceList = ImportExcel.readExcel(fileName, inputStream);
        for (Map<String, Object> studentMap : sourceList) {
            Student student = new Student();
            String password = (String)studentMap.get("password");
            String nickname = (String)studentMap.get("nickname");
            String email = (String)studentMap.get("email");
            String permission = (String)studentMap.get("permission");
            Date date = new Date();
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            String stringdate = sdf.format(date);
            Date date1 = sdf.parse(stringdate);
            student.setPassword(password);
            student.setCreatedate(date1);
            student.setNickname(nickname);
            student.setEmail(email);
            student.setPermission(permission);
            student.setTid(1);
            studentMapper.insert(student);
        }

    }
}

