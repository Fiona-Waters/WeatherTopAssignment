package controllers;
import java.util.HashMap;
import java.util.List;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.ReadingAnalytics;

public class StationCtrl extends Controller {

    public static void index(Long id)
    {
        fillWeatherCodes();
        fillIconWeatherCodes();
        Station station = Station.findById(id);

        if (station.readings.size() > 0) {
            Reading lastReading = station.readings.get(station.readings.size() - 1);
            station.weatherCondition = weatherCodes.get(lastReading.code);
            station.weatherConditionIcon = iconWeatherCodes.get(lastReading.code);
            station.fahrenheit = convertCToF(lastReading.temperature);
            station.toBeaufort = convertToBeaufort(lastReading.windSpeed);
            station.windChill = calcWindChill(lastReading.temperature, lastReading.windSpeed);
            station.windCompass = calcWindDirection(lastReading.windDirection);
            station.minTemperature = ReadingAnalytics.calcMinimumTemperature(station.readings);
            station.maxTemperature = ReadingAnalytics.calcMaximumTemperature(station.readings);
            station.minWindSpeed = ReadingAnalytics.calcMinimumWindSpeed(station.readings);
            station.maxWindSpeed = ReadingAnalytics.calcMaximumWindSpeed(station.readings);
            station.minPressure = ReadingAnalytics.calcMinimumPressure(station.readings);
            station.maxPressure = ReadingAnalytics.calcMaximumPressure(station.readings);
            station.tempTrend = ReadingAnalytics.tempTrend(station.readings,id);
            station.windTrend = ReadingAnalytics.windSpeedTrend(station.readings,id);
            station.pressureTrend = ReadingAnalytics.pressureTrend(station.readings,id);
        }

        render("station.html",station);
    }
    static HashMap<Integer, String> weatherCodes = new HashMap<>();

    static void fillWeatherCodes()
    {
        weatherCodes.put(100, "Clear");
        weatherCodes.put(200, "Partial clouds");
        weatherCodes.put(300, "Cloudy");
        weatherCodes.put(400, "Light Showers");
        weatherCodes.put(500, "Heavy Showers");
        weatherCodes.put(600, "Rain");
        weatherCodes.put(700, "Snow");
        weatherCodes.put(800, "Thunder");
    }

    static HashMap<Integer, String> iconWeatherCodes = new HashMap<>();

    static void fillIconWeatherCodes()
    {
        iconWeatherCodes.put(100,"sun icon");
        iconWeatherCodes.put(200,"cloud sun icon");
        iconWeatherCodes.put(300, "cloud icon");
        iconWeatherCodes.put(400, "cloud rain icon");
        iconWeatherCodes.put(500, "cloud showers heavy icon");
        iconWeatherCodes.put(600, "cloud rain icon");
        iconWeatherCodes.put(700, "snowman icon");
        iconWeatherCodes.put(800, "bolt icon");
    }
    public static float convertCToF(float celsius)
    {
        return celsius * 9/5 + 32;
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
        } else if (windSpeed > 102 && windSpeed <= 117)
        {
            beaufort = 11;
        }
        return beaufort;
    }

    //calculate windCompass method
    public static String calcWindDirection(float windDirection)
    {
        String compass = null;
        if(windDirection >=348.75 && windDirection <=360 && windDirection<=11.25)
        {
            compass = "North";
        }
        else if(windDirection >11.25 && windDirection <=33.75)
        {
            compass = "North North East";
        }
        else if(windDirection >33.75 && windDirection <=56.25)
        {
            compass = "North East";
        }
        else if(windDirection >56.25 && windDirection <=78.75)
        {
            compass = "East North East";
        }
        else if(windDirection >78.75 && windDirection<=101.25)
        {
            compass = "East";
        }
        else if(windDirection>101.25 && windDirection <=123.75)
        {
            compass = "East South East";
        }
        else if(windDirection>123.75 && windDirection<=146.25)
        {
            compass = "South East";
        }
        else if(windDirection >146.25 && windDirection<=168.75)
        {
            compass = "South South East";
        }
        else if(windDirection>168.75 && windDirection <=191.25)
        {
            compass = "South";
        }
        else if(windDirection>191.25 && windDirection <=213.75)
        {
            compass = "South South West";
        }
        else if(windDirection>213.75 && windDirection <=236.25)
        {
            compass = "South West";
        }
        else if(windDirection>236.25 && windDirection <=258.75)
        {
            compass = "West South West";
        }
        else if(windDirection >258.75 && windDirection <=281.25)
        {
            compass = "West";
        }
        else if(windDirection >281.25 && windDirection <=303.75)
        {
            compass = "West North West";
        }
        else if(windDirection >303.75 && windDirection <=326.25)
        {
            compass = "North West";
        }
        else if(windDirection >326.25 && windDirection <=348.75)
        {
            compass = "North North West";
        }
        return compass;
    }

    //calculate windChill method
    public static float calcWindChill(float temperature,float windSpeed)
    {
        double calc = Math.pow(windSpeed,0.16);
        float a = (float) 13.12;
        float b = (float) 0.6215;
        float c = (float) 11.37;
        float d = (float) 0.3965;
        return (float)(a + b*temperature - c*calc+ d*temperature*calc);
    }


}
