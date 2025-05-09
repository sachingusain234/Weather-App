package com.example.weather_app;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.weather_app.model.Weather;

import java.util.Map;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class WeatherController {
    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            Map body = response.getBody();
            if (body == null) throw new Exception("No data returned from API");
            Map sys = (Map) body.get("sys");
            String country = sys != null ? (String) sys.get("country") : "";
            String weatherIcon = "wi wi-day-sunny"; // You can map icon codes to classes as needed
            String weatherDescription = "N/A";
            if (body.get("weather") instanceof java.util.List) {
                java.util.List weatherList = (java.util.List) body.get("weather");
                if (!weatherList.isEmpty() && weatherList.get(0) instanceof Map) {
                    weatherDescription = (String) ((Map) weatherList.get(0)).get("description");
                }
            }
            Map main = (Map) body.get("main");
            double temperature = main != null && main.get("temp") != null ? ((Number) main.get("temp")).doubleValue() : 0.0;
            int humidity = main != null && main.get("humidity") != null ? ((Number) main.get("humidity")).intValue() : 0;
            Map wind = (Map) body.get("wind");
            double windSpeed = wind != null && wind.get("speed") != null ? ((Number) wind.get("speed")).doubleValue() : 0.0;
            Weather weather = new Weather(city, country, weatherIcon, weatherDescription, temperature, humidity, windSpeed, null);
            model.addAttribute("city", weather.getCity());
            model.addAttribute("country", weather.getCountry());
            model.addAttribute("weatherIcon", weather.getWeatherIcon());
            model.addAttribute("weatherDescription", weather.getWeatherDescription());
            model.addAttribute("temperature", weather.getTemperature());
            model.addAttribute("humidity", weather.getHumidity());
            model.addAttribute("windSpeed", weather.getWindSpeed());
            return "weather";
        } catch (Exception e) {
            model.addAttribute("error", "Could not fetch weather data. " + e.getMessage());
            model.addAttribute("city", city);
            model.addAttribute("country", "");
            model.addAttribute("weatherIcon", "wi wi-na");
            model.addAttribute("weatherDescription", "N/A");
            model.addAttribute("temperature", "N/A");
            model.addAttribute("humidity", "N/A");
            model.addAttribute("windSpeed", "N/A");
            return "weather";
        }
    }
}
