package chess;

import chess.figures.*;

import java.awt.*;
import java.util.HashMap;

public abstract class BoardState {
    public abstract void apply(Board board, Cells cells);

    public void handleCellSelected(Board board, Cell cell) {
    }

    public static class Empty extends BoardState {
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

    private static class Starting extends BoardState {
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
        private final Figure selectedFigure;
        private final HashMap<Cell, Figure.Move> getMove = new HashMap<>();
        private Cells cells;

        public Moving(Cell selectedCell) {
            assert selectedCell.getFigure() != null;
            this.selectedCell = selectedCell;
            this.selectedFigure = selectedCell.getFigure();
        }

        @Override
        public void apply(Board board, Cells cells) {
            Figure figure = selectedCell.getFigure();
            var moves = figure.getMoves(cells);
            for (Figure.Move move : moves) {
                if (move == null) continue;
                Cell end = move.getEnd();
                if (end == null)
                    continue;
                getMove.put(end, move);
            }

            for (Cell cell : cells) {
                cell.setState(getState(cell, cells));
            }

            this.cells = cells;
        }

        @Override
        public void handleCellSelected(Board board, Cell cell) {
            Figure figure = cell.getFigure();
            if (figure != null && figure.getColor() == selectedFigure.getColor()) {
                board.setState(new Moving(cell));
                return;
            }

            Figure.Move move = getMove.get(cell);
            move.execute(cells);
            Color nextColor = selectedFigure.getColor() == Color.WHITE
                    ? Color.BLACK
                    : Color.WHITE;

            removeEnPassant(nextColor);

            board.setState(new Selecting(nextColor));
        }

        private CellState getState(Cell cell, Cells cells) {
            if (cell == selectedCell) return CellState.DISABLED;

            Figure figure = cell.getFigure();
            if (figure != null && figure.getColor() == selectedFigure.getColor())
                return CellState.SELECTABLE;

            if (getMove.containsKey(cell))
                return CellState.SELECTABLE;

            return CellState.DISABLED;
        }

        private void removeEnPassant(Color color) {
            for (Cell cell : cells) {
                Figure figure = cell.getFigure();
                if (figure instanceof Pawn.EnPassant enPassant && enPassant.getColor() == color)
                    cell.setFigure(null);
            }
        }
    }
}
