package business;

import core.Album;
import core.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistManagerImpl implements core.ArtistManager {

    private ArrayList<Artist> artists = new ArrayList<>();

    @Override
    public ArrayList<Artist> getArtists() {
        return artists;
    }

    @Override
    public String getArtist(String nickname) {
        Artist artist =artists.stream().filter(artist1 -> artist1.getNickname() == nickname).
                findAny().orElse(null);
        return artist == null ? "Artist not found!": artist.toString();
    }

    @Override
    public void createArtist(Artist newArtist) {
        artists.add(newArtist);
    }

    @Override
    public void updateArtist(Artist artist) {
        deleteArtist(artist.getNickname());
        createArtist(artist);
    }

    @Override
    public void deleteArtist(String nickname) {
        artists = artists.stream().filter(artist -> artist.getNickname() != nickname).collect(Collectors.toCollection(ArrayList::new));
    }
}
