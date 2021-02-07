package core;

import java.util.ArrayList;
import java.util.List;

public interface ArtistManager {

    /**
     * Lists artists.
     */
    public ArrayList<Artist> getArtists();

    /**
     * Gets the artist's details.
     */
    public String getArtist(String nickname);

    /**
     * Adds the artist.
     */
    public void createArtist(Artist newArtist);

    /**
     * Updates the artist.
     */
    public void updateArtist(Artist artist);

    /**
     * Deletes the artist's record.
     * If there are albums under the artist's name, the album records remain unchanged.
     */
    public void deleteArtist(String nickname);

}
