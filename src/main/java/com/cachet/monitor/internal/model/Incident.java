package com.cachet.monitor.internal.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vins on 06/02/2018.
 */
@Data
public class Incident extends AbstractCachetContainer  {

    private List<Map<String,Object>> data = new ArrayList<>();


}
