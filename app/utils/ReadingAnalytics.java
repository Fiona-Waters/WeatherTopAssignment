package utils;

import models.Reading;
import models.Station;

import java.util.List;

public class ReadingAnalytics {

    public static float calcMinimumTemperature(List<Reading> readings)
    {
        float minValue = 0;
        if (readings.size() > 0)
        {
            minValue = readings.get(0).temperature;
            for (int i=0; i<readings.size(); i++)
            {
                if(readings.get(i).temperature < minValue)
                {
                    minValue = readings.get(i).temperature;
                }
            }
        }
        return minValue;
    }

    public static float calcMaximumTemperature(List<Reading> readings)
    {
        float maxValue = 0;
        if(readings.size() >0)
        {
            maxValue = readings.get(0).temperature;
            for(int i=0; i<readings.size(); i++)
            {
                if(readings.get(i).temperature > maxValue)
                {
                    maxValue = readings.get(i).temperature;
                }
            }
        }
        return maxValue;
    }

    public static float calcMaximumWindSpeed(List<Reading> readings)
    {
        float maxValue = 0;
        if(readings.size() >0)
        {
            maxValue = readings.get(0).windSpeed;
            for(int i=0; i<readings.size(); i++)
            {
                if(readings.get(i).windSpeed > maxValue)
                {
                    maxValue = readings.get(i).windSpeed;
                }
            }
        }
        return maxValue;
    }

    public static float calcMinimumWindSpeed(List<Reading> readings)
    {
        float minValue = 0;
        if(readings.size() >0)
        {
            minValue = readings.get(0).windSpeed;
            for(int i=0; i<readings.size(); i++)
            {
                if(readings.get(i).windSpeed < minValue)
                {
                    minValue = readings.get(i).windSpeed;
                }
            }
        }
        return minValue;
    }

    public static float calcMaximumPressure(List <Reading> readings)
    {
        float maxValue = 0;
        if(readings.size() >0)
        {
            maxValue = readings.get(0).pressure;
            for(int i=0; i<readings.size(); i++)
            {
                if(readings.get(i).pressure > maxValue)
                {
                    maxValue = readings.get(i).pressure;
                }
            }
        }
        return maxValue;
    }

    public static float calcMinimumPressure(List <Reading> readings)
    {
        float minValue = 0;
        if(readings.size() >0)
        {
            minValue = readings.get(0).pressure;
            for(int i=0; i<readings.size(); i++)
            {
                if(readings.get(i).pressure < minValue)
                {
                    minValue = readings.get(i).pressure;
                }
            }
        }
        return minValue;
    }

    public static String tempTrend(List <Reading> readings, long id) {
        Station station = Station.findById(id);
        String result = "";
        if (readings.size() >= 3) {
            float lastTemp = station.readings.get(station.readings.size() - 1).temperature;
            float secondLastTemp = station.readings.get(station.readings.size() - 2).temperature;
            float thirdLastTemp = station.readings.get(station.readings.size() - 3).temperature;

            if (lastTemp > secondLastTemp && secondLastTemp > thirdLastTemp)
            {
                result = "Rising";
            }
            else if (thirdLastTemp < secondLastTemp && secondLastTemp < lastTemp)
            {
                result = "Falling";
            }
            else
            {result = "Steady";}
        }
        return result;
    }

    public static String windSpeedTrend(List <Reading> readings, long id) {
        Station station = Station.findById(id);
        String result = "";
        if (readings.size() >= 3) {
            float lastWindSpeed = station.readings.get(station.readings.size() - 1).windSpeed;
            float secondLastWindSpeed = station.readings.get(station.readings.size() - 2).windSpeed;
            float thirdLastWindSpeed = station.readings.get(station.readings.size() - 3).windSpeed;

            if (lastWindSpeed > secondLastWindSpeed && secondLastWindSpeed > thirdLastWindSpeed)
            {
                result = "Rising";
            }
            else if (thirdLastWindSpeed < secondLastWindSpeed && secondLastWindSpeed < lastWindSpeed)
            {
                result = "Falling";
            }
            else
            {result = "Steady";}
        }
        return result;
    }

    public static String pressureTrend(List <Reading> readings, long id) {
        Station station = Station.findById(id);
        String result = "";
        if (readings.size() >= 3) {
            float lastPressure = station.readings.get(station.readings.size() - 1).pressure;
            float secondLastPressure = station.readings.get(station.readings.size() - 2).pressure;
            float thirdLastPressure = station.readings.get(station.readings.size() - 3).pressure;

            if (lastPressure > secondLastPressure && secondLastPressure > thirdLastPressure)
            {
                result = "Rising";
            }
            else if (thirdLastPressure < secondLastPressure && secondLastPressure < lastPressure)
            {
                result = "Falling";
            }
            else
            {result = "Steady";}
        }
        return result;
    }



}
