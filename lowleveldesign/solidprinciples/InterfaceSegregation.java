package lowleveldesign.solidprinciples;
/*
The Interface Segregation Principle (ISP) states that no client should be forced to depend on methods it does not use.
In other words, it’s better to have multiple small, specific interfaces rather than a large, general-purpose interface.
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
