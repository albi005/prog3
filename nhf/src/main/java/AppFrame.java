import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private final Board board = new Board();
    private final InfoPanel infoPanel = new InfoPanel(board);

    public AppFrame() {
        super("Chess");
        this.setLayout(new GridLayout(1, 2));
        this.add(board);
        this.add(infoPanel);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

