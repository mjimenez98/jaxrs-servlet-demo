package service;

import business.AlbumManagerImpl;
import core.Album;
import core.AlbumManagerSingleton;
import core.ArtistManagerSingleton;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
}
