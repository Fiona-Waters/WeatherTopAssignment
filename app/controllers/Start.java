package controllers;
/**
 * Start controller using index method to render signup.html page at start up
 *
 * @author Fiona Waters
 * @date 18.05.2021
 * @version 5
 */

import play.Logger;
import play.mvc.Controller;

public class Start extends Controller {

  public static void index() {
    Logger.info("Rendering Main");
    render("signup.html");
  }
}
