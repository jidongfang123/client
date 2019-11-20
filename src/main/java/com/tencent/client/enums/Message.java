package com.tencent.client.enums;

public enum Message {
	OK(0, "成功"),
	FAIL(500,"失败"),
    ERROR_A(100, "账号为空"),
    ERROR_B(200, "密码为空"),
    ERROR_C(300, "尚未注册"),
	ERROR_D(400,"密码错误"),
	ERROR_E(501,"此用户名已被注册"),
	ERROR_F(501,"验证码错误");

	
    private int code;
    private String message;
    
	private Message(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
