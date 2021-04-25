package models;
import javax.persistence.Entity;
import play.db.jpa.Model;


@Entity
public class Reading extends Model {
    public int code;
    public float temperature;
    public float windSpeed;

    public Reading(int code, float temperature, float windSpeed)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }
}
