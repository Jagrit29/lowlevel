package lowleveldesign.systems.musicstreaming;

public class MusicService {
    private final MusicLibrary musicLibrary;
    private final UserManager userManager;
    private final MusicRecommender musicRecommender;

    public MusicService() {
        musicLibrary = MusicLibrary.getInstance();
        userManager = UserManager.getInstance();
        musicRecommender = MusicRecommender.getInstance();
    }

    public void start() {

    }

    public MusicLibrary getMusicLibrary() {
        return musicLibrary;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public MusicRecommender getMusicRecommender() {
        return musicRecommender;
    }
}
