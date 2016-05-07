package com.company.data.dataentry;

import com.company.data.dataentry.components.DateComponent;
import com.company.data.dataentry.components.DistanceComponent;
import com.company.data.dataentry.components.SpeedComponent;
import com.company.data.dataentry.components.TimeComponent;

/**
 * Created by Josh on 5/7/2016.
 */
public class ComponentManager {

    public static DateComponent dateComponent;
    public static DistanceComponent distanceComponent;
    public static TimeComponent timeComponent;
    public static SpeedComponent speedComponent;


    public static void calculateSpeed(){
        speedComponent.calculateSpeed(timeComponent.getTimeAsArray(), distanceComponent.getValueAsDouble());
    }
}
