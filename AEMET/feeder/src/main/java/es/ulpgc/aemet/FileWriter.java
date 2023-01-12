package es.ulpgc.aemet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileWriter {

    public void writeData(JsonArray jsonData, String path, String date, String file_extension) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(path + date + file_extension, "UTF-8");
        for (JsonElement item : jsonData){
            double lat = item.getAsJsonObject().get("lat").getAsDouble();
            double lon = item.getAsJsonObject().get("lon").getAsDouble();
            String fint = item.getAsJsonObject().get("fint").getAsString();
            String file_date = fint.substring(0, 10).replace("-", "");

            if ((lon > -16 && lon < -15) && (lat > 27.5 && lat < 28.4) && (file_date.equals(date))){
                Filter filtered = new Filter();
                filtered.filter(item);
                writer.println(item);
            }
        }
        writer.close();
    }
}
