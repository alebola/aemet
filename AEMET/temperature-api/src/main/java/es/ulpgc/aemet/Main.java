package es.ulpgc.aemet;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.List;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) throws SQLException {

        get("/v1/places/with-max-temperature", (req, res) -> {
            String fromDate = req.queryParams("from");
            String toDate = req.queryParams("to");
            SQLite sqlite = new SQLite();
            List<Measurements> measurementsList = sqlite.getData(fromDate, toDate, "tamax");
            return new Gson().toJson(measurementsList);
        });

        get("/v1/places/with-min-temperature", (req, res) -> {
            String fromDate = req.queryParams("from");
            String toDate = req.queryParams("to");
            SQLite sqlite = new SQLite();
            List<Measurements> measurementsList = sqlite.getData(fromDate, toDate, "tamin");
            return new Gson().toJson(measurementsList);
        });
    }
}
