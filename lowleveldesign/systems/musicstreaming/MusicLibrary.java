package lowleveldesign.systems.musicstreaming;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

// This is like the whole app music library, It will have songs, artiists and alsbums;
public class MusicLibrary {
    private static MusicLibrary instance;
    private Map<String, Song> songs;
    private Map<String, Artist> artists;
    private Map<String, Album> albums;

    public MusicLibrary() {
        songs = new ConcurrentHashMap<>();
        artists = new ConcurrentHashMap<>();
        albums = new ConcurrentHashMap<>();
    }

    public static synchronized MusicLibrary getInstance() {
        if(instance == null) {
            instance = new MusicLibrary();
        }

        return instance;
    }

    public void addAlbum(Album album) {
        albums.put(album.getId(), album);
        for (Song song : album.getSongs()) {
            addSong(song);
        }
    }

    public void addSong(Song song) {
        songs.put(song.getId(), song);
    }

    public void addArtist(Artist artist) {
        artists.put(artist.getId(), artist);
        for (Album album : artist.getAlbums()) {
            addAlbum(album);
        }
    }

    public Song getSong(String id) {
        return songs.get(id);
    }


    // now this is where we could implement the searchByArtisit, searchBy
    // I can jsut implement search by searchByArtiist for now;

    public List<Song> searchSongs(String query) {
        // query could be aritist name or album name or song name;
        return songs.values()
                .stream()
                .filter(song ->
                song.getTitle().toLowerCase().contains(query) || song.getAlbum().getName().toLowerCase().contains(query) || song.getArtist().getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

}
