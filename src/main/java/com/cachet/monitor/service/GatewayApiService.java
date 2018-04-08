package com.cachet.monitor.service;

import com.cachet.monitor.exception.ApiMonitorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vins on 05/02/2018.
 */
@Slf4j
@Service
public class GatewayApiService {

    @Autowired
    private RestTemplate restTemplate;


    public String getApiStatus(String url) {
        log.debug("Retrieving status for url ");
        try{
            ResponseEntity<Object> response
                    = restTemplate.getForEntity(url, Object.class);
            return response.getStatusCode().toString();

        }catch (HttpClientErrorException ex) {
            HttpStatus httpStatus = ex.getStatusCode();
            return httpStatus.toString();
        }catch (Exception e) {
            // connect exception
            return "-1";
        }
    }


}
