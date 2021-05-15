package utils;

import controllers.StationCtrl;
import models.Reading;
import models.Station;

import java.util.List;

public class ReadingAnalytics {

    public static void readingCalculations(Station station)
    {
        if (station.readings.size() > 0) {
            Reading lastReading = station.readings.get(station.readings.size() - 1);
            station.weatherCondition = StationCtrl.weatherCodes.get(lastReading.code);
            station.weatherConditionIcon = StationCtrl.iconWeatherCodes.get(lastReading.code);
            station.fahrenheit = StationCtrl.convertCToF(lastReading.temperature);
            station.toBeaufort = StationCtrl.convertToBeaufort(lastReading.windSpeed);
            station.windChill = StationCtrl.calcWindChill(lastReading.temperature, lastReading.windSpeed);
            station.windCompass = StationCtrl.calcWindDirection(lastReading.windDirection);
            station.minTemperature = ReadingAnalytics.calcMinimumTemperature(station.readings);
            station.maxTemperature = ReadingAnalytics.calcMaximumTemperature(station.readings);
            station.minWindSpeed = ReadingAnalytics.calcMinimumWindSpeed(station.readings);
            station.maxWindSpeed = ReadingAnalytics.calcMaximumWindSpeed(station.readings);
            station.minPressure = ReadingAnalytics.calcMinimumPressure(station.readings);
            station.maxPressure = ReadingAnalytics.calcMaximumPressure(station.readings);
            station.tempTrend = ReadingAnalytics.tempTrend(station.readings);
            station.windTrend = ReadingAnalytics.windSpeedTrend(station.readings);
            station.pressureTrend = ReadingAnalytics.pressureTrend(station.readings);
        }
    }

    public static float calcMinimumTemperature(List<Reading> readings)
    {
        float minValue = 0;
        if (readings.size() > 0)
        {
            minValue = readings.get(0).temperature;
            for (Reading reading : readings) {
                if (reading.temperature < minValue) {
                    minValue = reading.temperature;
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
            for (Reading reading : readings) {
                if (reading.temperature > maxValue) {
                    maxValue = reading.temperature;
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
            for (Reading reading : readings) {
                if (reading.windSpeed > maxValue) {
                    maxValue = reading.windSpeed;
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
            for (Reading reading : readings) {
                if (reading.windSpeed < minValue) {
                    minValue = reading.windSpeed;
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

    public static String tempTrend(List <Reading> readings) {

        String result = "";
        if (readings.size() >= 3) {
            float lastTemp = readings.get(readings.size() - 1).temperature;
            float secondLastTemp = readings.get(readings.size() - 2).temperature;
            float thirdLastTemp = readings.get(readings.size() - 3).temperature;

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

    public static String windSpeedTrend(List <Reading> readings) {
        String result = "";
        if (readings.size() >= 3) {
            float lastWindSpeed = readings.get(readings.size() - 1).windSpeed;
            float secondLastWindSpeed = readings.get(readings.size() - 2).windSpeed;
            float thirdLastWindSpeed = readings.get(readings.size() - 3).windSpeed;

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

    public static String pressureTrend(List <Reading> readings) {
        String result = "";
        if (readings.size() >= 3) {
            float lastPressure = readings.get(readings.size() - 1).pressure;
            float secondLastPressure = readings.get(readings.size() - 2).pressure;
            float thirdLastPressure = readings.get(readings.size() - 3).pressure;

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
