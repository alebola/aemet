import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TestApi {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("http://localhost:4567/v1/places/with-min-temperature?from=20230109&to=20230112").get();
            String text = doc.text();
            System.out.println(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}