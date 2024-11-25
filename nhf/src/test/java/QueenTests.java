import chess.figures.*;
import chess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class QueenTests {
    @Test
    public void testQueen() {
        Cells cells = new Cells();
        Figure queen = new Queen(Color.WHITE);
        cells.get(0, 0).setFigure(queen);

        var moves = queen.getMoves(cells);

        Assertions.assertEquals(21, moves.stream().filter(m -> m != null && m.getEnd() != null).count());
        Assertions.assertEquals(2, moves.stream().filter(m -> m != null && m.getEnd() != null && m.getEnd().getCol() == 7).count());
    }
}
