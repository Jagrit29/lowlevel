package lowleveldesign.systems.musicstreaming;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String id;
    private String name;
    private List<Song> songs;
    private Artist artist;

    public Album(String id, String name, Artist artist) {
        this.id = id;
        this.name = name;
        this.songs = new ArrayList<>();
        this.artist = artist;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Artist getArtist() {
        return artist;
    }
}
