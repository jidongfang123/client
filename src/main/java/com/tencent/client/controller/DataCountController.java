package com.tencent.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.tencent.client.dto.DataDto;
import com.tencent.client.util.HttpUtil;
import com.tencent.client.util.MD5;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DataCountController {
  
  @RequestMapping("accessSituation")
  public void accessSituation() {
    String url = "http://106.15.38.154:8080/court/upload/dictCourt";
    long time = System.currentTimeMillis();
    String key = "A8EVzQJtA8rKE754AxPqh"+time;
    String token = MD5.md5(key);
    Map<String, Object> map = new  HashMap<>();
    map.put("token", token);
    map.put("timestamp", time);
    DataDto d = new  DataDto();
    d.setCourtName("杭州");
    d.setProvince("杭州市中级人民法院");
    d.setRegion("浙江");
    d.setState("1");
    List<DataDto> list = new ArrayList<>();
    list.add(d);
    map.put("data", list);
    JSONObject json = new JSONObject(map);
    String response =  HttpUtil.http(url, json);
     log.info(response);
     
  }
  
}
