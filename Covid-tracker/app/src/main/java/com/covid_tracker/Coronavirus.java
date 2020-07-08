package com.covid_tracker;


import com.google.gson.*;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Coronavirus {
    private static final String BASE_URL = "https://coronavirus-tracker-api.herokuapp.com/v2/";
    private static final Gson gson = new Gson();

    public Latest getLatest() throws IOException {
        return gson.fromJson(getCoronaJson("latest"), Latest.class);
    }

    public ArrayList<Location> getLocations() throws IOException {
        return getLocationsList("locations");
    }

    private static ArrayList<Location> getLocationsList(String endpoint) throws IOException {
        JsonArray jsonArray = getCoronaJson(endpoint).getAsJsonArray("locations");
        ArrayList<Location> locations = new ArrayList<>();
        jsonArray.forEach(jsonElement -> locations.add(gson.fromJson(jsonElement, Location.class)));
        return locations;
    }

    private static JsonObject getCoronaJson(String endpoint) throws IOException {
        String url = BASE_URL + endpoint;
        return readJsonUrl(url).getAsJsonObject();
    }

    private static JsonElement readJsonUrl(String url) throws IOException {
        return new JsonParser().parse(getPage(url));
    }

    private static String getPage(String url) throws IOException {
        try{
            URL url1 = new URL(url);
            URLConnection connection = url1.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
            connection.connect();
            BufferedReader serverResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = serverResponse.readLine();
            serverResponse.close();
            return response;
        }
        catch (IOException ex)
        {
            return null;
        }
    }
}
