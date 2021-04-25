package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Controller {

    public static void index(){
        Logger.info("Rendering Dashboard");
        List<Station> stations = Station.findAll();
       // List<Reading> readings = Reading.findAll();
        render("dashboard.html", stations);
    }
}
