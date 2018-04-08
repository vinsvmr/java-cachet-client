package com.cachet.monitor.internal.component;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vins on 05/02/2018.
 */
@Component
@ConfigurationProperties("api-monitor")
public class ApiDataProvider {

    @Getter
    @Setter
    private List<ApiMonitor> apiMonitor;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiMonitor {
        private String enabled;
        private String name;
        private String url;
        private String method;
        private String componentId;
        private String expectedStatusCode;
        private String message;
        private String title;

    }
}
