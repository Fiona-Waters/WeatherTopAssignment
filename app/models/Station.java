package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {

    public String weatherCondition;
    public float fahrenheit;
    public int toBeaufort;
    public String windCompass;
    public float windChill;
    public String name;
    public float lat;
    public float lng;


    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    public Station(String name)
    {
        this.name = name;
        //this.lat = lat;
        //this.lng = lng;
    }

    //do I need this method?
    public Reading getLatestReading()
    {

        if (readings.size() >=1)
            {
                return readings.get(readings.size() - 1);
            }
            else
            {
                return null;
            }


    }
}
