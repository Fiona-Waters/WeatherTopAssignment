package controllers;

import play.Logger;
import play.mvc.Controller;

public class Main extends Controller {

    public static void index()
    {
        Logger.info("Rendering Main");
        render ("main.html");
    }
}
