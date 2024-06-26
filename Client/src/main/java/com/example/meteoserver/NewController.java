import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class NewController {

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("/averageTemperature")
    public double getAverageTemperature(
            @RequestParam("city") String city,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {

        List<WeatherData> weatherDataList = weatherRepository.findByCityAndDateBetween(city, startDate, endDate);

        double sumTemperature = 0;
        for (WeatherData weatherData : weatherDataList) {
            sumTemperature += weatherData.getTemperature();
        }
        double averageTemperature = sumTemperature / weatherDataList.size();

        return averageTemperature;
    }
}
