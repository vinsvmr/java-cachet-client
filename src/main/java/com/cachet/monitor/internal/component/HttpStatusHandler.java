package com.cachet.monitor.internal.component;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vins on 06/02/2018.
 */
@Component
public class HttpStatusHandler {

    private final Map<String, String> httpErrorStatus;


    public HttpStatusHandler(){
        httpErrorStatus = new HashMap<>();
        httpErrorStatus.put("520", "Web server is returning an unknown error (Cloudflare)");
        httpErrorStatus.put("521", "Web server is down (Cloudflare)");
        httpErrorStatus.put("522", "Connection timed out (Cloudflare)");
        httpErrorStatus.put("523", "Origin is unreachable (Cloudflare)");
        httpErrorStatus.put("524", "A timeout occurred (Cloudflare)");
        httpErrorStatus.put("525", "SSL handshake failed (Cloudflare)");
        httpErrorStatus.put("526", "Invalid SSL certificate (Cloudflare)");
        httpErrorStatus.put("444", "No Response (Nginx)");
        httpErrorStatus.put("494", "Request Header Too Large (Nginx)");
        httpErrorStatus.put("495", "Cert Error (Nginx)");
        httpErrorStatus.put("496", "No Cert (Nginx)");
        httpErrorStatus.put("497", "HTTP to HTTPS (Nginx)");
        httpErrorStatus.put("499", "Client Closed Request (Nginx)");
        httpErrorStatus.put("102", "Server has received and is processing the request");
        httpErrorStatus.put("103", "resume aborted PUT or POST requests");
        httpErrorStatus.put("122", "URI is longer than a maximum of 2083 characters");
        httpErrorStatus.put("207", "XML return with possible multiple seperate responses.");
        httpErrorStatus.put("208", "The results are previously returned.");
        httpErrorStatus.put("226", "The request has been fulfilled amd response is in instance manipulations.");
        httpErrorStatus.put("308", "Please connect again to the different URL using the same method.");
        httpErrorStatus.put("418", "I'm a teapot.");
        httpErrorStatus.put("420", "Method Failure");
        httpErrorStatus.put("421", "Enhance Your Calm");
        httpErrorStatus.put("422", "Unprocessable Entity");
        httpErrorStatus.put("423", "Locked");
        httpErrorStatus.put("424", "Failed Dependency");
        httpErrorStatus.put("426", "Upgrade Required");
        httpErrorStatus.put("428", "Precondition Required");
        httpErrorStatus.put("429", "Too Many Requests");
        httpErrorStatus.put("431", "Request Header Fields Too Large");
        httpErrorStatus.put("440", "Login Timeout (Microsoft)");
        httpErrorStatus.put("449", "Retry With (Microsoft)");
        httpErrorStatus.put("450", "Blocked by Windows Parental Controls");
        httpErrorStatus.put("451", "Unavailable For Legal Reasons");
        httpErrorStatus.put("506", "Variant Also Negotiates (RFC 2295)");
        httpErrorStatus.put("507", "Insufficient Storage (WebDAV; RFC 4918)");
        httpErrorStatus.put("508", "Loop Detected (WebDAV; RFC 5842)");
        httpErrorStatus.put("509", "Bandwidth Limit Exceeded (Apache bw/limited extension)");
        httpErrorStatus.put("510", "Not Extended (RFC 2774)");
        httpErrorStatus.put("511", "Network Authentication Required");
    }

    public String get(String code){
        return  httpErrorStatus.get(code);
    }

    public Map<String, String> getAllHttpError(){
        return httpErrorStatus;
    }
}
