package chess;

import chess.figures.*;

import java.awt.*;

public abstract class BoardState {
    public abstract void apply(Board board, Cells cells);

    public void handleCellSelected(Board board, Cell cell) {
    }

    public static class Disabled extends BoardState {
        @Override
        public void apply(Board board, Cells cells) {
            for (Cell cell : cells) {
                cell.setFigure(null);
                cell.setState(CellState.DISABLED);
            }
        }

        public void startGame(Board board) {
            board.setState(new Starting());
        }
    }

    public static class Starting extends BoardState {
        private Starting() {
        }

        @Override
        public void apply(Board board, Cells cells) {
            for (int col = 0; col < 8; col++) {
                cells.get(1, col).setFigure(new Pawn(Color.BLACK));
                cells.get(6, col).setFigure(new Pawn(Color.WHITE));
            }

            cells.get(0, 0).setFigure(new Rook(Color.BLACK));
            cells.get(0, 7).setFigure(new Rook(Color.BLACK));
            cells.get(7, 0).setFigure(new Rook(Color.WHITE));
            cells.get(7, 7).setFigure(new Rook(Color.WHITE));

            cells.get(0, 1).setFigure(new Knight(Color.BLACK));
            cells.get(0, 6).setFigure(new Knight(Color.BLACK));
            cells.get(7, 1).setFigure(new Knight(Color.WHITE));
            cells.get(7, 6).setFigure(new Knight(Color.WHITE));

            cells.get(0, 2).setFigure(new Bishop(Color.BLACK));
            cells.get(0, 5).setFigure(new Bishop(Color.BLACK));
            cells.get(7, 2).setFigure(new Bishop(Color.WHITE));
            cells.get(7, 5).setFigure(new Bishop(Color.WHITE));

            cells.get(0, 3).setFigure(new Queen(Color.BLACK));
            cells.get(7, 3).setFigure(new Queen(Color.WHITE));

            cells.get(0, 4).setFigure(new King(Color.BLACK));
            cells.get(7, 4).setFigure(new King(Color.WHITE));

            board.setState(new Selecting(Color.WHITE));
        }
    }

    public static class Selecting extends BoardState {
        private final Color color;

        public Selecting(Color color) {
            this.color = color;
        }

        @Override
        public void apply(Board board, Cells cells) {
            for (Cell cell : cells) {
                cell.setState(getState(cell));
            }
        }

        @Override
        public void handleCellSelected(Board board, Cell cell) {
            board.setState(new Moving(cell));
        }

        private CellState getState(Cell cell) {
            Figure figure = cell.getFigure();
            if (figure == null) return CellState.NEUTRAL;
            if (figure.getColor() == color) return CellState.SELECTABLE;
            return CellState.NEUTRAL;
        }
    }

    // select another cell or move with the selected cell
    public static class Moving extends BoardState {
        private final Cell selectedCell;

        public Moving(Cell selectedCell) {
            this.selectedCell = selectedCell;
        }

        @Override
        public void apply(Board board, Cells cells) {
            for (Cell cell : cells) {
                cell.setState(getState(cell));
            }
        }

        @Override
        public void handleCellSelected(Board board, Cell cell) {

        }

        private CellState getState(Cell cell) {
            if (cell == selectedCell) return CellState.NEUTRAL;

            return CellState.DISABLED;
        }
    }
}
