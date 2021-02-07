package core;

public class Album{

    private String ISRC_code; //unique
    private String title;
    private String content_description; //optional
    private int release_year;
    private String artist_nickname; //It may be possible that an artist record of an album is not stored in the artists repository.

    public Album(){}

    public Album(String ISRC_code, String title, String content_description, int release_year, String artist_nickname) {
        this.ISRC_code = ISRC_code;
        this.title = title;
        this.content_description = content_description;
        this.release_year = release_year;
        this.artist_nickname = artist_nickname;
    }

    public String getISRC_code() {
        return ISRC_code;
    }

    public void setISRC_code(String ISRC_code) {
        this.ISRC_code = ISRC_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_description() {
        return content_description;
    }

    public void setContent_description(String content_description) {
        this.content_description = content_description;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String getArtist_nickname() {
        return artist_nickname;
    }

    public void setArtist_nickname(String artist_nickname) {
        this.artist_nickname = artist_nickname;
    }

    public String toString(){
        return String.format("Album International Standard Recording Code (ISRC) code: %s \n" +
                        "Title: %s\n Content Description: %s\n, Release Year: %s\n, Artist: %s\n", this.getISRC_code(),
                this.getTitle(), this.getContent_description(), this.getRelease_year(), this.getArtist_nickname());
    }
}
