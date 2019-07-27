package com.annu.action.aspect;

import com.annu.action.annotation.Token;
import com.annu.action.util.AuthorityUtil;
import com.annu.action.util.ValidatorUtil;
import com.annu.action.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class AuthorityAspect {

    @Around("@annotation(token)")
    public Object authority(ProceedingJoinPoint joinPoint, Token token) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String requestToken = request.getHeader(AuthorityUtil.TOKEN);

        // token is empty
        ValidatorUtil.requireNonEmpty(requestToken, "token is empty");

        UserVo userVo = AuthorityUtil.parseToken(requestToken);

        //给参数赋值
        Object[] args = joinPoint.getArgs();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == userVo.getClass()) {
                args[i] = userVo;
                break;
            }
        }
        return joinPoint.proceed(args);
    }
}
