package chess;

public class CellState {
    public static final CellState DISABLED = new CellState(true, false);
    public static final CellState SELECTABLE = new CellState(false, true);
    public static final CellState NEUTRAL = new CellState(false, false);

    private final boolean isDisabled;
    private final boolean isSelectable;

    private CellState(boolean isDisabled, boolean isSelectable) {
        this.isDisabled = isDisabled;
        this.isSelectable = isSelectable;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public boolean isSelectable() {
        return isSelectable;
    }
}
