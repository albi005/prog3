package chess.figures;

import chess.CellDrawContext;
import chess.Cells;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Figure {
    public Queen(Color color) {
        super(color);
    }

    @Override
    protected void draw(CellDrawContext c) {
        c.line(0.3, 0.1, 0.3, 0.9);
        c.line(0.7, 0.1, 0.7, 0.9);
        c.line(0.3, 0.1, 0.7, 0.1);
        c.line(0.3, 0.9, 0.7, 0.9);
        c.line(0.3, 0.1, 0.7, 0.9);
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

    @Override
    public ArrayList<Move> getMoves(Cells cells) {
        return getStraightMoves(directions, cells);
    }
}
