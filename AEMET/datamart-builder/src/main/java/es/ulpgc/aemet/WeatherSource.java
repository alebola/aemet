package es.ulpgc.aemet;

import java.util.List;

public interface WeatherSource {
    List<Measurements> measurementsOf(String id) throws Exception;
}

