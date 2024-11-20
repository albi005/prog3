import java.awt.*;

public class Figure {
    private final Color color;
    private final String name;
    private final FigureDraw draw;

    public void draw(CellDrawContext c, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g.setColor(Color.GRAY);
        g.translate(2, 2);
        draw.draw(c);
        g.translate(-2, -2);
        g.setColor(color);
        draw.draw(c);
    }

    public interface FigureDraw {
        void draw(CellDrawContext c);
    }

    public Figure(Color color, String name, FigureDraw draw) {
        this.color = color;
        this.name = name;
        this.draw = draw;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public static Figure createPawn(Color color) {
        return new Figure(color, "Pawn", c -> {
            c.line(0.5, 0.1, 0.5, 0.9);
            c.line(0.4, 0.9, 0.6, 0.9);
            c.line(0.4, 0.9, 0.5, 0.8);
            c.line(0.5, 0.8, 0.6, 0.9);
        });
    }

    public static Figure createRook(Color color) {
        return new Figure(color, "Rook", c -> {
            c.line(0.3, 0.1, 0.3, 0.9);
            c.line(0.7, 0.1, 0.7, 0.9);
            c.line(0.3, 0.1, 0.7, 0.1);
            c.line(0.3, 0.9, 0.7, 0.9);
        });
    }

    public static Figure createKnight(Color color) {
        return new Figure(color, "Knight", c -> {
            c.line(0.3, 0.1, 0.3, 0.9);
            c.line(0.3, 0.9, 0.5, 0.7);
            c.line(0.5, 0.7, 0.7, 0.9);
            c.line(0.7, 0.9, 0.7, 0.1);
            c.line(0.7, 0.1, 0.5, 0.3);
            c.line(0.5, 0.3, 0.3, 0.1);
        });
    }

    public static Figure createBishop(Color color) {
        return new Figure(color, "Bishop", c -> {
            c.line(0.5, 0.1, 0.3, 0.3);
            c.line(0.3, 0.3, 0.7, 0.7);
            c.line(0.7, 0.7, 0.5, 0.9);
            c.line(0.5, 0.9, 0.3, 0.7);
            c.line(0.3, 0.7, 0.5, 0.1);
        });
    }

    public static Figure createQueen(Color color) {
        return new Figure(color, "Queen", c -> {
            c.line(0.3, 0.1, 0.3, 0.9);
            c.line(0.7, 0.1, 0.7, 0.9);
            c.line(0.3, 0.1, 0.7, 0.1);
            c.line(0.3, 0.9, 0.7, 0.9);
            c.line(0.3, 0.1, 0.7, 0.9);
        });
    }

    public static Figure createKing(Color color) {
        return new Figure(color, "King", c -> {
            c.line(0.3, 0.1, 0.3, 0.9);
            c.line(0.7, 0.1, 0.7, 0.9);
            c.line(0.3, 0.1, 0.7, 0.1);
            c.line(0.3, 0.9, 0.7, 0.9);
            c.line(0.3, 0.1, 0.7, 0.9);
            c.line(0.3, 0.1, 0.7, 0.9);
            c.line(0.3, 0.5, 0.7, 0.5);
            c.line(0.5, 0.1, 0.5, 0.9);
        });
    }
}

