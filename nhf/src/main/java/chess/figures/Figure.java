package chess.figures;

import chess.Cell;
import chess.CellDrawContext;
import chess.Cells;

import java.awt.*;
import java.util.ArrayList;

public abstract class Figure {
    protected final Color color;

    /// The current cell of the figure
    protected Cell cell;

    public Figure(Color color) {
        this.color = color;
    }

    public void draw(CellDrawContext c, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g.setColor(Color.GRAY);
        g.translate(2, 2);
        draw(c);
        g.translate(-2, -2);
        g.setColor(color);
        draw(c);
    }

    public Color getColor() {
        return color;
    }

    /// Draws the figure onto the (0, 0) to (1, 1) square
    protected abstract void draw(CellDrawContext c);

    /// Returns the list of possible moves for the figure
    public abstract ArrayList<Move> getMoves(Cells cells);

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    protected boolean isAlly(Cell cell) {
        if (cell == null)
            return false;
        if (cell.getFigure() == null)
            return false;
        return cell.getFigure().getColor() == color;
    }

    public abstract class Move {
        /// The cell where the figure will be moved. If null, the move is invalid
        protected Cell end;

        protected Move(Cell end) {
            this.end = end;

            if (isAlly(end))
                this.end = null;
        }

        public Cell getEnd() {
            return end;
        }

        public void execute(Cells cells) {
            cells.move(Figure.this, end);
        }

        @Override
        public String toString() {
            if (end == null)
                return "Move(null)";
            return "Move(r" + end.getRow() + ", c" + end.getCol() + ")";
        }
    }

    protected class StraightMove extends Move {
        public StraightMove(Cell start, int dx, int dy, Cells cells) {
            super(cells.get(start, dx, dy));
        }

        public StraightMove next(int dx, int dy, Cells cells) {
            if (end == null)
                return null;
            if (end.getFigure() != null)
                return null;
            return new StraightMove(end, dx, dy, cells);
        }

    }

    public ArrayList<Move> getStraightMoves(int[][] directions, Cells cells) {
        var result = new ArrayList<Move>();
        for (int[] dir : directions) {
            StraightMove move = new StraightMove(cell, dir[0], dir[1], cells);
            while (move != null && move.end != null) {
                result.add(move);
                move = move.next(dir[0], dir[1], cells);
            }
        }
        return result;
    }
}

