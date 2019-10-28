package com.tencent.client.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.tencent.client.enums.Message;

public class SendSms {
    public static ResponseVo aliyunSendSms(String phone,String veriCode) {
    	ResponseVo responseVo = new ResponseVo();
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIivv7aVXjHz5k", "mLpuyxHDEZRCaDIAASmUn4PQ8YlVwc");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "Java青年");
        request.putQueryParameter("TemplateCode", "SMS_109385188");
        String code = "{\"code\":"+veriCode+"}";
        request.putQueryParameter("TemplateParam", code);
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject json = JSONObject.parseObject(response.getData());
            responseVo.setMessage(json.get("Message").toString());
            if (!json.get("Code").toString().equals("OK")) {
        		responseVo.setCode(Message.ERROR_E.getCode());
        	}
            responseVo.setData(veriCode);
            return responseVo;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return responseVo;
      
    }
   
    
}
