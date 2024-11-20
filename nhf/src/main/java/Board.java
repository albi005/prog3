import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private final Cell[][] cells = new Cell[8][8];
    public Board() {
        setLayout(new GridLayout(8, 8));
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = new Cell(row, col);
                cells[row][col] = cell;
                add(cell);
            }
        }

        for (int col = 0; col < 8; col++) {
            cells[1][col].setFigure(Figure.createPawn(Color.BLACK));
            cells[6][col].setFigure(Figure.createPawn(Color.WHITE));
        }

        cells[0][0].setFigure(Figure.createRook(Color.BLACK));
        cells[0][7].setFigure(Figure.createRook(Color.BLACK));
        cells[7][0].setFigure(Figure.createRook(Color.WHITE));
        cells[7][7].setFigure(Figure.createRook(Color.WHITE));

        cells[0][1].setFigure(Figure.createKnight(Color.BLACK));
        cells[0][6].setFigure(Figure.createKnight(Color.BLACK));
        cells[7][1].setFigure(Figure.createKnight(Color.WHITE));
        cells[7][6].setFigure(Figure.createKnight(Color.WHITE));

        cells[0][2].setFigure(Figure.createBishop(Color.BLACK));
        cells[0][5].setFigure(Figure.createBishop(Color.BLACK));
        cells[7][2].setFigure(Figure.createBishop(Color.WHITE));
        cells[7][5].setFigure(Figure.createBishop(Color.WHITE));

        cells[0][3].setFigure(Figure.createQueen(Color.BLACK));
        cells[7][3].setFigure(Figure.createQueen(Color.WHITE));

        cells[0][4].setFigure(Figure.createKing(Color.BLACK));
        cells[7][4].setFigure(Figure.createKing(Color.WHITE));
    }
}
