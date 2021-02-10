package client;
import core.Album;

import service.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import static org.glassfish.grizzly.http.server.Constants.POST;

public class ConsoleApp {
    private static boolean loop = true;
    public static void apiMenu() throws IOException {
        while(loop){
            System.out.println();
            System.out.println("API USER MENU");
            System.out.println("Artist Selections:");
            System.out.println("[1] list artist");
            System.out.println("[2] get artist details");
            System.out.println("[3] add artist");
            System.out.println("[4] update artist");
            System.out.println("[5] delete artist");
            System.out.println("Album Selections:");
            System.out.println("[6] list albums");
            System.out.println("[7] get album details");
            System.out.println("[8] add album");
            System.out.println("[9] update album info");
            System.out.println("[10] delete album");
            System.out.println("[q] to quit");
            Scanner usrInput = new Scanner(System.in);
            System.out.print("Please enter selection: ");
            String sel = usrInput.nextLine();
            System.out.print("\n");
            switch (sel) {
                case "1":
                    System.out.println("List artists selected.");
                    try {
                        URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists");
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        int responseCode = con.getResponseCode();
                        System.out.println("GET Response Code :: " + responseCode);
                        if (responseCode == HttpURLConnection.HTTP_OK) { // success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer response = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                response.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(response.toString());
                        } else {
                            System.out.println("GET request not worked");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    System.out.println("Get artist details selected.");
                    System.out.print("Enter the ISRC of the new album: ");
                    String getArtISRC = usrInput.nextLine();
                    try {
                        URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists"+getArtISRC);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        int responseCode = con.getResponseCode();
                        System.out.println("GET Response Code :: " + responseCode);
                        if (responseCode == HttpURLConnection.HTTP_OK) { // success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer response = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                response.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(response.toString());
                        } else {
                            System.out.println("GET request not worked");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("Add artist selected.");
                    System.out.print("Enter the Nickname of the new artist: ");
                    String artNick = usrInput.nextLine();
                    System.out.print("What is the first name of the artist you would like to add?: ");
                    String artFirst = usrInput.nextLine();
                    System.out.print("What is the last name of the artist you would like to add?: ");
                    String artLast = usrInput.nextLine();
                    System.out.print("Enter the bio of the new artist: ");
                    String artBio = usrInput.nextLine();

                    String POST_ART_PARAMS = null;
                    try {
                        URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists");
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        con.setRequestProperty("Accept-Language", "application/json");
                        con.setRequestProperty("Content-Type", "application/json");

                        POST_ART_PARAMS = "{\n" +
                                "    \"nickname\": \"" + artNick + "\",\n" +
                                "    \"first_name\": \"" + artFirst + "\",\n" +
                                "    \"last_name\": \"" + artLast + "\",\n" +
                                "    \"short_bio\": \"" + artBio + "\",\n" +
                                "}";

                        // For POST only - START
                        con.setDoOutput(true);
                        OutputStream os = con.getOutputStream();
                        os.write(POST_ART_PARAMS.getBytes(StandardCharsets.UTF_8));
                        os.flush();
                        os.close();
                        // For POST only - END

                        int responseCode = con.getResponseCode();
                        System.out.println("POST Response Code :: " + responseCode);

                        if (responseCode == HttpURLConnection.HTTP_OK) { //success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer sbp = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                sbp.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(sbp.toString());
                        } else {
                            System.out.println("POST request not worked");
                        }
                    } catch (IOException protocolException) {
                        protocolException.printStackTrace();
                    }

                    System.out.println("JSON output: ");
                    System.out.println(POST_ART_PARAMS + "\n");

                    break;
                case "4":
                    System.out.println("Update artist selected.");
                    System.out.print("Enter the Nickname of the new artist: ");
                    String artNickp = usrInput.nextLine();
                    System.out.print("What is the first name of the artist you would like to add?: ");
                    String artFirstp = usrInput.nextLine();
                    System.out.print("What is the last name of the artist you would like to add?: ");
                    String artLastp = usrInput.nextLine();
                    System.out.print("Enter the bio of the new artist: ");
                    String artBiop = usrInput.nextLine();

                    String PUT_ART_PARAMS = null;
                    try {
                        URL obj = new URL("http://localhost:9090/artist_api_war_exploded/artists"+artNickp);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        con.setRequestProperty("Accept-Language", "application/json");
                        con.setRequestProperty("Content-Type", "application/json");

                        PUT_ART_PARAMS = "{\n" +
                                "    \"nickname\": \"" + artNickp + "\",\n" +
                                "    \"first_name\": \"" + artFirstp + "\",\n" +
                                "    \"last_name\": \"" + artLastp + "\",\n" +
                                "    \"short_bio\": \"" + artBiop + "\",\n" +
                                "}";

                        // For POST only - START
                        con.setDoOutput(true);
                        OutputStream os = con.getOutputStream();
                        os.write(PUT_ART_PARAMS.getBytes(StandardCharsets.UTF_8));
                        os.flush();
                        os.close();
                        // For POST only - END

                        int responseCode = con.getResponseCode();
                        System.out.println("POST Response Code :: " + responseCode);

                        if (responseCode == HttpURLConnection.HTTP_OK) { //success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer sbp = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                sbp.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(sbp.toString());
                        } else {
                            System.out.println("POST request not worked");
                        }
                    } catch (IOException protocolException) {
                        protocolException.printStackTrace();
                    }

                    System.out.println("JSON output: ");
                    System.out.println(PUT_ART_PARAMS + "\n");
                    break;
                case "5":
                    System.out.println("Delete artist selected.");
                    break;
                case "6":
                    System.out.println("List albums selected.");
                    try {
                        URL obj = new URL("http://localhost:8080/myapp/albums/");
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        int responseCode = con.getResponseCode();
                        System.out.println("GET Response Code :: " + responseCode);
                        if (responseCode == HttpURLConnection.HTTP_OK) { // success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer response = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                response.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(response.toString());
                        } else {
                            System.out.println("GET request not worked");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "7":
                    System.out.println("Get album details selected.");
                    System.out.print("Enter the ISRC of the new album: ");
                    String getISRC = usrInput.nextLine();
                    try {
                        URL obj = new URL("http://localhost:8080/myapp/albums/"+getISRC);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        int responseCode = con.getResponseCode();
                        System.out.println("GET Response Code :: " + responseCode);
                        if (responseCode == HttpURLConnection.HTTP_OK) { // success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer response = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                response.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(response.toString());
                        } else {
                            System.out.println("GET request not worked");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "8":
                    System.out.println("Add album selected.");
                    System.out.print("Enter the ISRC of the new album: ");
                    String alISRC = usrInput.nextLine();
                    System.out.print("What is the title of the album you would like to add?: ");
                    String alTitle = usrInput.nextLine();
                    System.out.print("Enter the content description of the new album: ");
                    String alDesc = usrInput.nextLine();
                    boolean intLoop = true;
                    System.out.print("Enter the release year of the new album: ");
                    int alYear = usrInput.nextInt();
                    usrInput.nextLine(); //Takes in blank \n from in.nextInt()
                    System.out.print("Enter the nickname of the new album: ");
                    String alNick = usrInput.nextLine();

                    String POST_PARAMS = null;
                    try {
                        URL obj = new URL("http://localhost:8080/myapp/albums");
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        con.setRequestProperty("Accept-Language", "application/json");
                        con.setRequestProperty("Content-Type", "application/json");

                        POST_PARAMS = "{\n" +
                                "    \"ISRC_code\": \"" + alISRC + "\",\n" +
                                "    \"title\": \"" + alTitle + "\",\n" +
                                "    \"content_description\": \"" + alDesc + "\",\n" +
                                "    \"release_year\": \"" + alYear + "\",\n" +
                                "    \"artist_nickname\": \"" + alNick + "\"\n" +
                                "}";

                        // For POST only - START
                        con.setDoOutput(true);
                        OutputStream os = con.getOutputStream();
                        os.write(POST_PARAMS.getBytes(StandardCharsets.UTF_8));
                        os.flush();
                        os.close();
                        // For POST only - END

                        int responseCode = con.getResponseCode();
                        System.out.println("POST Response Code :: " + responseCode);

                        if (responseCode == HttpURLConnection.HTTP_OK) { //success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer sbp = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                sbp.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(sbp.toString());
                        } else {
                            System.out.println("POST request not worked");
                        }
                    } catch (IOException protocolException) {
                        protocolException.printStackTrace();
                    }

                    System.out.println("JSON output: ");
                    System.out.println(POST_PARAMS + "\n");

                    break;
                case "9":
                    System.out.println("Update album info selected.");
                    System.out.print("Enter ISRC of the album you would like to get: ");
                    String upISRC = usrInput.nextLine();
                    System.out.print("What is the title of the album you would like to add?: ");
                    String upTitle = usrInput.nextLine();
                    System.out.print("Enter the content description of the new album: ");
                    String upDesc = usrInput.nextLine();
                    System.out.print("Enter the release year of the new album: ");
                    int upYear = usrInput.nextInt();
                    usrInput.nextLine(); //Takes in blank \n from in.nextInt()
                    System.out.print("Enter the nickname of the new album: ");
                    String upNick = usrInput.nextLine();

                    String PUT_PARAMS = null;
                    try {
                        URL obj = new URL("http://localhost:8080/myapp/albums");
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("PUT");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        con.setRequestProperty("Accept-Language", "application/json");
                        con.setRequestProperty("Content-Type", "application/json");

                        PUT_PARAMS = "{\n" +
                                "    \"ISRC_code\": \"" + upISRC + "\",\n" +
                                "    \"title\": \"" + upTitle + "\",\n" +
                                "    \"content_description\": \"" + upDesc + "\",\n" +
                                "    \"release_year\": \"" + upYear + "\",\n" +
                                "    \"artist_nickname\": \"" + upNick + "\"\n" +
                                "}";

                        // For POST only - START
                        con.setDoOutput(true);
                        OutputStream os = con.getOutputStream();
                        os.write(PUT_PARAMS.getBytes(StandardCharsets.UTF_8));
                        os.flush();
                        os.close();
                        // For POST only - END

                        int responseCode = con.getResponseCode();
                        System.out.println("PUT Response Code :: " + responseCode);

                        if (responseCode == HttpURLConnection.HTTP_OK) { //success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer sbp = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                sbp.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(sbp.toString());
                        } else {
                            System.out.println("POST request not worked");
                        }
                    } catch (IOException protocolException) {
                        protocolException.printStackTrace();
                    }

                    System.out.println("JSON output: ");
                    System.out.println(PUT_PARAMS + "\n");
                    break;
                case "10":
                    System.out.println("Delete album selected.");
                    System.out.print("Enter the ISRC of the album you wish to delete.");
                    String delISRC = usrInput.nextLine();
                    System.out.println("Album " + delISRC + " deleted successfully.");
                    try {
                        URL obj = new URL("http://localhost:8080/myapp/albums/"+delISRC);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("DELETE");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0");
                        int responseCode = con.getResponseCode();
                        System.out.println("DELETE Response Code :: " + responseCode);
                        if (responseCode == HttpURLConnection.HTTP_OK) { // success
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
                            String inputLine;
                            StringBuffer sb = new StringBuffer();

                            while ((inputLine = br.readLine()) != null) {
                                sb.append(inputLine);
                            }
                            br.close();

                            // print result
                            System.out.println(sb.toString());
                        } else {
                            System.out.println("DELETE request did not work.");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "q":
                    System.out.println("Quit Selected. Good Bye!");
                    loop = false;
                    break;
                default:
                    System.out.println("Not a valid selection, please try again.");
                    break;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        apiMenu();
    }
}