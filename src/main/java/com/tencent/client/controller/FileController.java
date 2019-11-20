package com.tencent.client.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tencent.client.util.Zip;

@RestController
@RequestMapping("dataFile")
public class FileController {

	@RequestMapping("upload")
	public String upload(String courtCode, String dataSource, @RequestParam(value = "file") MultipartFile file)
			throws IOException {
		String path = "";
		// 获取文件名称
		String fileRelName = file.getOriginalFilename();
		// 获取文件后缀名
		String[] split = fileRelName.split("\\.");
		String suffix = split[split.length - 1];
		if (!"zip".equals(suffix) && !"FLAG".equals(suffix)) {
			return "文件格式错误";
		}

		// 获取前缀
		String prefix = fileRelName.substring(0, fileRelName.lastIndexOf("."));
		// 获取当前时间
		// String timeStr1 =
		// LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		// 存放路径
		String filePath = "E:\\data\\wfy\\";
		// String filePath = "/data/fy/";
		// 创建文件夹
		File dirPath = new File(filePath + prefix);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		// 文件转换
		InputStream inputStreams = new ByteArrayInputStream(file.getBytes());
		// 文件保存路径
		String filep = dirPath + File.separator + prefix + "." + suffix;
		// 写入文件
		try {
			// 第三个参数代表强制覆盖重名文件
			Files.copy(inputStreams, Paths.get(filep), StandardCopyOption.REPLACE_EXISTING);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		if (!"FLAG".equals(suffix)) {

			// 解压
			path = Zip.decompressZip(filep, dirPath + File.separator);
		}else {
			return filep;
		}
		return path;
	}

}
