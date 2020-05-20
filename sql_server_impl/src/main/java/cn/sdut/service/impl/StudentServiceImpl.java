package cn.sdut.service.impl;

import cn.sdut.domain.Answer;
import cn.sdut.domain.Problem;
import cn.sdut.mapper.AnswerMapper;
import cn.sdut.mapper.ProblemMapper;
import cn.sdut.mapper.StudentMapper;
import cn.sdut.service.StudentService;
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

@Transactional(isolation = Isolation.DEFAULT)
@Service
public class StudentServiceImpl implements StudentService {
    /**
     * 注入需要的mapper接口
     */
    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AnswerMapper answerMapper;
    /**
     * 注入连接池和数据链接
     */
    @Autowired
    private DataSource dataSource;

    private Connection connection;

    /**
     * 添加answer 并且打分
     * 注意要try catch
     * 打分逻辑
     * 1：先从数据库查询出老师输入的数据库和答案然后进行判断
     * 2: 如果是增删改 先操作数据库然后查询全部数据(默认升序) 比对数据如果全部正确满分
     * 如果 数据比对不正确 只要程序不报错先给 50分 然后判断关键（待更新） 最多80分
     * 3: 如果是查询数据的话先比对数据如果数据是正确的那么满分
     * 如果数据不正确 只要查询出数据那么先给50分 然后判断关键字 （待更新）最多80分
     *
     * @param answer
     */
    @Override
    public void submitAnswer(Answer answer) throws SQLException {
        System.out.println("存储并且打分");
        System.out.println(answer);
        Double score = 0.0;
        PreparedStatement preparedStatement = null;
        String sqlStudent = null;

        try {
            connection = dataSource.getConnection();
            sqlStudent = answer.getInput();
            // 1：先从数据库查询出老师输入的数据库和答案然后进行判断
            Problem problem = problemMapper.selectByPrimaryKey(answer.getPid());
            String sql = problem.getInput().toLowerCase();
            preparedStatement = connection.prepareStatement(sql);
            //获取标准答案
            String output = problem.getOutput();
            if ( sql.contains("select") ) {
                //2: 如果是增删改 先操作数据库然后查询全部数据(默认升序) 比对数据如果全部正确满分
                // 如果 数据比对不正确 只要程序不报错先给 50分 然后判断关键（待更新） 最高八十分

                final ResultSet resultSet = preparedStatement.executeQuery();
                List list = this.convertList(resultSet);
                String studentExecute = JSONArray.toJSONString(list);

                if ( output.equals(studentExecute) ) {
                    //执行结果和老师的完全一致
                    score = 100d;
                } else {
                    score = 50.0;
                    // 判断关键字加分
                }

            } else {
                //如果是增删改 先操作数据库然后查询全部数据(默认升序) 比对数据如果全部正确满分
                // 如果 数据比对不正确 只要程序不报错先给 50分 然后判断关键（待更新） 最多80分

                Integer num = preparedStatement.executeUpdate();
                List list = this.qurryAllData(sqlStudent, connection);
                Map<String, Object> map = new HashMap();
                map.put("resultSet", list);
                map.put("num", num);
                String studentExecute = JSON.toJSONString(map);
                if ( output.equals(studentExecute) == true ) {
                    // 执行结果和老师的完全一致
                    score = 100d;
                } else {
                    // 执行结果和老师执行结果不一致但是程序不报错 可以执行
                    score = 50.0;

                    //把老师的执行结果解析成 map
                    Map mapTeacher = JSON.parseObject(sql, Map.class);
                    Integer numTeacher = Integer.parseInt(mapTeacher.get("num").toString());
                    if(numTeacher.equals(num) )
                    {
                        score +=20d;
                    }
                    // 判断关键字加分
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
            score = 0d;
        } finally {

            answer.setScore(score);
            try {
                // 回滚操作
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
            preparedStatement.close();
            connection.close();
        }


    }

    /**
     * 封装结果集
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        //获取键名
        ResultSetMetaData md = rs.getMetaData();
        //获取行的数量
        int columnCount = md.getColumnCount();
        while (rs.next()) {
            //声明Map
            Map rowData = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                //获取键名及值
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

    /**
     * 获取学生操作的数据库的全部数据
     *
     * @param sql
     * @param connection
     * @return
     */
    private List qurryAllData(String sql, Connection connection) throws SQLException {
        String qurry = "SELECT * FROM ";
        // 获得需要操作的数据库 根据from拆分
        String[] sqlArray = sql.toLowerCase().split("from");
        // 获取到form以后的内容并且去除空格
        sql = sqlArray[1].trim();
        sqlArray = sql.split(" ");
        qurry += sqlArray[0];
        PreparedStatement preparedStatement = connection.prepareStatement(qurry);
        ResultSet resultSet = preparedStatement.executeQuery();
        List list = this.convertList(resultSet);
        return list;
    }
}
