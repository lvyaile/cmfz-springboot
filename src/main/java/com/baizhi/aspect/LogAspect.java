package com.baizhi.aspect;

import com.baizhi.dao.LoggingDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Logging;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Administrator on 2018/8/5.
 */
@Configuration
@Aspect
public class LogAspect {
    @Autowired
    private LoggingDao loggingDao;

    @Pointcut(value = "@annotation(LogAnnotation)")
   // @Pointcut(value = "execution(* com.baizhi.service.*.*(..))")
    public void pt(){

    }
    @Around("pt()")
    public Object arond(ProceedingJoinPoint proceedingJoinPoint){
        // 什么人、什么时间、做了什么事


        Date date = new Date();

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        String name = annotation.name();
         String message = "";
        Object proceed = null;
        boolean flag = false;
        try {
            proceed = proceedingJoinPoint.proceed();
            message = "调用"+name+"方法并返回成功";
            flag = true;
        } catch (Throwable throwable) {
            message="调用"+name+"方法发生异常";
            flag = false;
            throwable.printStackTrace();
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        Logging logging = new Logging(admin.getUsername(),date,name,flag);
        loggingDao.add(logging);
        return proceed;
    }

}
