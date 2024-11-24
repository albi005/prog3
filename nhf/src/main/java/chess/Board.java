package chess;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private final Cells cells = new Cells();
    private BoardState state;

    public Board() {
        setLayout(new GridLayout(8, 8));

        cells.forEach(this::add);

        setState(new BoardState.Disabled());
    }

    public void setState(BoardState state) {
        this.state = state;
        state.apply(this, cells);
    }

    public BoardState getState() {
        return state;
    }
}
