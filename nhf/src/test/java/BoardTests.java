import chess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTests {
    @Test
    public void initialState() {
        Board board = new Board();
        Assertions.assertInstanceOf(BoardState.Empty.class, board.getState());
    }

    @Test
    public void foolsMate() {
        Board board = new Board();
        TestBoardState testBoardState = new TestBoardState(c -> {
        });
        board.setState(testBoardState);
        Cells cells = testBoardState.cells;

        BoardState.Empty empty = new BoardState.Empty();
        board.setState(empty);
        empty.startGame(board);
        Assertions.assertInstanceOf(BoardState.Selecting.class, board.getState());
        board.getState().handleCellSelected(board, cells.get(6, 5));
        Assertions.assertInstanceOf(BoardState.Moving.class, board.getState());
        board.getState().handleCellSelected(board, cells.get(5, 5));
        board.getState().handleCellSelected(board, cells.get(1, 4));
        board.getState().handleCellSelected(board, cells.get(2, 4));
        board.getState().handleCellSelected(board, cells.get(6, 6));
        board.getState().handleCellSelected(board, cells.get(4, 6));
        board.getState().handleCellSelected(board, cells.get(0, 3));
        board.getState().handleCellSelected(board, cells.get(4, 7));
        Assertions.assertInstanceOf(BoardState.Checkmated.class, board.getState());
    }
}