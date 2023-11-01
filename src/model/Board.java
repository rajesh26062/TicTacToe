package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int boardSize;
    private List<List<Cell>> board;

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.board = new ArrayList<>();


        for (int i = 0; i < boardSize; i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < boardSize; j++) {
                this.board.get(i).add(new Cell(i, j));
            }
        }
    }
    public void printBoard(){
        for(int i=0;i<boardSize;i++){
            List<Cell> cells = board.get(i);
            for(Cell cell : cells){
                cell.display();
            }
            System.out.println();
        }
    }
    public Board(Board board){
        this.boardSize = board.boardSize;
        this.board = board.board;
    }
}