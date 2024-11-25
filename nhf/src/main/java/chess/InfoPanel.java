package chess;

import chess.history.HistoryService;
import chess.history.Match;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class InfoPanel extends JPanel {
    private final PlayerPanel player1;
    private final PlayerPanel player2;
    private final JButton button;
    private final Board board;

    public InfoPanel(Board board) {
        super();
        this.board = board;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player1 = new PlayerPanel();
        this.player2 = new PlayerPanel();
        this.button = new JButton();
        this.button.setVisible(false);
        this.add(player1);
        this.add(button);
        this.add(player2);

        this.button.addActionListener(e -> {
            BoardState state = this.board.getState();
            if (state instanceof BoardState.Empty emptyState) {
                emptyState.startGame(this.board);
                return;
            }

            BoardState.Empty empty = new BoardState.Empty();
            this.board.setState(empty);
            empty.startGame(this.board);
        });

        Consumer<PlayerPanel> onPlayerChanged = playerPanel -> {
            BoardState.Empty empty;
            if (board.getState() instanceof BoardState.Empty emptyState)
                empty = emptyState;
            else {
                empty = new BoardState.Empty();
                this.board.setState(empty);
            }

            if (player1.getPlayerName() != null && player2.getPlayerName() != null)
                empty.startGame(this.board);
        };
        this.player1.setOnPlayerChanged(onPlayerChanged);
        this.player2.setOnPlayerChanged(onPlayerChanged);

        board.setOnStateChanged(state -> {
            if (state instanceof BoardState.Empty empty) {
                button.setVisible(false);
            }
            if (state instanceof BoardState.Selecting selecting) {
                button.setVisible(true);
                button.setText("Új játék");
            }
            if (state instanceof BoardState.Moving moving) {
            }
            if (state instanceof BoardState.Checked checked) {
            }
            if (state instanceof BoardState.Checkmated checkmated) {
                HistoryService historyService = HistoryService.getInstance();
                PlayerPanel winner;
                PlayerPanel loser;
                if (checkmated.getColor() == Color.WHITE) {
                    winner = player2;
                    loser = player1;
                } else {
                    winner = player1;
                    loser = player2;
                }

                historyService.addMatch(winner.getPlayerName(), loser.getPlayerName(), winner.getPlayerName());
                winner.update();
                loser.update();
            }
        });
    }
}
