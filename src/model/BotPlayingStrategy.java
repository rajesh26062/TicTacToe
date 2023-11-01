package model;

import exception.GameOverException;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player) throws GameOverException;
}