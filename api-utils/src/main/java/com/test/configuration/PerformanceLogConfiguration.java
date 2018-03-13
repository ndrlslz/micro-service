package com.test.configuration;

import com.test.log.PerformanceLog;
import com.test.log.PerformanceLogAppenderConfiguration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StopWatch;

import static java.lang.String.format;

@Aspect
@Configuration
@Import(PerformanceLogAppenderConfiguration.class)
public class PerformanceLogConfiguration {
    public static final Logger LOGGER = LoggerFactory.getLogger(PerformanceLog.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Around("@annotation(com.test.log.PerformanceLog)")
    public Object monitorPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Object result = proceedingJoinPoint.proceed();
            stopWatch.stop();
            log(stopWatch, proceedingJoinPoint, "success");
            return result;
        } catch (Throwable throwable) {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            log(stopWatch, proceedingJoinPoint, "error");
            throw throwable;
        }

    }

    private void log(StopWatch stopWatch, ProceedingJoinPoint proceedingJoinPoint, String result) {
        if (LOGGER.isInfoEnabled()) {
            Signature signature = proceedingJoinPoint.getSignature();
            String serviceName = signature.getDeclaringType().getSimpleName();
            String operationName = signature.getName();
            LOGGER.info(format("%1$s|%2$s|%3$s|%4$s|%5$s", applicationName, serviceName, operationName, result,
                    stopWatch.getTotalTimeMillis()));
        }
    }
}
