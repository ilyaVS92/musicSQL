package model;

//return which artist recorded a particular song - included in result: artist name, album name, song track

public class SongArtist {
    private String albumName;
    private String artistName;
    private int trackNo;
    private String songName;

    public SongArtist(String artistName, String albumName,int trackNo) {
        this.artistName = artistName;
        this.albumName = albumName;
        this.trackNo = trackNo;
    }
    public SongArtist(String artistName, String albumName,  int trackNo, String songName){
        this(artistName, albumName, trackNo);
        this.songName = songName;
    }

    public SongArtist() {

    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(int trackNo) {
        this.trackNo = trackNo;
    }
}
