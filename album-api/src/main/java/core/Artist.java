package core;

public class Artist{

    private String nickname; //a unique string identifying the artist
    private String first_name;
    private String last_name;
    private String short_bio; //optional

    public Artist(){}

    public Artist(String nickname, String first_name, String last_name, String short_bio) {
        this.nickname = nickname;
        this.first_name = first_name;
        this.last_name = last_name;
        this.short_bio = short_bio;
    }

    public Artist(Artist artist) {
        this.nickname = artist.nickname;
        this.first_name = artist.nickname;
        this.last_name = artist.last_name;
        this.short_bio = artist.short_bio;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getShort_bio() {
        return short_bio;
    }

    public void setShort_bio(String short_bio) {
        this.short_bio = short_bio;
    }

    public String toString(){
        return String.format("Artist Nickname: %s\n, First Name: %s\n, Last Name: %s\n, Short Bio: %\n",
                this.getNickname(), this.getFirst_name(), this.getLast_name(), this.getShort_bio());
    }
}
