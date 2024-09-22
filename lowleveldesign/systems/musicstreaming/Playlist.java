package lowleveldesign.systems.musicstreaming;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String id;
    private String name;
    private User owner;
    private List<Song> songs;

    public Playlist(String id, String name, User user) {
        this.id = id;
        this.owner = user;
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addToPlayList(Song song) {
        songs.add(song);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
