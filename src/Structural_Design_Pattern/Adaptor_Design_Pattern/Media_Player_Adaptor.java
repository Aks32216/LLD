package Structural_Design_Pattern.Adaptor_Design_Pattern;

interface MediaPlayer{
    void play(String filename);
}

class Mp3Player implements MediaPlayer{
    @Override
    public void play(String filename) {
        System.out.println("Playing via Mp3 Player : "+filename);
    }
}

class VlcCodecAdaptor implements MediaPlayer{
    private final VlcCodec vlcCodec;

    VlcCodecAdaptor(VlcCodec vlcCodec){
        this.vlcCodec=vlcCodec;
    }

    @Override
    public void play(String filename) {
        vlcCodec.playVlc(filename);
    }
}

class VlcCodec{
    public void playVlc(String filename){
        System.out.println("Playing via VLC media Player : "+filename);
    }
}

class Mp4CodecAdaptor implements MediaPlayer{
    private final Mp4Codec mp4Codec;

    Mp4CodecAdaptor(Mp4Codec mp4Codec){
        this.mp4Codec=mp4Codec;
    }

    @Override
    public void play(String filename) {
        mp4Codec.playMp4(filename);
    }
}

class Mp4Codec{
    public void playMp4(String filename){
        System.out.println("Playing via MP4 media Player : "+filename);
    }
}

class AudioPlayer {
    public void playFile(String filename) {
        MediaPlayer player;
        String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        switch (extension) {
            case "mp3":
                player = new Mp3Player();
                break;
            case "vlc":
                player = new VlcCodecAdaptor(new VlcCodec());
                break;
            case "mp4":
                player = new Mp4CodecAdaptor(new Mp4Codec());
                break;
            default:
                System.out.println("Unsupported format: " + extension);
                return;
        }

        player.play(filename);
    }
}

public class Media_Player_Adaptor {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.playFile("song.mp3");
        player.playFile("movie.mp4");
        player.playFile("documentary.vlc");
        player.playFile("image.png");
    }
}
