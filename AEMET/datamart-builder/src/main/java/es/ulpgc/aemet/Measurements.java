package es.ulpgc.aemet;

public class Measurements {
    public int date;
    public String station;
    public String place;
    public double temperature;
    public String time;

    public Measurements(int date, String station, String place, double temperature, String time) {
        this.date = date;
        this.station = station;
        this.place = place;
        this.temperature = temperature;
        this.time = time;
    }
    public int getTimestamp() {
        return date;
    }
    public String getStation() {
        return station;
    }
    public String getPlace() {
        return place;
    }
    public double getTemperature() {
        return temperature;
    }
    public String getTime() {
        return time;
    }
    public static Measurements minMeasurementsOf(String line) throws Exception {
        String[] values = line.split(",");
        String ubi = values[2].substring(7).replace("\"", "");;
        String idema = values[0].substring(10,15);
        int date = Integer.parseInt(values[1].substring(8, 18).replace("-", ""));
        String time = values[1].substring(19,27);
        if (values.length < 5)  {
            return null;
        } else {
            double tamin = Double.parseDouble(values[3].substring(8));
            Measurements measurements = new Measurements(date, idema, ubi, tamin, time);
            return measurements;
        }
    }
    public static Measurements maxMeasurementsOf(String line) throws Exception {
        String[] values = line.split(",");
        String ubi = values[2].substring(7).replace("\"", "");
        String idema = values[0].substring(10,15);
        int date = Integer.parseInt(values[1].substring(8, 18).replace("-", ""));
        String time = values[1].substring(19,27);
        if (values.length < 5)  {
            return null;
        } else {
            double tamax = Double.parseDouble(values[5].substring(8).replace("}", ""));
            Measurements measurements = new Measurements(date, idema, ubi, tamax, time);
            return measurements;
        }
    }
}

