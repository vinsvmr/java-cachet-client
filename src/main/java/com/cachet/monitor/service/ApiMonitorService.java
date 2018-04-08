package com.cachet.monitor.service;

import com.cachet.monitor.internal.component.ApiDataProvider;
import com.cachet.monitor.internal.component.HttpStatusHandler;
import com.cachet.monitor.exception.ApiMonitorException;
import com.cachet.monitor.service.Incident.IncidentService;
import com.cachet.monitor.service.component.ComponentService;
import com.cachet.monitor.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by vins on 05/02/2018.
 */
@Slf4j
@Service
public class ApiMonitorService {

    private final ApiDataProvider apiDataProvider;
    private final GatewayApiService gatewayApiService;
    private final IncidentService incidentService;
    private final ComponentService componentService;
    private final HttpStatusHandler httpHandler;

    private final Integer MAJOR_OUTAGE = 4;

    public ApiMonitorService(ApiDataProvider apiDataProvider, GatewayApiService gatewayApiService,
                             IncidentService incidentService, ComponentService componentService, HttpStatusHandler httpHandler){
        this.apiDataProvider = apiDataProvider;
        this.gatewayApiService = gatewayApiService;
        this.httpHandler = httpHandler;
        this.incidentService = incidentService;
        this.componentService = componentService;
    }

    public void checkApis() {
        if(apiDataProvider.getApiMonitor() != null){
            String apiStatus = null;
            Integer incidentId = null;
            String currentCachetStatus = null;

            for(ApiDataProvider.ApiMonitor dataMonitor : apiDataProvider.getApiMonitor()){
                try {
                    apiStatus = gatewayApiService.getApiStatus(dataMonitor.getUrl());
                    currentCachetStatus = componentService.getComponentsByID(dataMonitor.getComponentId());
                    incidentId = incidentService.checkForIncident(dataMonitor.getComponentId());
                    if (dataMonitor.getEnabled().equals("true")) {
                        if (!apiStatus.equals(dataMonitor.getExpectedStatusCode())) {
                            // error reponse
                            if (httpHandler.getAllHttpError().containsKey(apiStatus))
                                apiStatus = httpHandler.getAllHttpError().get(apiStatus);
                            //String error_code = MessageFormat.format("{0} check **failed** - {1}. {2} {3} HTTP StatusError: {4}",
                            //        dataMonitor.getUrl(), DateUtil.convertToString(new Date()), dataMonitor.getMethod(), dataMonitor.getUrl(), apiStatus);
                            String error_code = MessageFormat.format(dataMonitor.getMessage(),
                                           dataMonitor.getUrl(), DateUtil.convertToString(new Date()), dataMonitor.getMethod(), dataMonitor.getUrl(), apiStatus);dataMonitor.getMessage();
                            //String nameIncident = MessageFormat.format("{0} : HTTP Status Error", dataMonitor.getUrl());
                            String nameIncident = dataMonitor.getTitle();
                            if (StringUtils.isEmpty(incidentId)) {
                                // post incident. create a new incident
                                incidentService.postIncidents(nameIncident, error_code, dataMonitor.getComponentId(), currentCachetStatus);

                            } else {
                                // update Incident
                                incidentService.putIncidents(incidentId, nameIncident, error_code);
                            }
                            // if (currentCachetStatus != null && currentCachetStatus != MAJOR_OUTAGE)
                            //    componentService.putComponentsByID(dataMonitor.getComponentId() ,MAJOR_OUTAGE);

                        }


                    }
                }catch(ApiMonitorException ex){
                    log.error("Error trying to log incident for "+dataMonitor.getName());

                }
            }

        }else{
            log.error("Api data provider not loaded. ");

        }

    }



}
