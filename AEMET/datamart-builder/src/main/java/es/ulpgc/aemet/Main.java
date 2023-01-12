package es.ulpgc.aemet;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    public static void main(String[] args) throws Exception {
        String path = "THE PATH TO YOUR DATA LAKE";
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    SqliteWeatherDatabase.updateDataBase(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 55);
        calendar.set(Calendar.SECOND, 0);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, calendar.getTime(), 1000 * 60 * 60 * 24);
    }

}


