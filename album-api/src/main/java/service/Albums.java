package service;

import business.AlbumManagerImpl;
import core.Album;
import core.AlbumManagerSingleton;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Root resource (exposed at "albums" path)
 */
@Path("albums")
public class Albums {
    private static AlbumManagerImpl manager;
    private static boolean isManagerCreated = false;

    private void initialize() throws Exception {
        if (isManagerCreated)
            throw new Exception();
        isManagerCreated = true;

        AlbumManagerSingleton managerSingleton = AlbumManagerSingleton.INSTANCE;
        managerSingleton.setAlbumManagerImplementation("business.AlbumManagerImpl");
        manager = managerSingleton.getAlbumManagerImplementation();
    }

    /**
     * List all albums by ISRC and title
     * @return Collection of ISRCs and titles
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listAlbums() {
        try {
            if (!isManagerCreated)
                initialize();

            return manager.getAlbums().stream()
                    .sorted(Comparator.comparing(Album::getISRC_code))
                    .map(album -> album.getISRC_code() + " - " + album.getTitle())
                    .collect(Collectors.joining("\n"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Could not fetch albums";
        }
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public String addAlbum(Album album) {
        try {
            if (!isManagerCreated)
                initialize();

            if (album.getISRC_code() == null || album.getTitle() == null || album.getRelease_year() == 0 ||
                    album.getArtist_nickname() == null)
                return "Request could not be processed: Parameter missing";

            String albumToString = manager.getAlbum(album.getISRC_code());

            if (albumToString.equals("Album not found!")) {
                Album newAlbum = new Album(album);
                manager.createAlbum(newAlbum);

                return "Album created";
            } else {
                return "Album already exists";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Could not create album";
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateAlbum(Album album) {
        try {
            if (!isManagerCreated)
                initialize();

            if (album.getISRC_code() == null || album.getTitle() == null || album.getRelease_year() == 0 ||
                    album.getArtist_nickname() == null)
                return "Request could not be processed: Parameter missing";

            String albumToString = manager.getAlbum(album.getISRC_code());

            if (albumToString.equals("Album not found!")) {
                return albumToString;
            } else {
                Album newAlbum = new Album(album);
                manager.updateAlbum(newAlbum);

                return "Album updated";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Album could not be created";
        }
    }

    @GET
    @Path("{isrc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAlbum(@PathParam("isrc") String isrc) {
        try {
            if (!isManagerCreated)
                initialize();

            return manager.getAlbum(isrc);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Could not fetch album";
        }
    }

    @DELETE
    @Path("{isrc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAlbum(@PathParam("isrc") String isrc) {
        try {
            if (!isManagerCreated)
                initialize();

            String albumToString = manager.getAlbum(isrc);

            if (albumToString.equals("Album not found!")) {
                return albumToString;
            } else {
                manager.deleteAlbum(isrc);
                return "Album deleted";
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Album could not be deleted";
        }
    }
}
