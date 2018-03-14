package com.test.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.test.log.LogbackConfigurator;
import com.test.log.LogbackHelper;
import com.test.log.LoggingRequestAndResponseInterceptor;
import com.test.properties.MessageLogProperties;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;


@Configuration
@ConditionalOnProperty(value = "logging.message.enabled")
public class MessageLogConfiguration {
    private static final String MESSAGE_APPENDER = "MESSAGE_APPENDER";
    private static final String PATTERN = "%date [%X{X-B3-TraceId},%X{X-B3-SpanId},%X{X-B3-ParentSpanId},%X{X-Span-Export}] %msg%n";

    @Autowired
    private MessageLogProperties messageLogProperties;

    @Autowired
    private Environment environment;

    @Bean
    public MessageLogProperties messageLogProperties() {
        return new MessageLogProperties();
    }

    @Bean
    public LoggingRequestAndResponseInterceptor loggingRequestAndResponseInterceptor() {
        return new LoggingRequestAndResponseInterceptor();
    }

    @PostConstruct
    public void configureMessageLog() {
        LoggerContext factory = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();
        LogbackConfigurator configurator = new LogbackConfigurator(factory);
        Appender<ILoggingEvent> appender = fileAppender(configurator, logFile(environment));
        messageLogProperties.getMessage().getLevel().forEach(
                (key, value) -> configurator.logger(key, Level.valueOf(value), false, appender));
    }

    private Appender<ILoggingEvent> fileAppender(LogbackConfigurator configurator, String logFile) {
        return LogbackHelper.fileAppender(configurator, MESSAGE_APPENDER, logFile, PATTERN, "10MB");
    }

    private String logFile(Environment environment) {
        String path = environment.getRequiredProperty("logging.path");
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        return StringUtils.applyRelativePath(path, "message.log");
    }
}