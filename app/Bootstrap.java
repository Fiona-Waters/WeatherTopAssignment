/**
 * Bootstrap class loading data.yml file.
 *
 * @author Fiona Waters
 * @date 18.05.2021
 * @version 5
 */

import play.jobs.*;
import play.test.*;

import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
  public void doJob() {
    if (Member.count() == 0) {
      Fixtures.loadModels("data.yml");
    }
  }
}