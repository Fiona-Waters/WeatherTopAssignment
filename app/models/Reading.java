package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Date;

@Entity
public class Reading extends Model {
  public int code;
  public float temperature;
  public float windSpeed;
  public float pressure;
  public float windDirection;
  public Date date;


  public Reading(int code, float temperature, float windSpeed, float pressure, float windDirection, Date date) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
    this.date = date;
  }

}
