package Behavioural_Design_Pattern.Iterator_Design_Pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface IterableCollection<T>{
    Iterator<T> createIterator(String type);
}

class Playlist implements IterableCollection<String>{
    private List<String> songs=new ArrayList<>();

    public void addSong(String song) {
        songs.add(song);
    }

    public String getSongAt(int index) {
        return songs.get(index);
    }

    public int getSize() {
        return songs.size();
    }

    @Override
    public Iterator<String> createIterator(String type) {
        switch (type){
            case "reverse":
                return new ReversePlaylistIterator(this);
            case "simple":
                return new PlaylistIterator(this);
            case "shuffle":
                return new ShufflePlaylistIterator(this);
            default: throw new IllegalArgumentException();
        }
    }
}

interface Iterator<T>{
    boolean hasNext();
    T next();
}

class PlaylistIterator implements Iterator<String>{

    private Playlist playlist;
    private int idx=0;

    PlaylistIterator(Playlist playlist){
        this.playlist=playlist;
    }

    @Override
    public boolean hasNext() {
        return idx<playlist.getSize();
    }

    @Override
    public String next() {
        return playlist.getSongAt(idx++);
    }
}

class ReversePlaylistIterator implements Iterator<String> {
    private final Playlist playlist;
    private int index;

    public ReversePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.index = playlist.getSize() - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public String next() {
        return playlist.getSongAt(index--);
    }
}

class ShufflePlaylistIterator implements Iterator<String> {
    private final Playlist playlist;
    private final List<Integer> shuffledIndices;
    private int position = 0;

    public ShufflePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.shuffledIndices = new ArrayList<>();
        for (int i = 0; i < playlist.getSize(); i++) {
            shuffledIndices.add(i);
        }
        Collections.shuffle(shuffledIndices);
    }

    @Override
    public boolean hasNext() {
        return position < shuffledIndices.size();
    }

    @Override
    public String next() {
        int index = shuffledIndices.get(position++);
        return playlist.getSongAt(index);
    }
}


public class Music_Playlist {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong("Shape of You");
        playlist.addSong("Bohemian Rhapsody");
        playlist.addSong("Blinding Lights");

        Iterator<String> iterator = playlist.createIterator("simple");
        Iterator<String> reverseIterator= playlist.createIterator("reverse");
        Iterator<String> shuffleIterator=playlist.createIterator("shuffle");

        System.out.println("Now Playing:");
        while (iterator.hasNext()) {
            System.out.println(" 🎵 " + iterator.next());
        }

        System.out.println("Now Playing");
        while(reverseIterator.hasNext()){
            System.out.println(" 🎵 " + reverseIterator.next());
        }

        System.out.println("Now Playing");
        while(shuffleIterator.hasNext()){
            System.out.println(" 🎵 " + shuffleIterator.next());
        }
    }
}
