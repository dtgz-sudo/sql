package cn.sdut.mapper;

import cn.sdut.domain.SqlStudent;
import cn.sdut.domain.SqlStudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SqlStudentMapper {
    int countByExample(SqlStudentExample example);

    int deleteByExample(SqlStudentExample example);

    int deleteByPrimaryKey(String sno);

    int insert(SqlStudent record);

    int insertSelective(SqlStudent record);

    List<SqlStudent> selectByExample(SqlStudentExample example);

    SqlStudent selectByPrimaryKey(String sno);

    int updateByExampleSelective(@Param("record") SqlStudent record, @Param("example") SqlStudentExample example);

    int updateByExample(@Param("record") SqlStudent record, @Param("example") SqlStudentExample example);

    int updateByPrimaryKeySelective(SqlStudent record);

    int updateByPrimaryKey(SqlStudent record);
}