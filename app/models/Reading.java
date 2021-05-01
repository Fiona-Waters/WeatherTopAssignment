package models;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model {
    //public String weatherCondition;
    public int code;
    public float temperature;
    public float windSpeed;
    public float pressure;

    public Reading(int code, float temperature, float windSpeed, float pressure)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;

    }

   /* public String getWeatherCondition()
    {
      return weatherCondition;
    }

    */



}
