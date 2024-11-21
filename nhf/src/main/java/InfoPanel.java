import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JPanel player1;
    private JPanel player2;
    private JButton button;
    private Board board;

    public InfoPanel(Board board) {
        this.board = board;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player1 = new JPanel();
        this.player1.setBackground(Color.RED);
        this.player2 = new JPanel();
        this.player2.setBackground(Color.BLUE);
        this.button = new JButton("This is a button");
        this.add(player1);
        this.add(button);
        this.add(player2);

        this.button.addActionListener(e -> {
            this.board.reset();
        });
    }
}
