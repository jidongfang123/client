package com.tencent.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.web.multipart.MultipartFile;

public class UnZip {
	public static final int BUFFER_SIZE = 2048;

	/**
	 * 
	 * zip解压
	 * 
	 * @param srcFile     zip源文件
	 * 
	 * @param destDirPath 解压后的目标文件夹
	 * 
	 * @throws RuntimeException 解压失败会抛出运行时异常
	 * @throws IOException 
	 * 
	 */

	public static void unZip(MultipartFile srcFile ) throws RuntimeException, IOException {
		String destDirPath = "F:\\data\\wfy";
		long start = System.currentTimeMillis();

		// 判断源文件是否存在

		/*
		 * if (!srcFile.exists()) {
		 * 
		 * throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
		 * 
		 * }
		 */

		File toFile = null;
		if (srcFile.equals("") || srcFile.getSize() <= 0) {
			srcFile = null;
		} else {
			InputStream ins = null;
			ins = srcFile.getInputStream();
			toFile = new File(srcFile.getOriginalFilename());
			inputStreamToFile(ins, toFile);
		

			// 开始解压

			ZipFile zipFile = null;

			try {

				zipFile = new ZipFile(toFile);

				Enumeration<?> entries = zipFile.entries();

				while (entries.hasMoreElements()) {

					ZipEntry entry = (ZipEntry) entries.nextElement();

					System.out.println("解压" + entry.getName());

					// 如果是文件夹，就创建个文件夹

					if (entry.isDirectory()) {

						String dirPath = destDirPath + "/" + entry.getName();

						File dir = new File(dirPath);

						dir.mkdirs();

					} else {

						// 如果是文件，就先创建一个文件，然后用io流把内容copy过去

						File targetFile = new File(destDirPath + "/" + entry.getName());

						// 保证这个文件的父文件夹必须要存在

						if (!targetFile.getParentFile().exists()) {

							targetFile.getParentFile().mkdirs();

						}

						targetFile.createNewFile();

						// 将压缩文件内容写入到这个文件中

						InputStream is = zipFile.getInputStream(entry);

						FileOutputStream fos = new FileOutputStream(targetFile);

						int len;

						byte[] buf = new byte[BUFFER_SIZE];

						while ((len = is.read(buf)) != -1) {

							fos.write(buf, 0, len);

						}

						// 关流顺序，先打开的后关闭

						fos.close();

						is.close();

					}

				}

				long end = System.currentTimeMillis();

				System.out.println("解压完成，耗时：" + (end - start) + " ms");

			} catch (Exception e) {

				throw new RuntimeException("unzip error from ZipUtils", e);

			} finally {

				if (zipFile != null) {

					try {

						zipFile.close();
						ins.close();

					} catch (IOException e) {

						e.printStackTrace();

					}

				}

			}
		}

	}

	public static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
