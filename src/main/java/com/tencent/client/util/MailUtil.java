package com.tencent.client.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil {

	Logger logger = LoggerFactory.getLogger(MailUtil.class);

	/**
	 * 发送带附件的邮件
	 *
	 * @param receive  收件人
	 * @param subject  邮件主题
	 * @param msg      邮件内容
	 * @param filename 附件地址
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static boolean sendMail(String receive, String subject, String msg)
			throws GeneralSecurityException, UnsupportedEncodingException {
		if (StringUtils.isEmpty(receive)) {
			return false;
		}

		// 发件人电子邮箱
		final String from = "17600570699@163.com";
		// 发件人电子邮箱密码
		final String pass = "17600570699sqm";

		// 指定发送邮件的主机
		String host = "smtp.163.com"; // 邮件服务器

		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);
		// 设置端口
		properties.put("port", "25");

		properties.put("mail.smtp.auth", "true");
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//		properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.ssl.socketFactory", sf);
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() { // qq邮箱服务器账户、第三方登录授权码
				return new PasswordAuthentication(from, pass); // 发件人邮件用户名、密码
			}
		});

		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));
			
			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receive));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));

			// Set Subject: 主题文字
			message.setSubject(subject);

			// 创建消息部分
			BodyPart messageBodyPart = new MimeBodyPart();

			// 消息
			messageBodyPart.setText(msg);

			// 创建多重消息
			Multipart multipart = new MimeMultipart();

			// 设置文本消息部分
			multipart.addBodyPart(messageBodyPart);

      /*
       * // 附件部分 messageBodyPart = new MimeBodyPart(); // 设置要发送附件的文件路径 DataSource source = new
       * FileDataSource(filename); messageBodyPart.setDataHandler(new DataHandler(source));
       * 
       * messageBodyPart.setFileName(filename); // 处理附件名称中文（附带文件路径）乱码问题
       * messageBodyPart.setFileName(MimeUtility.encodeText(new File(filename).getName()));
       * multipart.addBodyPart(messageBodyPart);
       */

			// 发送完整消息
			message.setContent(multipart);

			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....");

			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
}