package Structural_Design_Pattern.Fascade_Design_Pattern;

class VideoReader{
    public void read(String filename){
        System.out.println("Reading video file "+filename);
    }
}

class Codec{
    public String getCodec(String filename){
        System.out.println("Getting the codec for file: "+filename);
        return filename+"_codec";
    }
}

class AudioExtracter{
    public String extract(String fileCodec){
        System.out.println("Extracting audio from code file"+fileCodec);
        return fileCodec+".audo";
    }
}

class CompressionCodec{
    public void compress(String fileCodec){
        System.out.println("Compressing the codec file: "+fileCodec);
    }
}

class Bitrate{
    public void bitrate(String filename){
        System.out.println("Bitrating the file: "+filename);
    }
}

class AudoSync{
    public void sync(String audioCodec){
        System.out.println("Syncing audo for: "+audioCodec);
    }
}

class Muxing{
    public void muxing(){
        System.out.println("Muxing the file:");
    }
}

class VideoConversionFascade{
    private VideoReader videoReader=new VideoReader();
    private Codec codec=new Codec();
    private AudioExtracter audioExtracter = new AudioExtracter();
    private CompressionCodec compressionCodec = new CompressionCodec();
    private Bitrate bitrate=new Bitrate();
    private AudoSync audoSync=new AudoSync();
    private Muxing muxing=new Muxing();

    public void convert(String fromFilename, String toFilename){
        System.out.println("----- STARTING VIDEO CONVERSION PROCESS ----------");
        videoReader.read(fromFilename);
        String fileCodec=codec.getCodec(fromFilename);
        String audioExtracterd=audioExtracter.extract(fileCodec);
        compressionCodec.compress(audioExtracterd);
        bitrate.bitrate(audioExtracterd);
        audoSync.sync(audioExtracterd);
        muxing.muxing();
    }
}

public class Video_Conversion_Library {
    public static void main(String[] args) {
        VideoConversionFascade videoConversionFascade=new VideoConversionFascade();
        videoConversionFascade.convert("video.mp4","ogg");
    }
}
