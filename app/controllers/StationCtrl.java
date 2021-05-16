package controllers;

import java.util.Date;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.ReadingAnalytics;

public class StationCtrl extends Controller {

    public static void index(Long id) {

        Station station = Station.findById(id);
        ReadingAnalytics.readingCalculations(station);
        render("station.html", station);
    }


    public static void addReading(Long id, int code, float temperature, float windSpeed, float windDirection, float pressure) {
        Date now = new Date();
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection, now);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }

    public static void deleteReading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing" + reading);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("/station.html", station);
    }

}
