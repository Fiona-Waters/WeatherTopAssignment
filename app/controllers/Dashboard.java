package controllers;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.ReadingAnalytics;

import java.util.*;

public class Dashboard extends Controller {


    public static void index() {

        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        Collections.sort(stations, Comparator.comparing(Station::getName));
        for(int i=0; i<stations.size(); i++) {
            ReadingAnalytics.readingCalculations(stations.get(i));
        }
        render("dashboard.html", member, stations);

    }

    public static void addStation(String title, float lat, float lng) {
        Logger.info("Adding a Station");
        Member member = Accounts.getLoggedInMember();
        Station station = new Station(title, lat, lng);
        member.stations.add(station);
        member.save();
        redirect("/dashboard");
    }


    public static void deleteStation(Long id) {
        Logger.info("Deleting a Station");
        Member member = Accounts.getLoggedInMember();
        Station station = Station.findById(id);
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect("/dashboard");
    }


}

