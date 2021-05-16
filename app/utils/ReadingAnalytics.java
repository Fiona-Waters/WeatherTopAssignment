package utils;

import models.Reading;
import models.Station;

import java.util.HashMap;
import java.util.List;

public class ReadingAnalytics {

    public static void readingCalculations(Station station)
    {
        fillWeatherCodes();
        fillIconWeatherCodes();

        if (station.readings.size() > 0) {
            Reading lastReading = station.readings.get(station.readings.size() - 1);
            station.weatherCondition = weatherCodes.get(lastReading.code);
            station.weatherConditionIcon = iconWeatherCodes.get(lastReading.code);
            station.fahrenheit = convertCToF(lastReading.temperature);
            station.toBeaufort = convertToBeaufort(lastReading.windSpeed);
            station.windChill = calcWindChill(lastReading.temperature, lastReading.windSpeed);
            station.windCompass = calcWindDirection(lastReading.windDirection);
            station.minTemperature = calcMinimumTemperature(station.readings);
            station.maxTemperature = calcMaximumTemperature(station.readings);
            station.minWindSpeed = calcMinimumWindSpeed(station.readings);
            station.maxWindSpeed = calcMaximumWindSpeed(station.readings);
            station.minPressure = calcMinimumPressure(station.readings);
            station.maxPressure = calcMaximumPressure(station.readings);
            station.tempTrend = tempTrend(station.readings);
            station.windTrend = windSpeedTrend(station.readings);
            station.pressureTrend = pressureTrend(station.readings);
        }
    }


    public static HashMap<Integer, String> weatherCodes = new HashMap<>();

    public static void fillWeatherCodes() {
        weatherCodes.put(100, "Clear");
        weatherCodes.put(200, "Partial clouds");
        weatherCodes.put(300, "Cloudy");
        weatherCodes.put(400, "Light Showers");
        weatherCodes.put(500, "Heavy Showers");
        weatherCodes.put(600, "Rain");
        weatherCodes.put(700, "Snow");
        weatherCodes.put(800, "Thunder");
    }

    public static HashMap<Integer, String> iconWeatherCodes = new HashMap<>();

    public static void fillIconWeatherCodes() {
        iconWeatherCodes.put(100, "sun icon");
        iconWeatherCodes.put(200, "cloud sun icon");
        iconWeatherCodes.put(300, "cloud icon");
        iconWeatherCodes.put(400, "cloud rain icon");
        iconWeatherCodes.put(500, "cloud showers heavy icon");
        iconWeatherCodes.put(600, "cloud rain icon");
        iconWeatherCodes.put(700, "snowman icon");
        iconWeatherCodes.put(800, "bolt icon");
    }

    public static float convertCToF(float celsius) {
        return celsius * 9 / 5 + 32;
    }

    public static int convertToBeaufort(float windSpeed) {
        int beaufort = 0;
        if (windSpeed > 1 && windSpeed <= 5) {
            beaufort = 1;
        } else if (windSpeed > 5 && windSpeed <= 11) {
            beaufort = 2;
        } else if (windSpeed > 11 && windSpeed <= 19) {
            beaufort = 3;
        } else if (windSpeed > 19 && windSpeed <= 28) {
            beaufort = 4;
        } else if (windSpeed > 28 && windSpeed <= 38) {
            beaufort = 5;
        } else if (windSpeed > 38 && windSpeed <= 49) {
            beaufort = 6;
        } else if (windSpeed > 49 && windSpeed <= 61) {
            beaufort = 7;
        } else if (windSpeed > 61 && windSpeed <= 74) {
            beaufort = 8;
        } else if (windSpeed > 74 && windSpeed <= 88) {
            beaufort = 9;
        } else if (windSpeed > 88 && windSpeed <= 102) {
            beaufort = 10;
        } else if (windSpeed > 102 && windSpeed <= 117) {
            beaufort = 11;
        }
        return beaufort;
    }


    public static String calcWindDirection(float windDirection) {
        String compass = null;
        if ((windDirection >= 348.75 && windDirection <= 360) || (windDirection <= 11.25)) {
            compass = "North";
        } else if (windDirection > 11.25 && windDirection <= 33.75) {
            compass = "North North East";
        } else if (windDirection > 33.75 && windDirection <= 56.25) {
            compass = "North East";
        } else if (windDirection > 56.25 && windDirection <= 78.75) {
            compass = "East North East";
        } else if (windDirection > 78.75 && windDirection <= 101.25) {
            compass = "East";
        } else if (windDirection > 101.25 && windDirection <= 123.75) {
            compass = "East South East";
        } else if (windDirection > 123.75 && windDirection <= 146.25) {
            compass = "South East";
        } else if (windDirection > 146.25 && windDirection <= 168.75) {
            compass = "South South East";
        } else if (windDirection > 168.75 && windDirection <= 191.25) {
            compass = "South";
        } else if (windDirection > 191.25 && windDirection <= 213.75) {
            compass = "South South West";
        } else if (windDirection > 213.75 && windDirection <= 236.25) {
            compass = "South West";
        } else if (windDirection > 236.25 && windDirection <= 258.75) {
            compass = "West South West";
        } else if (windDirection > 258.75 && windDirection <= 281.25) {
            compass = "West";
        } else if (windDirection > 281.25 && windDirection <= 303.75) {
            compass = "West North West";
        } else if (windDirection > 303.75 && windDirection <= 326.25) {
            compass = "North West";
        } else if (windDirection > 326.25 && windDirection <= 348.75) {
            compass = "North North West";
        }
        return compass;
    }

    public static float calcWindChill(float temperature, float windSpeed) {
        double calc = Math.pow(windSpeed, 0.16);
        float a = (float) 13.12;
        float b = (float) 0.6215;
        float c = (float) 11.37;
        float d = (float) 0.3965;
        return (float) (a + b * temperature - c * calc + d * temperature * calc);
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
