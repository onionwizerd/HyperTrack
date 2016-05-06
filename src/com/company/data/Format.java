package com.company.data;

/**
 * Created by Josh on 5/6/2016.
 */
public class Format {

    public Format() {
    }

    public int[] formatDate(String date){
        int[] formattedDate = new int[3];

        formattedDate[0] = Integer.parseInt(date.substring(0, date.indexOf("-")));
        formattedDate[1] = Integer.parseInt(date.substring(date.indexOf("-")+1, date.lastIndexOf("-")));
        formattedDate[2] = Integer.parseInt(date.substring(date.lastIndexOf("-")+1, date.length()));

        return formattedDate;
    }

    public int[] formatTime(String time){
        int[] formattedTime = new int[3];

        formattedTime[0] = Integer.parseInt(time.substring(0, time.indexOf(":")));
        formattedTime[1] = Integer.parseInt(time.substring(time.indexOf(":")+1, time.lastIndexOf(":")));
        formattedTime[2] = Integer.parseInt(time.substring(time.lastIndexOf(":")+1, time.length()));

        return formattedTime;
    }

    public int[] formatDistance(String distance){
        int[] formattedDistance = new int[2];


        formattedDistance[0] = Integer.parseInt(distance.substring(0, distance.indexOf(".")));
        formattedDistance[1] = Integer.parseInt(distance.substring(distance.indexOf(".")+1));

        System.out.println("Kilometers = " + formattedDistance[0]);
        System.out.println("Meters = " + formattedDistance[1]);

        return formattedDistance;
    }
}
