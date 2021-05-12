package controllers;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Dashboard extends Controller {


    public static void index() {

        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        render("dashboard.html", member, stations);

    }

    public static void addStation(String title, float lat, float lng)
    {
        Member member = Accounts.getLoggedInMember();
        Station station = new Station (title, lat, lng);
        member.stations.add(station);
        member.save();
        Logger.info("Adding a new station called " + title);
        station.save();
        redirect("/dashboard");
    }


    public static void addReading(Long id, int code, float temperature, float windSpeed, float windDirection, float pressure)
    {
        Reading reading = new Reading(code,temperature,windSpeed,windDirection,pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }
}

