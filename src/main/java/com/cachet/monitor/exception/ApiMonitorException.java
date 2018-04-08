package com.cachet.monitor.exception;

/**
 * Created by vins on 05/02/2018.
 */
public class ApiMonitorException extends Exception {

    public ApiMonitorException() {
        super();
    }

    public ApiMonitorException(String message) {
        super(message);
    }

    public ApiMonitorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiMonitorException(Throwable cause) {
        super(cause);
    }

    protected ApiMonitorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
