import javax.swing.*;
import java.awt.*;

public class Program {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        JFrame frame = new JFrame("Swing Example");

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JPanel player1 = new JPanel();
        player1.setBackground(Color.RED);
        JPanel player2 = new JPanel();
        player2.setBackground(Color.BLUE);
        infoPanel.add(player1);
        infoPanel.add(new JButton("This is a text field"));
        infoPanel.add(player2);

        frame.setLayout(new GridLayout(1, 2));
        frame.add(new Board());
        frame.add(infoPanel);

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

