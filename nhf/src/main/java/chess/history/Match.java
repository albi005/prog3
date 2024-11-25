package chess.history;

public final class Match {
    private final User white;
    private final User black;
    private final User winner;

    public Match(User white, User black, User winner) {
        this.white = white;
        this.black = black;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Match{" +
                "white=" + white.name() +
                ", black=" + black.name() +
                ", winner=" + (winner == null ? null : winner.name()) +
                '}';
    }

    public User white() {
        return white;
    }

    public User black() {
        return black;
    }

    public User winner() {
        return winner;
    }
};
