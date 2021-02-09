package core;

import java.util.ArrayList;

public interface AlbumManager {

    /**
     * Shows the list of albums by ISRC and title.
     * @return
     */
    public ArrayList<Album> getAlbums();

    /**
     * Returns the album info.
     * @return
     */
    public String getAlbum(String isrc);

    /**
     * Adds a new album to the collection; no artist details
     */
    public void createAlbum(Album newAlbum);

    /**
     * Updates album info.
     */
    public void updateAlbum(Album album);

    /**
     * Deletes the album only, artist details will be ignored
     */
    public void deleteAlbum(String isrc);

}
