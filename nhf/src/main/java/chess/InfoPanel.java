package chess;

import chess.history.HistoryService;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class InfoPanel extends JPanel {
    private final PlayerPanel player1;
    private final PlayerPanel player2;
    private final Board board;
    private final JMenuItem restart = new JMenuItem("Új játék");
    private final JMenuItem draw = new JMenuItem("Döntetlen");

    public InfoPanel(Board board, JMenu menu) {
        super();
        this.board = board;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player1 = new PlayerPanel();
        this.player2 = new PlayerPanel();
        this.add(player1);
        this.add(player2);

        menu.add(restart);
        menu.add(draw);
        setMenuEnabled(false);

        restart.addActionListener(e -> {
            BoardState.Empty empty = new BoardState.Empty();
            this.board.setState(empty);
            empty.startGame(this.board);
        });
        draw.addActionListener(e -> {
            HistoryService historyService = HistoryService.getInstance();
            historyService.addMatch(player1.getPlayerName(), player2.getPlayerName(), null);
            player1.update();
            player2.update();
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
            }
            if (state instanceof BoardState.Selecting selecting) {
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

            updateMenu();
        });
    }

    private void updateMenu() {
        if (board.getState() instanceof BoardState.Empty empty)
            setMenuEnabled(false);
        else if (board.getState() instanceof BoardState.Checkmated checkmated) {
            restart.setEnabled(true);
            draw.setEnabled(false);
        }
        else
            setMenuEnabled(true);
    }

    private void setMenuEnabled(boolean enabled) {
        restart.setEnabled(enabled);
        draw.setEnabled(enabled);
    }
}
