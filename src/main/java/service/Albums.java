package service;

import business.AlbumManagerImpl;
import core.Album;
import core.AlbumManagerSingleton;
import core.ArtistManagerSingleton;

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

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listAlbums() {
        try {
            if (!isManagerCreated)
                initialize();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return manager.getAlbums().stream()
                .sorted(Comparator.comparing(Album::getISRC_code))
                .map(album -> album.getISRC_code() + " - " + album.getTitle())
                .collect(Collectors.joining("\n"));
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public String addAlbum(Album album) {
        try {
            if (!isManagerCreated)
                initialize();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Album newAlbum = new Album(album);
        manager.createAlbum(newAlbum);

        return "Album created";
    }

    @PUT
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateAlbum(Album album) {
        try {
            if (!isManagerCreated)
                initialize();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Album newAlbum = new Album(album);
        manager.updateAlbum(newAlbum);

        return "Album updated";
    }

    @GET
    @Path("{isrc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAlbum(@PathParam("isrc") String isrc) {
        try {
            if (!isManagerCreated)
                initialize();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return manager.getAlbum(isrc);
    }

    @DELETE
    @Path("{isrc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAlbum(@PathParam("isrc") String isrc) {
        try {
            if (!isManagerCreated)
                initialize();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        manager.deleteAlbum(isrc);

        return "Album deleted";
    }
}
