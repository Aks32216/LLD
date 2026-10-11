package Structural_Design_Pattern.Bridge_Design_Pattern;

interface Device{
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int volume);
}

class TV implements Device {
    private boolean enabled = false;
    private int volume = 30;

    @Override
    public boolean isEnabled() { return enabled; }

    @Override
    public void enable() {
        enabled = true;
        System.out.println("TV: Turned ON");
    }

    @Override
    public void disable() {
        enabled = false;
        System.out.println("TV: Turned OFF");
    }

    @Override
    public int getVolume() { return volume; }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
        System.out.println("TV: Volume set to " + this.volume);
    }
}

class Radio implements Device {
    private boolean enabled = false;
    private int volume = 20;

    @Override
    public boolean isEnabled() { return enabled; }

    @Override
    public void enable() {
        enabled = true;
        System.out.println("Radio: Turned ON");
    }

    @Override
    public void disable() {
        enabled = false;
        System.out.println("Radio: Turned OFF");
    }

    @Override
    public int getVolume() { return volume; }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
        System.out.println("Radio: Volume set to " + this.volume);
    }
}

abstract class Remote{
    protected Device device;

    Remote(Device device){
        this.device=device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }
}

class BasicRemote extends Remote {
    public BasicRemote(Device device) {
        super(device);
    }
}

// RefinedAbstraction: AdvancedRemote
class AdvancedRemote extends Remote {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
        System.out.println("AdvancedRemote: Muted");
    }
}

public class Remote_And_Devices {
    public static void main(String[] args) {
        System.out.println("--- Basic Remote with TV ---");
        Device tv = new TV();
        Remote basicRemote = new BasicRemote(tv);
        basicRemote.togglePower();
        basicRemote.volumeUp();
        basicRemote.volumeUp();
        basicRemote.volumeDown();

        System.out.println("\n--- Advanced Remote with Radio ---");
        Device radio = new Radio();
        AdvancedRemote advancedRemote = new AdvancedRemote(radio);
        advancedRemote.togglePower();
        advancedRemote.volumeUp();
        advancedRemote.mute();

        System.out.println("\n--- Advanced Remote with TV ---");
        AdvancedRemote tvAdvanced = new AdvancedRemote(tv);
        tvAdvanced.volumeUp();
        tvAdvanced.mute();
    }
}
