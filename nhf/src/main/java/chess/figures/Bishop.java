package chess.figures;

import chess.CellDrawContext;
import chess.Cells;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Figure {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected void draw(CellDrawContext c) {
        c.line(0.5, 0.1, 0.3, 0.3);
        c.line(0.3, 0.3, 0.7, 0.7);
        c.line(0.7, 0.7, 0.5, 0.9);
        c.line(0.5, 0.9, 0.3, 0.7);
        c.line(0.3, 0.7, 0.5, 0.1);
    }

    private final int[][] directions = {
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
