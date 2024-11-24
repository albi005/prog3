package chess.figures;

import chess.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Knight extends Figure {
    public Knight(Color color) {
        super(color);
    }

    @Override
    protected void draw(CellDrawContext c) {
        c.line(0.3, 0.1, 0.3, 0.9);
        c.line(0.3, 0.9, 0.5, 0.7);
        c.line(0.5, 0.7, 0.7, 0.9);
        c.line(0.7, 0.9, 0.7, 0.1);
        c.line(0.7, 0.1, 0.5, 0.3);
        c.line(0.5, 0.3, 0.3, 0.1);
    }

    private final int[][] directions = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

    @Override
    public ArrayList<Move> getMoves(Cells cells) {
        return Arrays.stream(directions)
                .map(dir -> new KnightMove(dir[0], dir[1], cells))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private class KnightMove extends Move {
        public KnightMove(int dx, int dy, Cells cells) {
            super(cells.get(cell, dx, dy));
        }
    }
}

