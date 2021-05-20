package controllers;

/**
 * About controller using index method to render about.html page
 *
 * @author Fiona Waters
 * @date 18.05.2021
 * @version 5
 */

import play.*;
import play.mvc.*;

public class About extends Controller {

  public static void index() {
    Logger.info("Rendering about");
    render("about.html");
  }
}