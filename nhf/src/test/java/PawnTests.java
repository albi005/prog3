import chess.figures.*;
import chess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class PawnTests {
    @Test
    public void testPawn() {
        Cells cells = new Cells();
        cells.get(6, 0).setFigure(new Pawn(Color.WHITE));

        var moves = cells.get(6, 0).getFigure().getMoves(cells);

        Assertions.assertEquals(4, moves.size());
        Assertions.assertEquals(2, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
    }

    @Test
    public void testPawnAttackBoth() {
        Cells cells = new Cells();
        cells.get(6, 1).setFigure(new Pawn(Color.WHITE));
        cells.get(5, 2).setFigure(new Pawn(Color.BLACK));
        cells.get(5, 0).setFigure(new Pawn(Color.BLACK));

        var moves = cells.get(6, 1).getFigure().getMoves(cells);

        Assertions.assertEquals(4, moves.size());
        Assertions.assertEquals(4, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
    }

    @Test
    public void testPawnAttack1() {
        Cells cells = new Cells();
        cells.get(6, 1).setFigure(new Pawn(Color.WHITE));
        cells.get(5, 2).setFigure(new Pawn(Color.BLACK));

        var moves = cells.get(6, 1).getFigure().getMoves(cells);

        Assertions.assertEquals(4, moves.size());
        Assertions.assertEquals(3, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
    }
}
