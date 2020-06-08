package cn.sdut.mapper;

import cn.sdut.domain.Head;
import cn.sdut.domain.HeadExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadMapper {
    int countByExample(HeadExample example);

    int deleteByExample(HeadExample example);

    int deleteByPrimaryKey(Integer hid);

    int insert(Head record);

    int insertSelective(Head record);

    List<Head> selectByExample(HeadExample example);

    Head selectByPrimaryKey(Integer hid);

    int updateByExampleSelective(@Param("record") Head record, @Param("example") HeadExample example);

    int updateByExample(@Param("record") Head record, @Param("example") HeadExample example);

    int updateByPrimaryKeySelective(Head record);

    int updateByPrimaryKey(Head record);
}