package chess.history;

/**
 * Represents a match between two players.
 */
public final class Match {
    private final Player white;
    private final Player black;
    private final Player winner;

    public Match(Player white, Player black, Player winner) {
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

    public Player white() {
        return white;
    }

    public Player black() {
        return black;
    }

    public Player winner() {
        return winner;
    }
};
