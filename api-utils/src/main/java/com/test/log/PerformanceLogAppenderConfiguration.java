package com.test.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.test.configuration.PerformanceLogConfiguration;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Configuration
public class PerformanceLogAppenderConfiguration {
    private static final String PERFORMANCE_APPENDER = "PERFORMANCE_APPENDER";
    private static final String PATTERN = "%date %X{X-B3-TraceId},%X{X-B3-SpanId},%X{X-B3-ParentSpanId},%X{X-Span-Export}] %msg%n";

    private final Environment environment;

    @Autowired
    public PerformanceLogAppenderConfiguration(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void addPerformanceAppender() {
        LoggerContext factory = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();
        LogbackConfigurator configurator = new LogbackConfigurator(factory);
        Appender<ILoggingEvent> iLoggingEventAppender = fileAppender(configurator, logFile(environment));
        configurator.logger(PerformanceLogConfiguration.LOGGER.getName(), Level.DEBUG, false, iLoggingEventAppender);

    }

    private String logFile(Environment environment) {
        String path = environment.getRequiredProperty("logging.path");
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        return StringUtils.applyRelativePath(path, "performance.log");
    }

    private Appender<ILoggingEvent> fileAppender(LogbackConfigurator configurator, String logFile) {
        return LogbackHelper.fileAppender(configurator, PERFORMANCE_APPENDER, logFile, PATTERN, "10MB");
    }
}
