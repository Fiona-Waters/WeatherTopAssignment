package models;
/**
 * Member model handling station related data.
 *
 * @author Fiona Waters
 * @date 18.05.2021
 * @version 5
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {

  public String name;
  public float lat;
  public float lng;
  public String weatherCondition;
  public String weatherConditionIcon;
  public float fahrenheit;
  public int toBeaufort;
  public String windCompass;
  public float windChill;
  public float minTemperature;
  public float maxTemperature;
  public float maxWindSpeed;
  public float minWindSpeed;
  public float maxPressure;
  public float minPressure;
  public String tempTrend;
  public String windTrend;
  public String pressureTrend;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name, float lat, float lng) {
    this.name = name;
    this.lat = lat;
    this.lng = lng;
  }


  public Reading getLatestReading() {
    if (readings.size() >= 1) {
      return readings.get(readings.size() - 1);
    } else {
      return null;
    }
  }

  public String getName() {
    return name;
  }
}
