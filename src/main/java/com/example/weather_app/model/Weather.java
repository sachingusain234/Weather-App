package com.example.weather_app.model;

public class Weather {
    private String city;
    private String country;
    private String weatherIcon;
    private String weatherDescription;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private String error;

    public Weather() {}

    public Weather(String city, String country, String weatherIcon, String weatherDescription, double temperature, int humidity, double windSpeed, String error) {
        this.city = city;
        this.country = country;
        this.weatherIcon = weatherIcon;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.error = error;
    }

    // Getters and setters for all fields
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getWeatherIcon() { return weatherIcon; }
    public void setWeatherIcon(String weatherIcon) { this.weatherIcon = weatherIcon; }
    public String getWeatherDescription() { return weatherDescription; }
    public void setWeatherDescription(String weatherDescription) { this.weatherDescription = weatherDescription; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
} 