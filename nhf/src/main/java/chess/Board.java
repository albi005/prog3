package chess;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class Board extends JPanel {
    private final Cells cells = new Cells();
    private BoardState state;
    private Consumer<BoardState> onStateChanged;

    public Board() {
        setLayout(new GridLayout(8, 8));

        cells.forEach(this::add);
        cells.forEach(c -> c.setOnClick(cell -> state.handleCellSelected(this, cell)));

        setState(new BoardState.Empty());
    }

    public void setState(BoardState state) {
        this.state = state;
        state.apply(this, cells);
        if (onStateChanged != null) onStateChanged.accept(state);
    }

    public BoardState getState() {
        return state;
    }

    public void setOnStateChanged(Consumer<BoardState> onStateChanged) {
        this.onStateChanged = onStateChanged;
    }
}
