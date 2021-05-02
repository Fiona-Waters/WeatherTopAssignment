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


}
