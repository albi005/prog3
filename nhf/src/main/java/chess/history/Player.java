package chess.history;

import java.util.ArrayList;

/**
 * Represents a player with a list of previous matches.
 */
public final class Player {
    private final String name;
    private final ArrayList<Match> matches;

    public Player(String name, ArrayList<Match> matches) {
        this.name = name;
        this.matches = matches;
    }

    public Player(String name) {
        this(name, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", matches.size()=" + matches.size() +
                '}';
    }

    public String name() {
        return name;
    }

    public ArrayList<Match> matches() {
        return matches;
    }
}
