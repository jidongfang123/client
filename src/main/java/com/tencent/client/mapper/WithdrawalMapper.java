package com.tencent.client.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WithdrawalMapper {

  List<Map<String, Object>> withdrawalCaseCount();

  List<Map<String, Object>> executionNode();

  List<Map<String, Object>> distributionPartie();

}
