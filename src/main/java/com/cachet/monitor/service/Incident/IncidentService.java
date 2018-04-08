package com.cachet.monitor.service.Incident;

import com.cachet.monitor.exception.ApiMonitorException;
import com.cachet.monitor.internal.model.Incident;
import com.cachet.monitor.service.GatewayCachetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by vins on 06/02/2018.
 */
@Slf4j
@Service
public class IncidentService {

    private final String FIXED = "4";

    private final Integer INCIDENT_STATUS_INVESTIGATING   = 1;

    private final Integer INCIDENT_VISIBLE   = 1;

    private final String COMPONENT_STATUS_PARTIAL_OUTAGE   = "3";

    private final GatewayCachetService gatewayCachetService;

    public IncidentService(GatewayCachetService gatewayCachetService){
        this.gatewayCachetService = gatewayCachetService;
    }

    public Integer checkForIncident(String componentId) throws ApiMonitorException{
        Incident incident = gatewayCachetService.getIncidents();
        if(incident != null && incident.getData() != null &&  incident.getData().size() > 0){
               for(Map<String,Object> data : incident.getData()){
                  if(data.get("component_id").equals(componentId)) {
                      if (!data.get("status").equals(FIXED)) {
                          return (Integer) data.get("id");
                      }
                      return null;
                  }
               }

            return null;

        }else{
            return null;
        }

    }

    public void postIncidents(String incidentName, String message, String componentId, String componentStatus) throws ApiMonitorException{
        gatewayCachetService.postIncidents(incidentName, message, componentId, COMPONENT_STATUS_PARTIAL_OUTAGE, INCIDENT_STATUS_INVESTIGATING, INCIDENT_VISIBLE );
    }

    public void putIncidents(Integer incidentId, String incidentName, String message) throws ApiMonitorException{
        gatewayCachetService.putIncidents(incidentId, incidentName, message);
    }
}
