package com.tencent.client.config;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import com.tencent.client.model.SysLog;
import com.tencent.client.service.ISysLogService;



/**
* @author Promise
* @createTime 2018年12月18日 下午9:33:28
* @description 切面日志配置
*/
@Aspect
@Component
public class LogAsPect {
    
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(LogAsPect.class);

    @Autowired
    private ISysLogService sysLogService;
    
    //表示匹配带有自定义注解的方法
    @Pointcut("execution(public * com.tencent.client.controller..*.*(..))")
    public void Pointcut() {
    }

    

    @Around("Pointcut()")
    public Object  invoke(ProceedingJoinPoint  point) throws Throwable {
    	 MethodSignature signature = (MethodSignature)point.getSignature();
         Method method = signature.getMethod();
         SysLog sys_log = new SysLog();
         
         Log userAction = method.getAnnotation(Log.class);
         if (userAction != null) {
             // 注解上的描述
             sys_log.setUserAction(userAction.value());
         }
         
         // 请求的类名
         String className = point.getTarget().getClass().getName();
         // 请求的方法名
         String methodName = signature.getName();
         // 请求的方法参数值
         String args = Arrays.toString(point.getArgs());
         
         //从session中获取当前登陆人id
//       Long useride = (Long)SecurityUtils.getSubject().getSession().getAttribute("userid");
         
         Long userid = 1L;//应该从session中获取当前登录人的id，这里简单模拟下
         
         sys_log.setUserId(userid);
         sys_log.setCreateTime(new Date());
         sys_log.setUserAction("当前登陆人:"+userid+",类名:"+className+",方法名:"+methodName+"参数:"+args+"执行时间:"+System.currentTimeMillis()+"1" );
         
         Object result = point.proceed();
         sysLogService.insertLog(sys_log);
         return result;
    	
    }
    
    public static void main(String[] args) {
		
    	System.out.print(new Date());
	}
    
   
}