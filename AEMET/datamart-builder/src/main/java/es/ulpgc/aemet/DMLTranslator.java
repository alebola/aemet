package es.ulpgc.aemet;

public class DMLTranslator {
    private static final String INSERT_MIN_MEASUREMENT =
            "INSERT OR IGNORE INTO tamin (DATE, TIME, STATION, PLACE, VALUE) VALUES ('%d', '%s', '%s', '%s', '%f')";

    private static final String INSERT_MAX_MEASUREMENT =
            "INSERT OR IGNORE INTO tamax (DATE, TIME, STATION, PLACE, VALUE) VALUES ('%d', '%s', '%s', '%s', '%f')";
    public static String insertMin(Measurements measurement){
        return String.format(INSERT_MIN_MEASUREMENT,
                measurement.date,
                measurement.time,
                measurement.station,
                measurement.place,
                measurement.temperature);
    }
    public static String insertMax(Measurements measurement){
        return String.format(INSERT_MAX_MEASUREMENT,
                measurement.date,
                measurement.time,
                measurement.station,
                measurement.place,
                measurement.temperature);
    }

}

