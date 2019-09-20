package com.annu.action.aspect;

import com.annu.action.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Aspect
@Slf4j
@Component
@EnableAspectJAutoProxy
public class ApiLogAspect {


    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
    )
    public void log() {

    }

    @Around("log()")
    public Object aroundRequest(ProceedingJoinPoint point) throws Throwable {

        WebLogDto logDto = WebLogDto.builder()
                .httpMethod(request.getMethod())
                .path(request.getRequestURI() + "?" + request.getQueryString())
                .header(this.extractHeader(request))
                .parameter(this.extractParameters(point))
                .build();
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            logDto.setPath(request.getRequestURI() + "?" + queryString);
        } else {
            logDto.setPath(request.getRequestURI());
        }
        log.info("Api日志-请求：logDto = {}", JsonUtils.OBJECT_MAPPER.writeValueAsString(logDto));
        Object proceed;
        try {
            proceed = point.proceed();
        } catch (Throwable throwable) {
            log.error("Api日志-异常：logDto = {}", JsonUtils.OBJECT_MAPPER.writeValueAsString(logDto), throwable);
            throw throwable;
        }
        logDto.setParameter(proceed);
        log.info("Api日志-返回：logDto = {}", JsonUtils.OBJECT_MAPPER.writeValueAsString(logDto));
        return proceed;
    }

    private List extractParameters(JoinPoint point) {

        if (!(point instanceof MethodInvocationProceedingJoinPoint)) {
            return Collections.emptyList();
        }

        MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) point;
        Object[] args = methodPoint.getArgs();

        if (ArrayUtils.isEmpty(args)) {
            return Collections.emptyList();
        }

        List<Object> param = new ArrayList<>(5);
        for (Object arg : args) {
            if (arg instanceof BeanPropertyBindingResult || arg instanceof HttpServletRequest) {
                continue;
            }
            if (arg instanceof List) {
                param.addAll((List) arg);
                continue;
            }
            param.add(arg);
        }
        if (CollectionUtils.isEmpty(param)) {
            return Collections.emptyList();
        }
        return param;
    }

    private Map<String, String> extractHeader(HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headerMap.put(key, value);
        }
        return headerMap;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class WebLogDto {

        /**
         * POST、GET、PUT等
         */
        private String httpMethod;

        /**
         * 请求路径+queryString
         */
        private String path;

        /**
         * request header
         */
        private Map<String, String> header;

        /**
         * 请求和返回参数
         */
        private Object parameter;
    }


}
