package lowleveldesign.systems.musicstreaming;

public class Song {
    private String id;
    private String title;
    private Artist artist;
    private Album album;

    public Song(String id, String title, Artist artist, Album album) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album getAlbum() {
        return album;
    }
}
