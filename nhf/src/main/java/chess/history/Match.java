package chess.history;

public record Match(User white, User black, User winner) {
    @Override
    public String toString() {
        return "Match{" +
                "white=" + white.name() +
                ", black=" + black.name() +
                ", winner=" + (winner == null ? null : winner.name()) +
                '}';
    }
};
