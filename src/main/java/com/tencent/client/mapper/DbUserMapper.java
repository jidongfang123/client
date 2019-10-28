package com.tencent.client.mapper;

import com.tencent.client.model.DbUser;
import com.tencent.client.model.DbUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DbUserMapper {
    long countByExample(DbUserExample example);

    int deleteByExample(DbUserExample example);

    int insert(DbUser record);

    int insertSelective(DbUser record);

    List<DbUser> selectByExample(DbUserExample example);

    int updateByExampleSelective(@Param("record") DbUser record, @Param("example") DbUserExample example);

    int updateByExample(@Param("record") DbUser record, @Param("example") DbUserExample example);

	DbUser selectUserInfoByExample(DbUserExample example);
}