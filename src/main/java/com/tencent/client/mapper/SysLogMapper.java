package com.tencent.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tencent.client.model.SysLog;
import com.tencent.client.model.SysLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysLogMapper {

	long countByExample(SysLogExample example);

	int deleteByExample(SysLogExample example);

	int deleteByPrimaryKey(Integer id);

	int insertSelective(SysLog record);

	List<SysLog> selectByExample(SysLogExample example);

	SysLog selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

	int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

	int updateByPrimaryKeySelective(SysLog record);

	int updateByPrimaryKey(SysLog record);

	int insert(SysLog entity);

}
