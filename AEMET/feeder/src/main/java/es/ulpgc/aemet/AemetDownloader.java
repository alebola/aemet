package es.ulpgc.aemet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class AemetDownloader {

    public JsonArray response(String url, String apiKey) throws IOException {
        String response = Jsoup.connect(url)
                .validateTLSCertificates(false)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();

        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String datos = jsonResponse.get("datos").getAsString();

        String response2 = Jsoup.connect(datos)
                .validateTLSCertificates(false)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();

        JsonArray jsonData = new Gson().fromJson(response2, JsonArray.class);
        return jsonData;
    }
}
