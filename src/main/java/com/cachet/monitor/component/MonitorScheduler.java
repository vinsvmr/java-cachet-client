package com.cachet.monitor.component;

import com.cachet.monitor.exception.ApiMonitorException;
import com.cachet.monitor.service.ApiMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by vins on 05/02/2018.
 */
/*
 check https://github.com/Gaz492/cachet-monitor
 */
@Slf4j
@Component
public class MonitorScheduler {

    private final boolean scheduleEnabled;
    private final ApiMonitorService apiMonitorService;

    public MonitorScheduler(@Value("${scheduler.monitor.enabled:false}")boolean scheduleEnabled, ApiMonitorService apiMonitorService){
        this.scheduleEnabled = scheduleEnabled;
        this.apiMonitorService = apiMonitorService;
    }

    @Scheduled(cron = "${scheduler.monitor.cron}")
    public void tryBilling(){
        if (scheduleEnabled) {
            monitorDataDear();
        }
    }

    public void monitorDataDear() {
        apiMonitorService.checkApis();

    }

}
