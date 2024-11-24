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
        ForwardOne forwardOne = new ForwardOne(cells);
        ForwardTwo forwardTwo = forwardOne.next(cells);
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

    private class ForwardOne extends Move {
        private ForwardOne(Cells cells) {
            super(forward(cell, cells));
            if (end != null && end.getFigure() != null)
                end = null;
        }

        public ForwardTwo next(Cells cells) {
            if (end == null)
                return null;
            if (cell.getRow() != getStartRow())
                return null;
            return new ForwardTwo(end, cells);
        }
    }

    private class ForwardTwo extends Move {
        private final Cell forwardOne;

        private ForwardTwo(Cell forwardOne, Cells cells) {
            super(forward(forwardOne, cells));
            if (end != null && end.getFigure() != null)
                end = null;
            this.forwardOne = forwardOne;
        }

        @Override
        public void execute(Cells cells) {
            super.execute(cells);
            forwardOne.setFigure(new EnPassant());
        }
    }

    private class Attack extends Move {
        public Attack(boolean right, Cells cells) {
            super(attack(right, cells));

            if (end != null && end.getFigure() == null)
                end = null;
        }

        @Override
        public void execute(Cells cells) {
            if (end.getFigure() instanceof EnPassant enPassant)
                enPassant.finish();
            super.execute(cells);
        }
    }

    public class EnPassant extends Figure {
        public EnPassant() {
            super(Pawn.this.color);
        }

        @Override
        protected void draw(CellDrawContext c) {
            // nothing
        }

        @Override
        public ArrayList<Move> getMoves(Cells cells) {
            return new ArrayList<>();
        }

        private void finish() {
            Pawn.this.cell.setFigure(null);
        }
    }
}
