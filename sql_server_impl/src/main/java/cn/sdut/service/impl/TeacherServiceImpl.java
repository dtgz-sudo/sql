package cn.sdut.service.impl;

import cn.sdut.ImportExcel;
import cn.sdut.domain.*;
import cn.sdut.entity.Alldata;
import cn.sdut.entity.Personaldata;
import cn.sdut.entity.Piedata;
import cn.sdut.mapper.AnswerMapper;
import cn.sdut.mapper.ProblemMapper;
import cn.sdut.mapper.StudentMapper;
import cn.sdut.mapper.TeacherMapper;
import cn.sdut.service.TeacherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * Teacher 服务层
 * @author 赵德锋
 * @Transactional 平台事务管理器管理本平台
 * @Service 添加到ioc容器
 */


@Service
public class TeacherServiceImpl  implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AnswerMapper answerMapper;

    /**
     * 提交评论
     *
     * @param list
     */
    @Override
    public void updateComment(List<Answer> list) {
        for (Answer answer : list) {
            Integer aid = answer.getAid();
            Answer answer1 = answerMapper.selectByPrimaryKey(aid);
            answer1.setComment(answer.getComment());
            answerMapper.updateByPrimaryKey(answer1);

        }
    }

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
    @Transactional(isolation = Isolation.DEFAULT)
    @Override
    public String inputSql(Problem problem) throws SQLException {
        System.out.println("problem" + problem);
        String sql = problem.getInput();
        Connection connection = null;
        connection= dataSource.getConnection();
       // 取消自动提交
        connection.setAutoCommit(false);
        String output = "";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if ( sql.toLowerCase().contains("select") ) {
                //查询
                final ResultSet resultSet = preparedStatement.executeQuery();
                // 讲结果集封装到list集合中 并别返回到数据中
                List list = this.convertList(resultSet);
                Map<String, Object> map = new HashMap();
                map.put("resultSet", list);
                map.put("num", list.size());
                output = JSON.toJSONString(map);
            } else {
                /**
                 * 增删改的策略：
                 *           保存sql语句的执行状态
                 *           并且保存执行sql语句之后数据库的状态
                 */
                Integer num = preparedStatement.executeUpdate();
                // 获取当前操作的数据库的全部数据
                List list = this.qurryAllData(sql, connection,problem.getTablename());
                Map<String, Object> map = new HashMap();
                map.put("resultSet", list);
                map.put("num", num);
                output = JSON.toJSONString(map);
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
                throw throwables;
                }
            preparedStatement.close();
            connection.close();
        }
        System.out.println(output);
        return output;
    }

    /**
     * 获取老师操作的数据库的全部数据
     *
     * @param sql
     * @param connection
     * @return
     */
    private List qurryAllData(String sql, Connection connection,String table) throws SQLException {
        String qurry = "SELECT * FROM " + table;

        PreparedStatement preparedStatement = connection.prepareStatement(qurry);
        ResultSet resultSet = preparedStatement.executeQuery();
        List list = this.convertList(resultSet);
        return list;
    }

    /**
     * 数据库添加 一个新的题目
     *
     * @param problem
     */
    @Transactional(isolation = Isolation.DEFAULT)
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
    public void importstudent(MultipartFile mFile,int tid) throws Exception {
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
            student.setTid(tid);
            studentMapper.insert(student);
        }

    }

//    查询学生整体答题情况
    //    select count(*) as num,pid,score from (select pid,sid,MAX(score) as score from answer group by sid,pid having sid in (select sid from student where tid = 1))A GROUP BY pid,score
    @Override
    public List findalldata(int tid) throws SQLException {
        System.out.println("teacherservicefindalldata");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection= dataSource.getConnection();
        String sql = "select count(*) as num,pid,score from (select pid,sid,MAX(score) as score from answer group by sid,pid having sid in (select sid from student where tid = ?))A GROUP BY pid,score";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,tid);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map> list = this.convertList(resultSet);

        //将数据进行分类
        List<Alldata> listdata = new ArrayList<Alldata>();
        for (int i = 0;i < list.size();) {
            Alldata data1 = new Alldata();
            int pid = (int)list.get(i).get("pid");
            double score = (double)list.get(i).get("score");
            long num = (long)list.get(i).get("num");
            data1.setPid(pid);
            if(score >= 0&&score <= 49) {
                data1.setNum0(num);
            } else if(score >= 50&&score <= 69) {
                data1.setNum50(num);
            } else if(score >= 70&&score <= 99) {
                data1.setNum70(num);
            } else if(score == 100) {
                data1.setNum100(num);
            }
            if(i != list.size() - 1){
                i++;
                while(i < list.size()) {
                    if((int)list.get(i).get("pid") == pid)
                    {
                        score = (double)list.get(i).get("score");
                        num = (long)list.get(i).get("num");
                        if(score >= 0&&score <= 49) {
                            data1.setNum0(num);
                        } else if(score >= 50&&score <= 69) {
                            data1.setNum50(num);
                        } else if(score >= 70&&score <= 99) {
                            data1.setNum70(num);
                        } else if(score == 100) {
                            data1.setNum100(num);
                        }
                        i++;
                        continue;
                    }
                    break;
                }
                listdata.add(data1);
            }
            else{
                listdata.add(data1);
                i++;
            }
        }
        preparedStatement.close();
        connection.close();
        return listdata;
    }

    /**
     * 查询指定老师未评论的问题
     *
     * @param tid
     * @return
     */
    @Override
    public List<Map> findCommontAnswer(Integer tid) {
        List<Map> list = new ArrayList<>();
        AnswerExample answerExample = new AnswerExample();
        AnswerExample.Criteria criteria = answerExample.createCriteria();
        criteria.andTidEqualTo(tid);
        criteria.andCommentIsNull();
        List<Answer> answers = answerMapper.selectByExample(answerExample);
        for (Answer answer : answers) {
            Integer pid = answer.getPid();
            Integer sid = answer.getSid();
            Problem problem = problemMapper.selectByPrimaryKey(pid);
            Student student = studentMapper.selectByPrimaryKey(sid);

            Map map = new HashMap();
            map.put("aid",answer.getAid());
            map.put("sql",answer.getInput());
            map.put("score",answer.getScore());
            map.put("title",problem.getTitle());
            map.put("stuName",student.getNickname());
            map.put("comment",null);
            list.add(map);
        }
        return list;
    }

    //    查询学生部分完成的人数 select count(*) as num from (select sid from (select count(*) as snum,sid from (select MAX(score),sid,pid from answer GROUP BY sid,pid)A GROUP BY sid)B where snum < (select count(*) as allnum from problem where tid = 1) and snum > 0)C
