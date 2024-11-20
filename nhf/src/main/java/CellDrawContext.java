import java.awt.*;

public class CellDrawContext {
    private final Graphics g;
    private final double width;
    private final double height;

    public CellDrawContext(Graphics g, double width, double height) {
        this.g = g;
        this.width = width;
        this.height = height;
    }

    public void line(double x1, double y1, double x2, double y2) {
        g.drawLine((int) (x1 * width), (int) (y1 * height), (int) (x2 * width), (int) (y2 * height));
    }
}
