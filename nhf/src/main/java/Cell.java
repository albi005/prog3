import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell extends JComponent {
    public static final Color Light = new Color(0xFFCE9E);
    public static final Color Dark = new Color(0xD18B47);
    private final int row;
    private final int col;
    private boolean isHovered = false;
    private boolean isPressed = false;

    private Figure figure;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        setPreferredSize(new Dimension(96, 96));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(row % 2 == col % 2 ? Light : Dark);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (figure != null) {
            CellDrawContext c = new CellDrawContext(g, getWidth(), getHeight());
            figure.draw(c, g);
        }

        if (isHovered) {
            g.setColor(new Color(0, 0, 0, 32));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (isPressed) {
            g.setColor(new Color(0, 0, 0, 32));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent e) {
        super.processMouseMotionEvent(e);

        if (e.getID() == MouseEvent.MOUSE_ENTERED)
            isHovered = true;
        else if (e.getID() == MouseEvent.MOUSE_EXITED)
            isHovered = false;

        repaint();
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
