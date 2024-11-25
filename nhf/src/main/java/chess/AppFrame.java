package chess;

import chess.history.HistoryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppFrame extends JFrame {
    public AppFrame() {
        super("Chess");
        this.setLayout(new GridLayout(1, 2));
        Board board = new Board();
        this.add(board);
        InfoPanel infoPanel = new InfoPanel(board);
        this.add(infoPanel);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                HistoryService.getInstance().save();
            }
        });
    }
}

