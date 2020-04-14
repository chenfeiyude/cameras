package com.feiyu4fun.cameras.aspects;

import com.feiyu4fun.cameras.exceptions.AuthenticationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Aspect
@Component
public class CentralAuthAspect {
    @Pointcut("execution(* com.feiyu4fun.cameras.controllers.management.CamerasController.*(..)) "
    )
    private void validUserPointCut(){}

    @Before("validUserPointCut() && args(headers,..)")
    public void beforeGetData(JoinPoint joinPoint, Map<String, String> headers) throws AuthenticationException
    {
        String username = headers.get("username");
        String token = headers.get("token");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(token))
            throw new AuthenticationException("You are not allowed to use this internal api");
    }
}
