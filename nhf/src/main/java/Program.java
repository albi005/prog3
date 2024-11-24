import chess.AppFrame;

import javax.swing.*;

public class Program {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        AppFrame frame = new AppFrame();
        frame.setVisible(true);
    }
}
