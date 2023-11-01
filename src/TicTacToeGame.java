import controller.GameController;
import exception.GameOverException;
import model.*;
import service.winningStrategy.WinningStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class TicTacToeGame {
    public static void  main(String[] args) throws GameOverException {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("Enter the dimension of the game");
        int dimension = sc.nextInt();

        System.out.println("Will there be any bot in the game ? Y/N");
        String isBotPresent = sc.next();

        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension - 1;

        if(isBotPresent.equalsIgnoreCase("Y")){
            iteratorNumber = iteratorNumber - 1;
        }
        for(int i=1;i<=iteratorNumber;i++){
            System.out.println("What is the name of player, number : " + i);
            String playerName = sc.next();

            System.out.println("What is the symbol of player, number : " + i);
            String symbol = sc.next();

            players.add(new Player(i, playerName, symbol.charAt(0), PlayerType.Human));
        }

        if(isBotPresent.equalsIgnoreCase("Y")){
            System.out.println("What is the name of BOT");
            String botName = sc.next();

            System.out.println("What is the symbol of BOT");
            String botSymbol = sc.next();

            //TODO: Take input for BOT difficulty level, and set strategy accordingly
            BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.Easy;
            Bot bot = new Bot(
                    dimension,
                    botName,
                    botSymbol.charAt(0),
                    PlayerType.Bot,
                    botDifficultyLevel
            );
            players.add(bot);
        }
        //randomises the list of players, so order of playing is completely random
        Collections.shuffle(players);
        Game game = gameController.createGame(dimension, players, WinningStrategies.ORDERONE_WINNINGSTRATEGY);
        int playerIndex = -1;
        while(gameController.getGameStatus(game).equals(GameStatus.Inprogress)){
            System.out.println("Current board status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
//            System.out.println("Do you want to undo your move? Y/N");
//            String isUndoRequired = sc.next();
//            if(isUndoRequired.equalsIgnoreCase("Y")){
//                gameController.undoMove(game, movePlayed);
//            }
            Player winner = gameController.checkWinner(game, movePlayed);
            if(winner != null){
                System.out.println("WINNER IS : " + winner.getPlayerName());
                break;
            }
        }
        System.out.println("Final board status");
        gameController.displayBoard(game);
        System.out.println("Do you want a replay");
        //TODO : call the replay logic here



    }
}
