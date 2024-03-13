package requestTo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

public class GetConnection {
    private static String responseBody;
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public static List<CurrencyRate> getApiDate(){
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(GetCurrentURL.getUrl()))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() == 200) {
            responseBody = response.body();
        } else {
            System.out.println("Error: " + response.statusCode());
        }

        Type listType = new TypeToken<List<CurrencyRate>>(){}.getType();
        return gson.fromJson(responseBody, listType);
    }
}
