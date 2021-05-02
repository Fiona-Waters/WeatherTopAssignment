package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Dashboard extends Controller {

    public static void index(){
        fillWeatherCodes();
        Logger.info("Rendering Dashboard");
        List<Station> stations = Station.findAll();

        for(Station station:stations)
        {
            if(station.readings.size() >0)
            {
                Reading lastReading = station.readings.get(station.readings.size()-1);
                station.weatherCondition = weatherCodes.get(lastReading.code);
                station.fahrenheit = convertCToF(lastReading.temperature);
                station.toBeaufort = convertToBeaufort(lastReading.windSpeed);
            }
        }

        render("dashboard.html", stations);
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

    public static float convertCToF(float celsius)
    {
        return celsius * 9/5 + 32;
    }

    public static int convertToBeaufort(float windSpeed) {
        int beaufort = 0;
        if (windSpeed == 1) {
            beaufort = 0;
        } else if ((windSpeed > 1) && (windSpeed <= 5)) {
            beaufort = 1;
        } else if ((windSpeed > 5) && (windSpeed <= 11)) {
            beaufort = 2;
        } else if ((windSpeed > 11) && (windSpeed <= 19)) {
            beaufort = 3;
        } else if ((windSpeed > 19) && (windSpeed <= 28)) {
            beaufort = 4;
        } else if ((windSpeed > 28) && (windSpeed <= 38)) {
            beaufort = 5;
        } else if ((windSpeed > 38) && (windSpeed <= 49)) {
            beaufort = 6;
        } else if ((windSpeed > 49) && (windSpeed <= 61)) {
            beaufort = 7;
        } else if ((windSpeed > 61) && (windSpeed <= 74)) {
            beaufort = 8;
        } else if ((windSpeed > 74) && (windSpeed <= 88)) {
            beaufort = 9;
        } else if ((windSpeed > 88) && (windSpeed <= 102)) {
            beaufort = 10;
        } else if ((windSpeed > 102) && (windSpeed <= 117))
        {
            beaufort = 11;
        }
        return beaufort;
    }



}
