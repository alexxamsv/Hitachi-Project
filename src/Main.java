import javax.mail.MessagingException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, MessagingException {
        Scanner scanner = new Scanner(System.in);
        //The application should take 4 input parameters – File name (path to the file on the file system), Sender email address, Password, Receiver email address.


        String fileName = scanner.nextLine();
        String senderEmail = scanner.nextLine();
        String senderPassword = scanner.nextLine();
        String receiverEmail = scanner.nextLine();

        List<String> parameters = new ArrayList<>();
        parameters.add(fileName);
        parameters.add(senderEmail);
        parameters.add(senderPassword);
        parameters.add(receiverEmail);

        if (parameters.size() != 4) {
            System.out.println("Please enter 4 arguments");
            System.exit(0);
        }


        List<String> fileLines = readFile(fileName);
        if (fileLines == null) {
            System.exit(0);
        }
        List<WeatherDay> daysCSV = parseCSV(fileLines);
        List<WeatherDay> goodDays = filterGoodWeather(daysCSV);
        WeatherDay bestDayFound = bestDay(goodDays);
        //object of WeatherAnalyzer class
        WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer(daysCSV);
        createFileWeatherRepost(goodDays, weatherAnalyzer, bestDayFound);
        //object of MailSender class
        MailSender mailSender = new MailSender();
        mailSender.sendMail(receiverEmail, senderEmail, senderPassword, "weatherReport.csv");


    }

    private static WeatherDay bestDay(List<WeatherDay> goodDays) {
        WeatherDay bestDay = goodDays.get(0);
        for (WeatherDay goodDay : goodDays) {
            if (goodDay.getWindSpeed() + goodDay.getHumidity() < bestDay.getWindSpeed() + bestDay.getHumidity()) {
                bestDay = goodDay;
            }

        }
        //return best day
        return bestDay;
    }

    private static List<WeatherDay> parseCSV(List<String> fileLines) {
        List<WeatherDay> results = new ArrayList<WeatherDay>();

        for (int i = 1; i < fileLines.size(); i++) {
            String line = fileLines.get(i);
            String[] tokens = line.split(",");
            int day = Integer.parseInt(tokens[0]);
            int temperature = Integer.parseInt(tokens[1]);
            int windSpeed = Integer.parseInt(tokens[2]);
            int humidity = Integer.parseInt(tokens[3]);
            int precipitation = Integer.parseInt(tokens[4]);
            String lightning = tokens[5];
            String clouds = tokens[6];


            WeatherDay createdDay = new WeatherDay(day, temperature, windSpeed, humidity, precipitation, lightning, clouds);
            results.add(createdDay);


        }
        return results;
    }

    private static boolean goodWeather(int day, int temperature, int windSpeed, int humidity, int precipitation, String lightning, String clouds) {
        if (temperature <= 2 && temperature >= 31) {
            return false;
        }
        if (windSpeed >= 10) {
            return false;
        }
        if (humidity >= 60) {
            return false;
        }
        if (precipitation != 0 || !lightning.equals("No") || clouds.equals("cumulus") || clouds.equals("nimbus")) {
            return false;
        }
        return true;
    }

    public static List<String> readFile(String fileName) throws IOException {
        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            System.out.println("File exists");
        } else {
            System.out.println("File does not exist");
            return null;
        }
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String line : lines) {
            System.out.println(line);
        }
        return lines;
    }

    private static List<WeatherDay> filterGoodWeather(List<WeatherDay> days) {
        List<WeatherDay> goodDays = new ArrayList<>();
        for (WeatherDay weatherDay : days) {
            if (goodWeather(weatherDay.getDay(), weatherDay.getTemperature(), weatherDay.getWindSpeed(), weatherDay.getHumidity(), weatherDay.getPrecipitation(), weatherDay.isLightning(), weatherDay.getClouds())) {
                goodDays.add((weatherDay));
            }
        }
        return goodDays;
    }
    private static void createFileWeatherRepost(List<WeatherDay> goodDays,WeatherAnalyzer weatherAnalyzer, WeatherDay bestDay) throws IOException {
        File file = new File("weatherReport.csv");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Day,Temperature,Wind Speed,Humidity,Precipitation,Lightning,Clouds");
        printWriter.println("," + weatherAnalyzer.getAverageTemperature(goodDays) + "," +
                weatherAnalyzer.getAverageWindSpeed(goodDays) +
                "," + weatherAnalyzer.getAverageHumidity(goodDays) +
                "," + weatherAnalyzer.getAveragePrecipitation(goodDays)+ "," + ",");
        printWriter.println("," + weatherAnalyzer.getHottestDay(goodDays).getTemperature()  + "," +
                weatherAnalyzer.getWindyDay(goodDays).getWindSpeed() +
                "," + weatherAnalyzer.getHumidDay(goodDays).getHumidity() +
                "," + weatherAnalyzer.getRainyDay(goodDays).getPrecipitation()+ "," + ",");
        printWriter.println("," + weatherAnalyzer.getColdestDay(goodDays).getTemperature() + "," +
                weatherAnalyzer.getCalmDay(goodDays).getWindSpeed() +
                "," + weatherAnalyzer.getDryDay(goodDays).getHumidity() +
                "," + weatherAnalyzer.getDryDay2(goodDays).getPrecipitation()+ "," + ",");
        printWriter.println("," + weatherAnalyzer.getMedianTemperature(goodDays) + "," +
                weatherAnalyzer.getMedianWindSpeed(goodDays) +
                "," + weatherAnalyzer.getMedianHumidity(goodDays) +
                "," + weatherAnalyzer.getMedianPrecipitation(goodDays) + "," + ",");
        printWriter.println(bestDay.getDay() + "," + bestDay.getTemperature() +
                "," + bestDay.getWindSpeed() + "," + bestDay.getHumidity() + ","
                + bestDay.getPrecipitation() + "," + bestDay.isLightning() + "," + bestDay.getClouds());
        printWriter.close();

    }

}
