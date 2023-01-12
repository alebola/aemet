package es.ulpgc.aemet;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SqliteWeatherDatabase implements WeatherDatabase {
    private final Connection connection;

    public SqliteWeatherDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:datamart.db");
        initDatabase();
    }
    private static final String TAMIN =
            "CREATE TABLE IF NOT EXISTS tamin (" +
                    "DATE NUMBER UNIQUE, " +
                    "TIME TEXT, " +
                    "STATION TEXT, " +
                    "PLACE TEXT, " +
                    "VALUE NUMBER);";

    private static final String TAMAX =
            "CREATE TABLE IF NOT EXISTS tamax (" +
                    "DATE NUMBER UNIQUE, " +
                    "TIME TEXT, " +
                    "STATION TEXT, " +
                    "PLACE TEXT, " +
                    "VALUE NUMBER)";

    private void initDatabase() throws SQLException{
        connection.createStatement().execute(TAMIN);
        connection.createStatement().execute(TAMAX);
    }

    @Override
    public void addMax(Measurements measurement) throws SQLException {
        try{
            connection.createStatement().execute(DMLTranslator.insertMax(measurement));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void addMin(Measurements measurement) throws SQLException {
        try{
            connection.createStatement().execute(DMLTranslator.insertMin(measurement));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateDataBase(String path) throws Exception {

        File[] files = new File(path).listFiles();

        for (File file : files) {
            List<Measurements> minMeasurementsList = new ArrayList<>();
            List<Measurements> maxMeasurementsList = new ArrayList<>();
            if (file.isFile()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    minMeasurementsList.add(Measurements.minMeasurementsOf(line));
                    maxMeasurementsList.add(Measurements.maxMeasurementsOf(line));
                }
                scanner.close();
                Measurements minTemp = minMeasurementsList.stream()
                        .filter(x -> x != null)
                        .min(Comparator.comparing(Measurements::getTemperature)).orElse(null);

                Measurements maxTemp = maxMeasurementsList.stream()
                        .filter(x -> x != null)
                        .max(Comparator.comparing(Measurements::getTemperature)).orElse(null);

                WeatherDatabase weatherDatabase = new SqliteWeatherDatabase();
                weatherDatabase.addMin(minTemp);
                weatherDatabase.addMax(maxTemp);
            }
        }
    }

}


