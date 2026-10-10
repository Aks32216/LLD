package Structural_Design_Pattern.Fascade_Design_Pattern;

// --- Subsystems ---

class Amplifier {
    public void on() { System.out.println("Amplifier: Powering on."); }
    public void off() { System.out.println("Amplifier: Shutting down."); }
    public void setVolume(int level) { System.out.println("Amplifier: Volume set to " + level + "."); }
}

class DvdPlayer {
    public void on() { System.out.println("DVD Player: Powering on."); }
    public void off() { System.out.println("DVD Player: Shutting down."); }
    public void play(String movie) { System.out.println("DVD Player: Playing '" + movie + "'."); }
    public void stop() { System.out.println("DVD Player: Stopped."); }
}

class Projector {
    public void on() { System.out.println("Projector: Warming up."); }
    public void off() { System.out.println("Projector: Cooling down."); }
    public void wideScreenMode() { System.out.println("Projector: Widescreen mode enabled."); }
}

class SmartLights {
    public void dim(int level) { System.out.println("Lights: Dimmed to " + level + "%."); }
    public void on() { System.out.println("Lights: Full brightness."); }
}

class StreamingService {
    public void connect() { System.out.println("Streaming: Connected to service."); }
    public void disconnect() { System.out.println("Streaming: Disconnected."); }
    public void stream(String movie) { System.out.println("Streaming: Now streaming '" + movie + "'."); }
}

class HomeThreaterFascade{
    private Amplifier amplifier=new Amplifier();
    private DvdPlayer dvdPlayer= new DvdPlayer();
    private Projector projector=new Projector();
    private SmartLights smartLights = new SmartLights();
    private StreamingService streamingService=new StreamingService();

    public HomeThreaterFascade(Amplifier amp, DvdPlayer dvd, Projector projector,
                             SmartLights lights, StreamingService streaming) {
        this.amplifier = amp;
        this.dvdPlayer = dvd;
        this.projector = projector;
        this.smartLights = lights;
        this.streamingService = streaming;
    }

    public void watchMovie(String movie) {
        System.out.println("\n--- Preparing to watch: " + movie + " ---");
        smartLights.dim(15);
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setVolume(20);
        streamingService.connect();
        streamingService.stream(movie);
        System.out.println("--- Enjoy the movie! ---\n");
    }

    public void endMovie() {
        System.out.println("\n--- Shutting down home theater ---");
        streamingService.disconnect();
        amplifier.off();
        projector.off();
        smartLights.on();
        System.out.println("--- Home theater off ---\n");
    }
}

public class Home_Theater_System {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        DvdPlayer dvd = new DvdPlayer();
        Projector projector = new Projector();
        SmartLights lights = new SmartLights();
        StreamingService streaming = new StreamingService();

        HomeThreaterFascade theater = new HomeThreaterFascade(amp, dvd, projector, lights, streaming);

        theater.watchMovie("Interstellar");
        theater.endMovie();
    }
}
