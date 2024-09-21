package lowleveldesign.principles.solidprinciples;
/*
I - Interface Segregation Principle (ISP):
Definition: Clients should not be forced to depend on interfaces they do not use.
Explanation: Larger interfaces should be split into smaller, more specific ones so that classes implement only the methods they need.
Example: Instead of having one large Animal interface with methods like walk(), fly(), and swim(), you could have separate interfaces like Walkable, Flyable, and Swimmable, so animals only implement the methods relevant to them.
 */

// Wrong way
interface MediaPlayer {
    void playAudio(String audiofile);
    void playVideo(String videofile);
}

// Now in the above scenairo if there is a Audioplayer, it won't have video;

// Correct Way
interface AudioPlayer {
    void playAudio(String audiofile);
}

interface VideoPlayer {
    void playVideo(String videofile);
}

class SimpleAudioPlayer implements AudioPlayer {
    @Override
    public void playAudio(String audioFile) {
        System.out.println("Playing audio: " + audioFile);
    }
}

class SimpleVideoPlayer implements VideoPlayer {
    @Override
    public void playVideo(String videoFile) {
        System.out.println("Playing video: " + videoFile);
    }
}

// this is
public class InterfaceSegregation {
}
