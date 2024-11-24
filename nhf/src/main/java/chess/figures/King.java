package chess.figures;

import chess.Cell;
import chess.CellDrawContext;
import chess.Cells;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class King extends Figure {
    public King(Color color) {
        super(color);
    }

    @Override
    protected void draw(CellDrawContext c) {
        c.line(0.3, 0.1, 0.3, 0.9);
        c.line(0.7, 0.1, 0.7, 0.9);
        c.line(0.3, 0.1, 0.7, 0.1);
        c.line(0.3, 0.9, 0.7, 0.9);
        c.line(0.3, 0.1, 0.7, 0.9);
        c.line(0.3, 0.1, 0.7, 0.9);
        c.line(0.3, 0.5, 0.7, 0.5);
        c.line(0.5, 0.1, 0.5, 0.9);
    }

    private final int[][] directions = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    private class KingMove extends StraightMove {
        public KingMove(Cell start, int dx, int dy, Cells cells, HashSet<Cell> isAttackable) {
            super(start, dx, dy, cells);

            if (end != null && isAttackable.contains(end))
                end = null;
        }
    }

    @Override
    public ArrayList<Move> getMoves(Cells cells) {
        var result = new ArrayList<Move>();
        var isAttackable = new HashSet<Cell>();
        for (Cell cell : cells) {
            Figure figure = cell.getFigure();
            if (figure == null)
                continue;
            if (figure.getColor() == color)
                continue;
            if (figure instanceof King otherKing) {
                for (int[] dir : directions) {
                    Cell end = cells.get(cell, dir[0], dir[1]);
                    if (end != null)
                        isAttackable.add(end);
                }
                continue;
            }
            for (Move move : figure.getMoves(cells)) {
                if (move == null || move.getEnd() == null)
                    continue;
                Cell end = move.getEnd();
                isAttackable.add(end);
            }
        }

        for (int[] dir : directions) {
            result.add(new KingMove(cell, dir[0], dir[1], cells, isAttackable));
        }

        return result;
    }
}
