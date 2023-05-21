import java.util.List;

public class WeatherAnalyzer {
    private List<WeatherDay> weatherDays;
    private List<WeatherDay> bestDay;

    public List<WeatherDay> getBestDay() {
        return bestDay;
    }

    public WeatherAnalyzer(List<WeatherDay> days) {
        weatherDays = days;

    }
    public double getAverageTemperature(List<WeatherDay> days) {
        int sum = 0;
        for (WeatherDay day : weatherDays) {
            sum += day.getTemperature();
        }
        //rounded to 2 decimal places
        return Math.round((double) sum / weatherDays.size() * 100.0) / 100.0;
    }
    //returns the day with the highest temperature
    public WeatherDay getHottestDay(List<WeatherDay> days) {
        WeatherDay hottestDay = weatherDays.get(0);
        for (WeatherDay day : weatherDays) {
            if (day.getTemperature() > hottestDay.getTemperature()) {
                hottestDay = day;
            }
        }
        return hottestDay;
    }
    //returns the day with the lowest temperature
    public WeatherDay getColdestDay(List<WeatherDay> days) {
        WeatherDay coldestDay = weatherDays.get(0);
        for (WeatherDay day : weatherDays) {
            if (day.getTemperature() < coldestDay.getTemperature()) {
                coldestDay = day;
            }
        }
        return coldestDay;
    }
    //returns the day with the average wind speed
    public double getAverageWindSpeed(List<WeatherDay> days) {
        int sum = 0;
        for (WeatherDay day : weatherDays) {
            sum += day.getWindSpeed();
        }
        //rounded to 2 decimal places
        return Math.round((double) sum / weatherDays.size() * 100.0) / 100.0;
    }
    //returns the day with the highest wind speed
    public WeatherDay getWindyDay(List<WeatherDay> days) {
        WeatherDay windyDay = weatherDays.get(0);
        for (WeatherDay day : weatherDays) {
            if (day.getWindSpeed() > windyDay.getWindSpeed()) {
                windyDay = day;
            }
        }
        return windyDay;
    }
    //returns the day with the lowest wind speed
    public WeatherDay getCalmDay(List<WeatherDay> days) {
        WeatherDay calmDay = weatherDays.get(0);
        for (WeatherDay day : weatherDays) {
            if (day.getWindSpeed() < calmDay.getWindSpeed()) {
                calmDay = day;
            }
        }
        return calmDay;
    }
    //returns the day with the average humidity
    public double getAverageHumidity(List<WeatherDay> days) {
        int sum = 0;
        for (WeatherDay day : weatherDays) {
            sum += day.getHumidity();
        }
        //rounded to 2 decimal places
        return Math.round((double) sum / weatherDays.size() * 100.0) / 100.0;
    }
    //returns the day with the highest humidity
    public WeatherDay getHumidDay(List<WeatherDay> days) {
        WeatherDay humidDay = weatherDays.get(0);
        for (WeatherDay day : weatherDays) {
            if (day.getHumidity() > humidDay.getHumidity()) {
                humidDay = day;
            }
        }
        return humidDay;
    }
    //returns the day with the lowest humidity
    public WeatherDay getDryDay(List<WeatherDay> days) {
        WeatherDay dryDay = weatherDays.get(0);
        for (WeatherDay day : weatherDays) {
            if (day.getHumidity() < dryDay.getHumidity()) {
                dryDay = day;
            }
        }
        return dryDay;
    }
    //returns the day with the average precipitation
    public double getAveragePrecipitation(List<WeatherDay> days) {
        int sum = 0;
        for (WeatherDay day : weatherDays) {
            sum += day.getPrecipitation();
        }
        //rounded to 2 decimal places
        return Math.round((double) sum / weatherDays.size() * 100.0) / 100.0;
    }
    //returns the day with the highest precipitation
    public WeatherDay getRainyDay(List<WeatherDay> days) {
        WeatherDay rainyDay = weatherDays.get(0);
        for (WeatherDay day : weatherDays) {
            if (day.getPrecipitation() > rainyDay.getPrecipitation()) {
                rainyDay = day;
            }
        }
        return rainyDay;
    }
    //returns the day with the lowest precipitation
    public WeatherDay getDryDay2(List<WeatherDay> days) {
        WeatherDay dryDay = weatherDays.get(0);
        for (WeatherDay day : weatherDays) {
            if (day.getPrecipitation() < dryDay.getPrecipitation()) {
                dryDay = day;
            }
        }
        return dryDay;
    }
    //returns the median value of the temperature
    public double getMedianTemperature(List<WeatherDay> days) {
        int size = weatherDays.size();
        if (size % 2 == 0) {
            return (weatherDays.get(size / 2).getTemperature() + weatherDays.get(size / 2 - 1).getTemperature()) / 2.0;
        } else {
            return weatherDays.get(size / 2).getTemperature();
        }
    }
    //returns the median value of the wind speed
    public double getMedianWindSpeed(List<WeatherDay> days) {
        int size = weatherDays.size();
        if (size % 2 == 0) {
            return (weatherDays.get(size / 2).getWindSpeed() + weatherDays.get(size / 2 - 1).getWindSpeed()) / 2.0;
        } else {
            return weatherDays.get(size / 2).getWindSpeed();
        }
    }
    //returns the median value of the humidity
    public double getMedianHumidity(List<WeatherDay> days) {
        int size = weatherDays.size();
        if (size % 2 == 0) {
            return (weatherDays.get(size / 2).getHumidity() + weatherDays.get(size / 2 - 1).getHumidity()) / 2.0;
        } else {
            return weatherDays.get(size / 2).getHumidity();
        }
    }
    //returns the median value of the precipitation
    public double getMedianPrecipitation(List<WeatherDay> days) {
        int size = weatherDays.size();
        if (size % 2 == 0) {
            return (weatherDays.get(size / 2).getPrecipitation() + weatherDays.get(size / 2 - 1).getPrecipitation()) / 2.0;
        } else {
            return weatherDays.get(size / 2).getPrecipitation();
        }
    }
}
