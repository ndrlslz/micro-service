package com.test.utils.log;


import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class LogbackHelper {
    public static Appender<ILoggingEvent> fileAppender(LogbackConfigurator configurator,
                                                       String appenderName,
                                                       String logFile,
                                                       String logPattern,
                                                       String maxFileSize) {
        RollingFileAppender<ILoggingEvent> appender = new RollingFileAppender<>();
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern(OptionHelper.substVars(logPattern, configurator.getContext()));
        appender.setEncoder(encoder);
        configurator.start(encoder);

        appender.setFile(logFile);

        FixedWindowRollingPolicy rollingPolicy = new FixedWindowRollingPolicy();
        rollingPolicy.setFileNamePattern(logFile + ".%i");
        rollingPolicy.setMinIndex(1);
        rollingPolicy.setMaxIndex(10);
        appender.setRollingPolicy(rollingPolicy);
        rollingPolicy.setParent(appender);
        configurator.start(rollingPolicy);

        setMaxFileSize(appender, maxFileSize, configurator);
        configurator.appender(appenderName, appender);
        return appender;
    }

    private static void setMaxFileSize(
            RollingFileAppender<ILoggingEvent> appender,
            String maxFileSize,
            LogbackConfigurator configurator) {
        SizeBasedTriggeringPolicy<ILoggingEvent> triggeringPolicy = new SizeBasedTriggeringPolicy<>();
        try {
            triggeringPolicy.setMaxFileSize(FileSize.valueOf(maxFileSize));
        } catch (NoSuchMethodError ex) {
            // Logback < 1.1.8 used String configuration
            Method method = ReflectionUtils.findMethod(
                    SizeBasedTriggeringPolicy.class, "setMaxFileSize", String.class);
            ReflectionUtils.invokeMethod(method, triggeringPolicy, maxFileSize);
        }
        appender.setTriggeringPolicy(triggeringPolicy);
        configurator.start(triggeringPolicy);
    }
}
