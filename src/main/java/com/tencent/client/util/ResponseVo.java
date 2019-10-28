package com.tencent.client.util;

import lombok.Data;

@Data
public class ResponseVo {
	
	private int code;
	
	private String message;
	
	private Object data;
}
