package model;

import exception.DuplicateSymbolException;
import exception.InvalidBoardSizeException;
import exception.InvalidBotCountException;
import exception.InvalidNumberOfPlayersException;
import service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return CurrentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        CurrentPlayer = currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Board> getBoardState() {
        return boardState;
    }

    public void setBoardState(List<Board> boardState) {
        this.boardState = boardState;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(WinningStrategy winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    private  Board currentBoard;
    private List<Player> players;
    private Player CurrentPlayer;
    private GameStatus gameStatus;
    private Player winner;
    private List<Move> moves;
    private List<Board> boardState;
    private WinningStrategy winningStrategy;
    private int numberOfSymbols;

    public void setNumberOfSymbols(int numberOfSymbols) {
        this.numberOfSymbols = numberOfSymbols;
    }

    private Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.currentBoard = currentBoard;
        this.players = players;
        this.gameStatus = gameStatus.Inprogress;
        this.moves = new ArrayList<Move>();
        this.boardState = new ArrayList<Board>();
        this.winningStrategy = winningStrategy;
        this.numberOfSymbols = 0;

    }


    public List<Board> getBoardStates() {
        return boardState;
    }

    public int getNumberOfSymbols() {
        return numberOfSymbols;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private  int dimension;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder dimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public int getDimension() {
            return dimension;
        }

        public void setDimension(int dimension) {
            this.dimension = dimension;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public WinningStrategy getWinningStrategy() {
            return winningStrategy;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public  Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }
        public void validateBotCount() throws InvalidBotCountException{
            int botCount = 0 ;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.Bot)){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new InvalidBotCountException("Bot count cannot be more than 1, current count : " + botCount);
            }
        }
        public void validateBoardSize() throws InvalidBoardSizeException {
            if(dimension < 3 || dimension > 10){
                throw new InvalidBoardSizeException("Board size should be >=3 and <=10, current board size" + dimension);
            }
        }
        public void validatePlayerNumber() throws InvalidNumberOfPlayersException {
            if(players.size() != (dimension-1)){
                throw new InvalidNumberOfPlayersException("Number of players is invalid, current count : " + players.size());
            }
        }
        public void validateDuplicateSymbol() throws DuplicateSymbolException {
            HashSet<Character> symbolSet = new HashSet<>();
            for(Player player : players){
                symbolSet.add(player.getSymbol());
            }

            if(symbolSet.size() != players.size()){
                throw new DuplicateSymbolException("All players should have unique symbol");
            }
        }
        public void validate() throws InvalidBoardSizeException, InvalidBotCountException, InvalidNumberOfPlayersException, DuplicateSymbolException {
            validateBoardSize();
            validateBotCount();
            validatePlayerNumber();
            validateDuplicateSymbol();
        }

        public Game build() throws InvalidBotCountException, DuplicateSymbolException, InvalidNumberOfPlayersException, InvalidBoardSizeException {
            validate();
            return new Game(new Board(dimension), players, winningStrategy);
        }

    }




    }