//  查询学生全部完成的人数select count(*) as num from (select sid from (select count(*) as snum,sid from (select MAX(score),sid,pid from answer GROUP BY sid,pid)A GROUP BY sid)B where snum >= (select count(*) as allnum from problem where tid = 1))C
    @Override
    public Piedata findpiedata(int tid) throws SQLException {
        System.out.println("teacherservicefindpiedata");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection= dataSource.getConnection();
        String sql = "select count(*) as num from (select sid from (select count(*) as snum,sid from (select MAX(score),sid,pid from answer where tid = ? GROUP BY sid,pid)A GROUP BY sid)B where snum < (select count(*) as allnum from problem where tid = ?) and snum > 0)C";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,tid);
        preparedStatement.setInt(2,tid);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map> listpart = this.convertList(resultSet);

        sql = "select count(*) as num from (select sid from (select count(*) as snum,sid from (select MAX(score),sid,pid from answer where tid = ? GROUP BY sid,pid)A GROUP BY sid)B where snum >= (select count(*) as allnum from problem where tid = ?))C";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,tid);
        preparedStatement.setInt(2,tid);
        ResultSet resultSet2 = preparedStatement.executeQuery();
        List<Map> listall = this.convertList(resultSet2);

        sql = "select count(*) as allstudent from student where tid = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,tid);
        ResultSet resultSet3 = preparedStatement.executeQuery();
        List<Map> all = this.convertList(resultSet3);
        long partnum = (long)listpart.get(0).get("num");
        long allnum = (long)listall.get(0).get("num");
        long allstudent = (long)all.get(0).get("allstudent");

        Piedata piedata = new Piedata();
        piedata.setAllvalue(allnum);
        piedata.setPartvalue(partnum);
        piedata.setNotvalue(allstudent-allnum-partnum);
        preparedStatement.close();
        connection.close();
        return piedata;
    }
//查询所有学生
    @Override
    public List findallstudent(int tid) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andTidEqualTo(tid);
        List<Student> students = studentMapper.selectByExample(studentExample);
        return students;
    }

    @Override
    public List findpersonaldata(int sid) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection= dataSource.getConnection();
        String sql = "select MAX(score) as score,pid from answer where sid = ? GROUP BY pid";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,sid);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map> list = this.convertList(resultSet);

        List<Personaldata> listdata = new ArrayList<Personaldata>();
        for (int i = 0;i<list.size();i++)
        {
            Personaldata personaldata = new Personaldata();
            personaldata.setPid((int)list.get(i).get("pid"));
            personaldata.setScore((double)list.get(i).get("score"));
            listdata.add(personaldata);
        }

        return listdata;
    }

    /**
     * 根据主键查询数据
     *
     * @param sid
     * @return
     */
    @Override
    public Teacher findTeacherByTid(Integer sid) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(sid);
        return teacher;
    }

    /**
     * 数据库中更新teacher
     *
     * @param teacher
     */
    @Override
    public void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
     * 查看全部此老师题目
     *
     * @param tid
     * @return
     */
    @Override
    public List<Problem> findAllProblemByTid(Integer tid) {
        ProblemExample example = new ProblemExample();
        ProblemExample.Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(tid);
        List<Problem> problems = problemMapper.selectByExample(example);
        return problems;
    }

    /**
     * 删除对应问题
     *
     * @param pid
     */
    @Override
    public void deleteProblemByPid(Integer pid) {
        problemMapper.deleteByPrimaryKey(pid);
    }
}
