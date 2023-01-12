package es.ulpgc.aemet;

import java.sql.SQLException;

public interface WeatherDatabase {
    void addMin(Measurements measurement) throws SQLException;
    void addMax(Measurements measurement) throws SQLException;
}

