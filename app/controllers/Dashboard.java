package controllers;

/**
 * Dashboard controller handling all Dashboard related actions.
 *
 * @author Fiona Waters
 * @date 18.05.2021
 * @version 5
 */

import models.Member;
import models.Station;
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
    for (Station station : stations) {
      ReadingAnalytics.readingCalculations(station);
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

