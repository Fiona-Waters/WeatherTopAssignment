package utils;

import models.Reading;

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
}
