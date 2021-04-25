import java.util.List;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob()
    {
        Fixtures.loadModels("data.yml");
    }
}
