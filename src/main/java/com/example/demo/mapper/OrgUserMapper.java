package com.example.demo.mapper;

import com.example.demo.entity.OrgUser;
import com.example.demo.entity.OrgUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgUserMapper {
    long countByExample(OrgUserExample example);

    int deleteByExample(OrgUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrgUser record);

    int insertSelective(OrgUser record);

    List<OrgUser> selectByExample(OrgUserExample example);

    OrgUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrgUser record, @Param("example") OrgUserExample example);

    int updateByExample(@Param("record") OrgUser record, @Param("example") OrgUserExample example);

    int updateByPrimaryKeySelective(OrgUser record);

    int updateByPrimaryKey(OrgUser record);
}