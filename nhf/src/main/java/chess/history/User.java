package chess.history;

import java.util.ArrayList;

public record User(String name, ArrayList<Match> matches) {
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
}
