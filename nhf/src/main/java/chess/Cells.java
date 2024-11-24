package chess;

import chess.figures.Figure;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

public class Cells implements Iterable<Cell> {
    private final Cell[][] cells = new Cell[8][8];

    public Cells() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public Cell get(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8)
            return null;
        return cells[row][col];
    }

    public Cell get(Cell cell, int dx, int dy) {
        return get(cell.getRow() + dy, cell.getCol() + dx);
    }

    public void move(Figure figure, Cell cell) {
        Cell start = figure.getCell();
        start.setFigure(null);
        cell.setFigure(figure);
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<>() {
            private int row = 0;
            private int col = 0;

            @Override
            public boolean hasNext() {
                return row < 8 && col < 8;
            }

            @Override
            public Cell next() {
                Cell cell = cells[row][col];
                col++;
                if (col == 8) {
                    col = 0;
                    row++;
                }
                return cell;
            }
        };
    }
}