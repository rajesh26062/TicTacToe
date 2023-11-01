package service.winningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(WinningStrategies winningStrategy, int dimension){
        //TODO : add a switch case basis of the strategy chosen and return the object
        switch (winningStrategy.toString()){
            case "ORDERONE_WINNINGSTRATEGY" :
                return new OrderOneWinningStrategy(dimension);
            default:
                return new OrderOneWinningStrategy(dimension);
        }
    }
}
