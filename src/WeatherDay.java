public class WeatherDay {
    private int day;
    private int temperature;
    private int windSpeed;
    private int humidity;
    private int precipitation;
    private String lightning;
    private String clouds;

    public WeatherDay(int day, int temperature, int windSpeed, int humidity, int precipitation, String lightning, String clouds) {
        this.day = day;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.lightning = lightning;
        this.clouds = clouds;
    }

    public int getDay() {
        return day;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public String isLightning() {
        return lightning;
    }

    public String getClouds() {
        return clouds;
    }

    @Override
    public String toString() {
        return "Day=" + day +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", humidity=" + humidity +
                ", precipitation=" + precipitation +
                ", lightning=" + lightning +
                ", clouds=" + clouds;
    }
}
