package chess.figures;

import chess.Cell;
import chess.CellDrawContext;
import chess.Cells;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Figure {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    protected void draw(CellDrawContext c) {
        c.line(0.5, 0.1, 0.5, 0.9);
        c.line(0.4, 0.9, 0.6, 0.9);
        c.line(0.4, 0.9, 0.5, 0.8);
        c.line(0.5, 0.8, 0.6, 0.9);
    }

    @Override
    public ArrayList<Move> getMoves(Cells cells) {
        Forward forwardOne = new Forward(cell, cells);
        Move forwardTwo = forwardOne.next(cells);
        var result = new ArrayList<Move>();
        result.add(forwardOne);
        result.add(forwardTwo);
        result.add(new Attack(true, cells));
        result.add(new Attack(false, cells));
        return result;
    }

    private int forwardDy() {
        return getColor() == Color.WHITE ? -1 : 1;
    }

    private Cell forward(Cell start, Cells cells) {
        int row = start.getRow() + forwardDy();
        return cells.get(row, start.getCol());
    }

    private int getStartRow() {
        return getColor() == Color.WHITE ? 6 : 1;
    }

    private Cell attack(boolean right, Cells cells) {
        int row = cell.getRow() + forwardDy();
        int col = cell.getCol() + (right ? 1 : -1);
        return cells.get(row, col);
    }

    private class Forward extends Move {
        private Forward(Cell start, Cells cells) {
            super(forward(start, cells));
            if (end != null && end.getFigure() != null)
                end = null;
        }

        public Move next(Cells cells) {
            if (end == null)
                return null;
            if (cell.getRow() != getStartRow())
                return null;
            return new Forward(end, cells);
        }
    }

    private class Attack extends Move {
        public Attack(boolean right, Cells cells) {
            super(attack(right, cells));

            if (end != null && end.getFigure() == null)
                end = null;
        }
    }
}
