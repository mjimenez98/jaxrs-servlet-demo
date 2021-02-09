package client;

import core.Album;

import service.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter selection: ");
            String sel = in.nextLine();
            System.out.print("\n");
            switch (sel) {
                case "1":
                    System.out.println("List artists selected.");
                    break;
                case "2":
                    System.out.println("Get artist details selected.");
                    break;
                case "3":
                    System.out.println("Add artist selected.");
                    break;
                case "4":
                    System.out.println("Update artist selected.");
                    break;
                case "5":
                    System.out.println("Delete artist selected.");
                    break;
                case "6":
                    System.out.println("List albums selected.");
                    break;
                case "7":
                    System.out.println("Get album details selected.");
                    System.out.print("Enter ISRC of the album you would like to get: ");
                    String getAl = in.nextLine();
                    break;
                case "8":
                    System.out.println("Add album selected.");
                    System.out.print("Enter the ISRC of the new album: ");
                    String alISRC = in.nextLine();
                    System.out.print("What is the title of the album you would like to add?: ");
                    String alTitle = in.nextLine();
                    System.out.print("Enter the content description of the new album: ");
                    String alDesc = in.nextLine();
                    System.out.print("Enter the release year of the new album: ");
                    int alYear = in.nextInt();
                    in.nextLine(); //Takes in blank \n from in.nextInt()
                    System.out.print("Enter the nickname of the new album: ");
                    String alNick = in.nextLine();

//                    Formatted:
//                    String jsonStr = "{\n\t\"ISRC_code\": \"" + alISRC + "\","
//                            + "\n\t\"title\": \"" + alTitle + "\","
//                            + "\n\t\"content_description\": \"" + alDesc + "\","
//                            + "\n\t\"year\": \"" + alYear + "\","
//                            + "\n\t\"album_nickname\": \"" + alNick + "\" \n}";

                    String jsonStr = "{\"ISRC_code\": \"" + alISRC + "\", "
                            + "\"title\": \"" + alTitle + "\", "
                            + "\"content_description\": \"" + alDesc + "\", "
                            + "\"year\": \"" + alYear + "\", "
                            + "\"album_nickname\": \"" + alNick + "\"}";

                    System.out.println("JSON output: ");
                    System.out.println(jsonStr +"\n");

                    URL urlPost = new URL ("http://localhost:8080/myapp/albums");
                    HttpURLConnection con = (HttpURLConnection)urlPost.openConnection();
                    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    con.setRequestProperty("Accept", "application/json");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestMethod("POST");

                    OutputStream os = con.getOutputStream();
                    os.write(jsonStr.getBytes(StandardCharsets.UTF_8));
                    os.close();


                    System.out.println("Album " + alTitle + " added successfully.");
                    break;
                case "9":
                    System.out.println("Update album info selected.");
                    System.out.print("Which is the ISRC of the album you would like to update?");
                    String upAlISRC = in.nextLine();
                    Album updAl = new Album();
                    System.out.println("Album " + upAlISRC + " updated successfully.");
                    //FIX THIS
                    break;
                case "10":
                    System.out.println("Delete album selected.");
                    System.out.print("Enter the ISRC of the album you wish to delete.");
                    String delAl = in.nextLine();
                    System.out.println("Album " + delAl + " deleted successfully.");
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