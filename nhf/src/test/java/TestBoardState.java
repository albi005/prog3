import chess.*;

import java.util.function.Consumer;

public class TestBoardState extends BoardState {
    private final Consumer<Cells> apply;
    public Cells cells;

    public TestBoardState(Consumer<Cells> apply) {
        this.apply = apply;
    }

    @Override
    public void apply(Board board, Cells cells) {
        apply.accept(cells);
        this.cells = cells;
    }
}
