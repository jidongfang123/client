package com.tencent.client.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tencent.client.util.UnZip;
import com.tencent.client.util.Zip;

@RestController
@RequestMapping("dataFile")
public class FileController {
	public static final String auth = "8a58c0c02db24c0aaf510aa6706fc8d5";

	@RequestMapping("upload")
	public String upload(String courtCode, String dataSource, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws IOException {
		if (file == null) {
			return "fail 文件未上传";
		}
		String header = request.getHeader("Authorization");
		if (!auth.equals(header)) {
			return "fail Authorization 错误";
		}
		// 获取文件名称
		String fileRelName = file.getOriginalFilename();
		//获取前缀
		String prefix = fileRelName.substring(0,fileRelName.lastIndexOf("."));
	
		// 获取当前时间
		String timeStr1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		// 根据参数生成文件名称
		String fileName = "1000_" + courtCode + "_" + dataSource + "_" + timeStr1;
		System.out.println(fileName);
		// 获取文件后缀名
		String[] split = fileRelName.split("\\.");
		String suffix = split[split.length - 1];
		String filePath = "E:\\data\\wfy";
		// String filePath = "/data/fy";
		// 创建文件夹
		/*
		 * File dir = new File(filePath); if (!dir.exists()) { dir.mkdirs(); }
		 */
		File dirPath = new File(filePath+"\\"+prefix);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		// 文件转换
		InputStream inputStreams = new ByteArrayInputStream(file.getBytes());
		//UnZip.unZip(file);
		// 写入文件
		Files.copy(inputStreams, Paths.get(dirPath + File.separator + fileName + "." + suffix));
		Zip.decompressZip( dirPath + File.separator + fileName + "." + suffix,dirPath + File.separator );
		return "ok";
	}

}
