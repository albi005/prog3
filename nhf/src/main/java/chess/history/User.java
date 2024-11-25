package chess.history;

import java.util.ArrayList;

public final class User {
    private final String name;
    private final ArrayList<Match> matches;

    public User(String name, ArrayList<Match> matches) {
        this.name = name;
        this.matches = matches;
    }

    public User(String name) {
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
