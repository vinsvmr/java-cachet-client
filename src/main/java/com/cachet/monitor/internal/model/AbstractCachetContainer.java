package com.cachet.monitor.internal.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vins on 06/02/2018.
 */
@Data
public class AbstractCachetContainer {
    private Map<String,Object> meta = new HashMap<>();
}
