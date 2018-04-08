package com.cachet.monitor.service;

import com.cachet.monitor.exception.ApiMonitorException;
import com.cachet.monitor.internal.model.Component;
import com.cachet.monitor.internal.model.Incident;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vins on 06/02/2018.
 */
@Slf4j
@Service
public class GatewayCachetService {

    @Autowired
    private RestTemplate restTemplate;

    private final String apiUrl;
    private final String apiToken;

    public GatewayCachetService(@Value("${cachet.setting.apiUrl}")String apiUrl, @Value("${cachet.setting.apiToken}")String apiToken){
        this.apiUrl = apiUrl;
        this.apiToken = apiToken;

    }


    public Incident getIncidents() throws ApiMonitorException{

        try {
            ResponseEntity<Incident> restResponse =
                    restTemplate.exchange(apiUrl+"/incidents",
                            HttpMethod.GET, null, new ParameterizedTypeReference<Incident>() {
                            }
                    );
            return restResponse.getBody();
        }catch (HttpClientErrorException exc) {
            HttpStatus httpStatus = exc.getStatusCode();
            log.error("GetIncidents - Cachet client exception  ", exc);
            throw new ApiMonitorException("GetIncidents - Cachet client exception");
        }catch (Exception e){
            log.error("GetIncidents - Connection error", e);
            throw new ApiMonitorException("GetIncidents - Connection error");
        }

    }

    public void postIncidents(String incidentName, String message, String componentId, String componentStatus,  Integer status, Integer visible) throws ApiMonitorException{
        Map<String, Object> map = new HashMap<>();
        map.put("name", incidentName);
        map.put("message", message);
        map.put("status", status);
        map.put("visible", visible);
        map.put("component_id", Integer.valueOf(componentId));
        map.put("component_status", Integer.valueOf(componentStatus));


        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("X-Cachet-Token", apiToken);
            headers.add("Content-Type", "application/json");
            HttpEntity<Map> request = new HttpEntity<Map>(map, headers);
            restTemplate.postForEntity(apiUrl + "/incidents", request, Map.class);


        }catch (HttpClientErrorException exc) {
            HttpStatus httpStatus = exc.getStatusCode();
            log.error("PostIncidents - Cachet client exception  ", exc);
            throw new ApiMonitorException("GetIncidents - Cachet client exception");
        }catch (Exception e){
            log.error("PostIncidents - Connection error", e);
            throw new ApiMonitorException("PostIncidents - Connection error");
        }

    }

    public void putIncidents(Integer incidentId, String incidentName, String message) throws ApiMonitorException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", incidentName);
        map.put("message", message);

        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("X-Cachet-Token", apiToken);
            headers.add("Content-Type", "application/json");
            HttpEntity<Map> request = new HttpEntity<Map>(map, headers);
            restTemplate.put(apiUrl + "/incidents/"+incidentId, request, Map.class);

        }catch (HttpClientErrorException exc) {
            HttpStatus httpStatus = exc.getStatusCode();
            log.error("PutIncidents - Cachet client exception  ", exc);
            throw new ApiMonitorException("GetIncidents - Cachet client exception");

        } catch (Exception e) {
            log.error("PutIncidents - Connection error", e);
            throw new ApiMonitorException("PutIncidents - Connection error");
        }
    }

    public Component getComponentByID(String componentId) throws ApiMonitorException{
        try {
            ResponseEntity<Component> restResponse =
                    restTemplate.exchange(apiUrl + "/components/" + componentId,
                            HttpMethod.GET, null, new ParameterizedTypeReference<Component>() {
                            }
                    );
            return restResponse.getBody();
        } catch (HttpClientErrorException exc) {
            HttpStatus httpStatus = exc.getStatusCode();
            log.error("GetComponentByID - Cachet client exception  ", exc);
            throw new ApiMonitorException("GetComponentByID - Cachet client exception");
        } catch (Exception e) {
            log.error("GetComponentByID - Connection error", e);
            throw new ApiMonitorException("GetComponentByID - Connection error");
        }
    }



}
