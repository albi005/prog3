import chess.figures.*;
import chess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class KingTests {
    @Test
    public void testKing() {
        Cells cells = new Cells();
        King king = new King(Color.WHITE);
        cells.get(0, 0).setFigure(king);

        var moves = king.getMoves(cells);

        Assertions.assertEquals(3, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
        Assertions.assertFalse(king.isChecked(cells));
    }

    @Test
    public void testKingVsBishop() {
        Cells cells = new Cells();
        King king = new King(Color.WHITE);
        cells.get(0, 0).setFigure(king);
        cells.get(1, 1).setFigure(new Bishop(Color.BLACK));

        var moves = king.getMoves(cells);

        Assertions.assertEquals(3, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
        Assertions.assertTrue(king.isChecked(cells));
    }

    @Test
    public void testKingVsRook() {
        Cells cells = new Cells();
        King king = new King(Color.WHITE);
        cells.get(0, 0).setFigure(king);
        cells.get(1, 1).setFigure(new Rook(Color.BLACK));

        var moves = king.getMoves(cells);

        Assertions.assertEquals(1, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
        Assertions.assertFalse(king.isChecked(cells));
    }

    @Test
    public void testKingVsQueen() {
        Cells cells = new Cells();
        King king = new King(Color.WHITE);
        cells.get(0, 0).setFigure(king);
        cells.get(1, 1).setFigure(new Queen(Color.BLACK));

        var moves = king.getMoves(cells);

        Assertions.assertEquals(1, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
        Assertions.assertTrue(king.isChecked(cells));
    }

    @Test
    public void testCheckmate() {
        Cells cells = new Cells();
        King king = new King(Color.WHITE);
        cells.get(0, 0).setFigure(king);
        cells.get(0, 2).setFigure(new Rook(Color.BLACK));
        cells.get(1, 2).setFigure(new Rook(Color.BLACK));

        var moves = king.getMoves(cells);

        Assertions.assertEquals(0, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
        Assertions.assertTrue(king.isChecked(cells));
    }
}

