package com.tencent.client.config;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

@Component
public class HandlerInterceptor extends HandlerInterceptorAdapter {   
	
    public static final String AUTH = "8a58c0c02db24c0aaf510aa6706fc8d5";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            String header = request.getHeader("Authorization");
            if (!AUTH.equals(header)) {
            	render(response);
    			return false;
    		}
            
        }
        return true;
    }
	

    
	private void render(HttpServletResponse response)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString("Authorization 校验失败");
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
