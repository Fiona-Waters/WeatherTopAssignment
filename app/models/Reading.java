package models;
import javax.persistence.Entity;

import play.db.jpa.Model;
import java.util.HashMap;


@Entity
public class Reading extends Model {
    private int code;
    private float temperature;
    private float windSpeed;
    private float pressure;
    //mapping weather description to weather codes
    private HashMap<Integer, String> weatherCodes;

    public Reading(int code, float temperature, float windSpeed, float pressure)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;

        weatherCodes = new HashMap<Integer, String>();
        fillWeatherCodes();

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public HashMap<Integer, String> getWeatherCodes() {
        return weatherCodes;
    }

    public void setWeatherCodes(HashMap<Integer, String> weatherCodes) {
        this.weatherCodes = weatherCodes;
    }

    public String getWeatherCondition()
    {
        return weatherCodes.get(code);
    }

    private void fillWeatherCodes()
    {
        weatherCodes.put(100, "Clear");
        weatherCodes.put(200, "Partial clouds");
        weatherCodes.put(300, "Cloudy");
        weatherCodes.put(400, "Light Showers");
        weatherCodes.put(500, "Heavy Showers");
        weatherCodes.put(600, "Rain");
        weatherCodes.put(700, "Snow");
        weatherCodes.put(800, "Thunder");
    }


}
