package org.ivanov.api.aspect;

import lombok.RequiredArgsConstructor;
import org.ivanov.api.metric.LoginCountTotalMetric;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginMetricAspect {

    private final LoginCountTotalMetric loginCountTotalMetric;

    @AfterReturning("execution(public * org.ivanov.api.service.TokenService.login(..))")
    public void afterLogin() {
        loginCountTotalMetric.incrementLoginCount();
    }
}