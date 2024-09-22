package lowleveldesign.systems.musicstreaming;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String id;
    private String name;
    private List<Album> albums;

    public Artist(String id, String name) {
        this.id = id;
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public void addAlbums(Album album) {
        albums.add(album);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

}
