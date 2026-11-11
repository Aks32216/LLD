package Game_Question_Practice.Chess.Models;

import Game_Question_Practice.Chess.Enums.Color;

public class Player {
    private final String name;
    private final Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() { return name; }
    public Color getColor() { return color; }

    @Override
    public String toString() { return name + " (" + color + ")"; }
}