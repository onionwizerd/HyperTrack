package com.company.fitness.data.dataentry;

import com.company.fitness.data.dataentry.components.DateComponent;
import com.company.fitness.data.dataentry.components.DistanceComponent;
import com.company.fitness.data.dataentry.components.SpeedComponent;
import com.company.fitness.data.dataentry.components.TimeComponent;

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
