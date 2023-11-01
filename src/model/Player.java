package model;

import exception.GameOverException;

import java.util.Scanner;

public class Player {
    private String playerName;
    private Character symbol;

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.playerName = playerName;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeMove(Board board) throws GameOverException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row for your move, " );
        int row = sc.nextInt();
        System.out.println("Enter the col for your move, " );
        int col = sc.nextInt();

        //TODO: validation for the move, check row and column, and cell status

        board.getBoard().get(row).get(col).setCellState(CellState.Filled);
        board.getBoard().get(row).get(col).setPlayer(this);
        return new Move(row, col, this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    private int id;
    private PlayerType playerType;
}
