package com.tencent.client.model;

import java.util.Date;

import lombok.Data;
@Data
public class SysLog {
    private Integer id;

	private Long userId;

	private String userAction;

	private Date createTime;

	
}