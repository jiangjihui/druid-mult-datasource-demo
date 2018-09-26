package com.jjh.druid.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面类
 * 处理Controller方法调用
 * https://blog.csdn.net/u010125432/article/details/80334025
 */
@Aspect
@Component
public class WebLogAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 第一个*号：表示返回类型，*号表示所有的类型。
     * 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
     * 第二个*号：表示类名，*号表示所有的类。
     * *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     * https://blog.csdn.net/lang_niu/article/details/51559994
     */
    @Pointcut("execution(public * com.jjh.druid.controllers..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("[WEB] [URL] "+request.getRequestURI().toString());
        logger.info("[WEB] [HTTP_METHOD] "+request.getMethod());
        logger.info("[WEB] [IP] "+request.getRemoteAddr());
    }

    @AfterReturning(returning = "returnValue",pointcut = "webLog()")
    public void doAfterReturning(Object returnValue) throws JsonProcessingException {
        logger.info("[WEB] [RESPONSE] "+mapper.writeValueAsString(returnValue));
    }


}
