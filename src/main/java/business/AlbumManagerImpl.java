package business;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AlbumManagerImpl implements core.AlbumManager{

    private ArrayList<Album> albums = new ArrayList<>();

    @Override
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    @Override
    public String getAlbum(String isrc) {
        Album album =albums.stream().filter(album1 -> album1.getISRC_code() == isrc).
                findAny().orElse(null);
        return album == null ? "Album not found!": album.toString();

    }

    @Override
    public void createAlbum(Album newAlbum) {
        albums.add(newAlbum);
    }

    @Override
    public void updateAlbum(Album album) {
        deleteAlbum(album.getISRC_code());
        createAlbum(album);

    }

    @Override
    public void deleteAlbum(String isrc) {
        albums = albums.stream().filter(album -> album.getISRC_code() != isrc).collect(Collectors.toCollection(ArrayList::new));
    }
}
