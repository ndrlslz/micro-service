package com.test.utils.properties;

import org.springframework.boot.autoconfigure.security.SecurityPrerequisite;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "logging")
public class MessageLogProperties implements SecurityPrerequisite {
    private Message message = new Message();

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message {
        private boolean enabled = true;
        private Map<String, String> level = new HashMap<>();

        public Message() {
            level.put("org.springframework.ws.client.MessageTracing", "TRACE");
            level.put("org.springframework.web.client.RestTemplate", "DEBUG");
            level.put("com.test.log.LoggingRequestAndResponseInterceptor", "DEBUG");
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Map<String, String> getLevel() {
            return level;
        }

        public void setLevel(Map<String, String> level) {
            this.level = level;
        }
    }
}
