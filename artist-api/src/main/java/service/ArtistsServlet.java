package service;

import business.ArtistManagerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Artist;
import core.ArtistManagerSingleton;

import java.io.*;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "artistsServlet")
public class ArtistsServlet extends HttpServlet {
    private static ArtistManagerImpl manager;

    public void init() {
        ArtistManagerSingleton managerSingleton = ArtistManagerSingleton.INSTANCE;
        managerSingleton.setArtistManagerImplementation("business.ArtistManagerImpl");
        manager = managerSingleton.getArtistManagerImplementation();
    }

    /**
     * IF /artists    List all artists by nickname and name
     * IF /artists/*  Find artist by nickname where * is nickname
     * @return Collection of nicknames and artist names
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String res = null;
        String path = req.getPathInfo();

        try {
            if (path == null) {
                res = manager.getArtists().stream()
                        .sorted(Comparator.comparing(Artist::getNickname))
                        .map(artist -> artist.getNickname() + " - " + artist.getFirst_name() + " " + artist.getLast_name())
                        .collect(Collectors.joining("\n"));
            } else {
                int first = path.indexOf("/");
                int second = path.indexOf("/", first) | path.length();
                String nickname = path.substring(first + 1, second);
                res = manager.getArtist(nickname);
            }
        } catch(Exception e) {
            e.printStackTrace();
            res = "Artists could not be fetched";
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.append(res);
        out.close();
    }

    /**
     * Create artist
     * Receives in request body Artist object as JSON
     * @return Success or error message
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            // Get artist
            ObjectMapper mapper = new ObjectMapper();
            Artist artist = mapper.readValue(req.getReader(), Artist.class);
            String artistToString = manager.getArtist(artist.getNickname());

            if (artistToString.equals("Artist not found!")) {
                // Create artist
                Artist newArtist = new Artist(artist);
                manager.createArtist(newArtist);

                // Send response
                out.append("Artist created");
            } else {
                out.append("Artist already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.append("Artist could not be created");
        }

        out.close();
    }

    /**
     * Update artist
     * Receives in request body Artist object as JSON
     * @return Success or error message
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            // Get artist
            ObjectMapper mapper = new ObjectMapper();
            Artist artist = mapper.readValue(req.getReader(), Artist.class);
            String artistToString = manager.getArtist(artist.getNickname());

            if (artistToString.equals("Artist not found!")) {
                out.append(artistToString);
            } else {
                // Create artist
                Artist newArtist = new Artist(artist);
                manager.updateArtist(newArtist);

                // Send response
                out.append("Artist updated");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.append("Artist could not be updated");
        }

        out.close();
    }

    /**
     * Delete artist
     * /artists/* where * is nickname
     * @return Success or error message
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String res = null;

        try {
            String path = req.getPathInfo();
            int first = path.indexOf("/");
            int second = path.indexOf("/", first) | path.length();
            String nickname = path.substring(first + 1, second);

            if (nickname.isEmpty()) {
                res = "No artist has been specified to delete";
            } else {
                String artistToString = manager.getArtist(nickname);

                if (artistToString.equals("Artist not found!")) {
                    res = artistToString;
                } else {
                    manager.deleteArtist(nickname);
                    res = "Artist deleted";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "Artist could not be deleted";
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.append(res);
        out.close();
    }

    public void destroy() {}
}