package es.ulpgc.aemet;

import com.google.gson.JsonArray;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String apiKey = "YOUR API KEY";
        String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
        String path = "THE PATH WHERE YOU WANT TO CREATE YOUR DATA LAKE";

        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String date = f.format(new Date());
        String file_extension = ".events";

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                AemetDownloader aemetDownloader = new AemetDownloader();
                JsonArray jsonData = null;
                try {
                    jsonData = aemetDownloader.response(url, apiKey);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                FileWriter fileWriter = new FileWriter();
                try {fileWriter.writeData(jsonData,path,date,file_extension);}
                catch (FileNotFoundException e) {throw new RuntimeException(e);}
                catch (UnsupportedEncodingException e) {throw new RuntimeException(e);}
            }
        };

        timer.schedule(timerTask, 0, 60*60*1000);

    }


}
