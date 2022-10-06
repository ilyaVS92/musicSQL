package model;

public class Song {
    private int id;
    private int trackNumber;
    private String title;
    private int albumID;

    public Song(int id, int trackNumber, String title, int albumID) {
        this.id = id;
        this.trackNumber = trackNumber;
        this.title = title;
        this.albumID = albumID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }
}
