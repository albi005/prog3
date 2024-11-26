package chess;

import chess.history.HistoryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppFrame extends JFrame {
    public AppFrame() {
        super("Chess");

        Board board = new Board();
        this.add(board, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Játék");
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        InfoPanel infoPanel = new InfoPanel(board, menu);
        this.add(infoPanel, BorderLayout.EAST);

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                HistoryService.getInstance().save();
            }
        });
    }
}

