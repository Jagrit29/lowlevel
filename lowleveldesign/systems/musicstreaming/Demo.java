package lowleveldesign.systems.musicstreaming;

public class Demo {
    public static void main(String args[]) {
        MusicService spotify = new MusicService();
        User user1 = new User("1", "John", "password123");
        User user2 = new User("2", "Jane", "password456");

        Artist diljit = new Artist("id1", "Diljit");
        Artist sartaj = new Artist("id1", "Sartaj");

        Album album1 = new Album("a1", "DiljitAlbum", diljit);
        Album album2 = new Album("a3", "SartajAlbum", sartaj);

        Song song1 = new Song("s1", "DiljitSong", diljit, album1);
        Song song2 = new Song("s3", "SartajSong", sartaj, album2);

        album1.addSong(song1);
        album2.addSong(song2);

        diljit.addAlbums(album1);
        sartaj.addAlbums(album2);





    }
}
