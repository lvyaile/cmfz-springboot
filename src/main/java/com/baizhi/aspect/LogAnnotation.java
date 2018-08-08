package com.baizhi.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/8/5.
 */
//声明注解的使用位置  范围
@Target(ElementType.METHOD)
//声明注解的生效的时机
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    public String name();
}