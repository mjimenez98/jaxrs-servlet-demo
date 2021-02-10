package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Artist {
    private static void printResponse(HttpURLConnection con) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        System.out.println(response.toString());
    }

    public static void getArtists() {
        try {
            URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                printResponse(con);
            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getArtist(String nickname) {
        try {
            URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists/"+nickname);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                printResponse(con);
            } else {
                System.out.println("GET request not worked");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addArtist(String nickname, String firstName, String lastName, String bio) {
        String POST_ART_PARAMS = null;

        try {
            URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "application/json");
            con.setRequestProperty("Content-Type", "application/json");

            POST_ART_PARAMS = "{\n" +
                    "    \"nickname\": \"" + nickname + "\",\n" +
                    "    \"first_name\": \"" + firstName + "\",\n" +
                    "    \"last_name\": \"" + lastName + "\",\n" +
                    "    \"short_bio\": \"" + bio + "\"\n" +
                    "}";

            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_ART_PARAMS.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                printResponse(con);
            } else {
                System.out.println("POST request not worked");
            }
        } catch (IOException protocolException) {
            protocolException.printStackTrace();
        }

        System.out.println("JSON output: ");
        System.out.println(POST_ART_PARAMS + "\n");
    }

    public static void updateArtist(String nickname, String firstName, String lastName, String bio) {
        String PUT_ART_PARAMS = null;

        try {
            URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists/"+nickname);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("PUT");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "application/json");
            con.setRequestProperty("Content-Type", "application/json");

            PUT_ART_PARAMS = "{\n" +
                    "    \"nickname\": \"" + nickname + "\",\n" +
                    "    \"first_name\": \"" + firstName + "\",\n" +
                    "    \"last_name\": \"" + lastName + "\",\n" +
                    "    \"short_bio\": \"" + bio + "\"\n" +
                    "}";

            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(PUT_ART_PARAMS.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                printResponse(con);
            } else {
                System.out.println("PUT request did not work.");
            }
        } catch (IOException protocolException) {
            protocolException.printStackTrace();
        }

        System.out.println("JSON output: ");
        System.out.println(PUT_ART_PARAMS + "\n");
    }

    public static void deleteArtist(String nickname) {
        try {
            URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists/"+nickname);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("DELETE");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                printResponse(con);
            } else {
                System.out.println("DELETE request not worked");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
