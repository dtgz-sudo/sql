package cn.sdut.mapper;

import cn.sdut.domain.SqlSc;
import cn.sdut.domain.SqlScExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SqlScMapper {
    int countByExample(SqlScExample example);

    int deleteByExample(SqlScExample example);

    int insert(SqlSc record);

    int insertSelective(SqlSc record);

    List<SqlSc> selectByExample(SqlScExample example);

    int updateByExampleSelective(@Param("record") SqlSc record, @Param("example") SqlScExample example);

    int updateByExample(@Param("record") SqlSc record, @Param("example") SqlScExample example);
}