package com.cachet.monitor.service.component;

import com.cachet.monitor.exception.ApiMonitorException;
import com.cachet.monitor.internal.model.Component;
import com.cachet.monitor.service.GatewayCachetService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * Created by vins on 06/02/2018.
 */
@Slf4j
@Service
public class ComponentService {

    private final GatewayCachetService gatewayCachetService;

    public ComponentService(GatewayCachetService gatewayCachetService){
        this.gatewayCachetService = gatewayCachetService;
    }

    public String getComponentsByID(String componentId) throws ApiMonitorException{
        Component component = gatewayCachetService.getComponentByID(componentId);
        if(component != null && component.getData() != null && component.getData().size() > 0){
            return (String)component.getData().get("status");
        }
        return null;
    }

    public void putComponentsByID(String componentId, Integer status){
       // gatewayCachetService.put();
    }
}
