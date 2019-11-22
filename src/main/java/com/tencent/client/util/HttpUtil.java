package com.tencent.client.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtil {

	public static String http(String url, Map<String, Object> params) {
		URL u = null;
		HttpURLConnection con = null;
		// 构建请求参数
		
		  StringBuffer sb = new StringBuffer(); if (params != null) { for
		  (Entry<String, Object> e : params.entrySet()) { sb.append(e.getKey());
		  sb.append("="); sb.append(e.getValue().toString()); sb.append("&"); }
		  sb.substring(0, sb.length() - 1); }
		 
		log.info("send_url:" + url);
		log.info("send_data:" + params);
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			//// POST 只能为大写，严格限制，post会不识别
			con.setRequestMethod("POST");
			con.setDoOutput(true); // 是否发送数据
			con.setDoInput(true); // 是否接受数据
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(params.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			// 一定要有返回值，否则无法把请求发送给server端。
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
