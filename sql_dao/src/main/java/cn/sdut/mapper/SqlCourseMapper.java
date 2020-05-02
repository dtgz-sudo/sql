package cn.sdut.mapper;

import cn.sdut.domain.SqlCourse;
import cn.sdut.domain.SqlCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SqlCourseMapper {
    int countByExample(SqlCourseExample example);

    int deleteByExample(SqlCourseExample example);

    int deleteByPrimaryKey(Integer cno);

    int insert(SqlCourse record);

    int insertSelective(SqlCourse record);

    List<SqlCourse> selectByExample(SqlCourseExample example);

    SqlCourse selectByPrimaryKey(Integer cno);

    int updateByExampleSelective(@Param("record") SqlCourse record, @Param("example") SqlCourseExample example);

    int updateByExample(@Param("record") SqlCourse record, @Param("example") SqlCourseExample example);

    int updateByPrimaryKeySelective(SqlCourse record);

    int updateByPrimaryKey(SqlCourse record);
}