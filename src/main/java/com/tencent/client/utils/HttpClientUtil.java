package com.tencent.client.utils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtil {
	public static final Log logger = LogFactory.getLog("httpclient");


	public static String httpReader(String url, String code){
		logger.info("GetPage:"+url);
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);	
		
		String result = null;
		try {
			client.executeMethod(method);
			int status = method.getStatusCode();
			if (status == HttpStatus.SC_OK) {
				result = method.getResponseBodyAsString();
			} else {
				logger.info("Method failed: " + method.getStatusLine());
			}
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			logger.info("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			logger.info("发生网络异常！");
			e.printStackTrace();
		} finally{
			// 释放连接
			if(method!=null)method.releaseConnection();
			method = null;
			client = null;
		}
		return result;
	}
	
	public static String httpPost(String url, Map paramMap, String code) {
		logger.info("GetPage:"+url);
		String content = null;
		if (url == null || url.trim().length() == 0 || paramMap == null
				|| paramMap.isEmpty())
			return null;
		HttpClient httpClient = new HttpClient();
		
		//设置header
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");//
		
		//代理设置
		//httpClient.getHostConfiguration().setProxy("128.128.176.74", 808);
		
		PostMethod method = new PostMethod(url);
		Iterator it = paramMap.keySet().iterator();
		

		while (it.hasNext()) {
			String key = it.next() + "";
			Object o = paramMap.get(key);
			if (o != null && o instanceof String) {
				method.addParameter(new NameValuePair(key, o.toString()));
			}
			if (o != null && o instanceof String[]) {
				String[] s = (String[]) o;
				if (s != null)
					for (int i = 0; i < s.length; i++) {
						method.addParameter(new NameValuePair(key, s[i]));
					}
			}
		}
		try {
			
			int statusCode = httpClient.executeMethod(method);
			
			System.out.println("httpClientUtils::statusCode="+statusCode);

			logger.info(method.getStatusLine());
			content = new String(method.getResponseBody(), code);
			
		} catch (Exception e) {
			logger.info("time out");
			e.printStackTrace();
		} finally {
			if(method!=null)method.releaseConnection();
			method = null;
			httpClient = null;
		}
		return content;

	}
    public static ResultObject httpClientPost(String url, Map paramMap, String code) {
        return   httpClientPost(url, paramMap, code,30000,600000);
    }
    public static ResultObject httpClientPost(String url, Map paramMap, String code,long conTimeout,int soTimeout) {
        logger.info("GetPage:"+url);
        ResultObject ro = new ResultObject();
        String content = null;
        if (url == null || url.trim().length() == 0 || paramMap == null
                || paramMap.isEmpty()) {
            ro.setRsltCode(-1);
            ro.setRsltComment("URL不能为空");
            return ro;
        }
        HttpClient httpClient = new HttpClient();
        //设置header
        httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");//
        httpClient.getParams().setConnectionManagerTimeout(conTimeout);        //10秒
        httpClient.getParams().setSoTimeout(soTimeout);
        //代理设置
        //httpClient.getHostConfiguration().setProxy("128.128.176.74", 808);

        PostMethod method = new PostMethod(url);
        Iterator it = paramMap.keySet().iterator();


        while (it.hasNext()) {
            String key = it.next() + "";
            Object o = paramMap.get(key);
            if (o != null && o instanceof String) {
                method.addParameter(new NameValuePair(key, o.toString()));
            }
            if (o != null && o instanceof String[]) {
                String[] s = (String[]) o;
                if (s != null)
                    for (int i = 0; i < s.length; i++) {
                        method.addParameter(new NameValuePair(key, s[i]));
                    }
            }
        }
        try {

            int statusCode = httpClient.executeMethod(method);
            ro.setRsltCode(statusCode);
            System.out.println("httpClientUtils::statusCode="+statusCode);

            logger.info(method.getStatusLine());
            content = new String(method.getResponseBody(), code);
            ro.setRsltComment(content);
        } catch (HttpException e) {
            logger.info("Http请求异常：HttpException");
        } catch (UnsupportedEncodingException e) {
            logger.info("编码转换异常：UnsupportedEncodingException");
        } catch (IOException e) {
            logger.info("IO异常：请求的网址不正确或服务没响应："+url);
        } finally {
            if(method!=null)method.releaseConnection();
            method = null;
            httpClient = null;
        }
        return ro;

    }
	public static String httpPost(String url, Map paramMap) {
		//编码：GB2312
		return HttpClientUtil.httpPost(url, paramMap, "UTF-8");
	}
}
