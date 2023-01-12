package es.ulpgc.aemet;

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SQLite {
    static final String DB_URL = "jdbc:sqlite:datamart.db";


    public static List<Measurements> getData(String fromDate, String toDate, String table) throws SQLException {

        String query = "";
        int fromDateInt = Integer.parseInt(fromDate);
        int toDateInt = Integer.parseInt(toDate);

        if (table.equals("tamin")) {
            query = "SELECT * FROM tamin WHERE DATE >= " + fromDateInt + " AND DATE <= " + toDateInt + " ORDER BY VALUE ASC";
        } else if (table.equals("tamax")) {
            query = "SELECT * FROM tamax WHERE DATE >= " + fromDateInt + " AND DATE <= " + toDateInt + " ORDER BY VALUE DESC";
        }

        List<Measurements> measurementsList = new ArrayList<>();
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                //Display values of "VALUES"
                int date = rs.getInt("DATE");
                String time = rs.getString("TIME");
                String station = rs.getString("STATION");
                String place = rs.getString("PLACE");
                String value = rs.getString("VALUE");
                NumberFormat format = NumberFormat.getInstance(Locale.US);
                format.setMaximumFractionDigits(1);
                double formated_value = Double.parseDouble(format.format(Double.parseDouble(value.replace(",", "."))));
                Measurements measurements = new Measurements(date, station, place, formated_value, time);
                measurementsList.add(measurements);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return measurementsList;
    }
}
