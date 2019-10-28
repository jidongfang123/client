package com.tencent.client.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SendSms {
    public ResponseVo aliyunSendSms() {
    	ResponseVo responseVo = new ResponseVo();
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIivv7aVXjHz5k", "mLpuyxHDEZRCaDIAASmUn4PQ8YlVwc");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "17600570699");
        request.putQueryParameter("SignName", "Java青年");
        request.putQueryParameter("TemplateCode", "SMS_109385188");
        request.putQueryParameter("TemplateParam", "{\"code\":\"1111\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            JSONObject json = JSONObject.parseObject(response.getData());
            responseVo.setMessage(json.get("Message").toString());
            return responseVo;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return responseVo;
      
    }
}
