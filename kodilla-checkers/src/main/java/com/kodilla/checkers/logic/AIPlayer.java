package com.kodilla.checkers.logic;

import com.kodilla.checkers.gui.Board;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class AIPlayer extends Player {

    private final Random rand = new Random();

    public AIPlayer(Team playerTeam) {
        setPlayerTeam(playerTeam);
        resetPlayer();
        setPlayerType(PlayerType.AI);
    }

    @Override
    public Optional<Move> getPlayerMove(Board board) {
        ArrayList<Move> possibleMoves = board.getPossibleMoves();
        int r = rand.nextInt(possibleMoves.size());
        return Optional.of(possibleMoves.get(r));
    }
}
