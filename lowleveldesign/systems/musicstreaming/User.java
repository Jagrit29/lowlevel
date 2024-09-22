package lowleveldesign.systems.musicstreaming;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String password;
    private List<Playlist> playlists;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.playlists = new ArrayList<>();
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}
