package com.kodilla.checkers.logic;

import com.kodilla.checkers.gui.Board;

import java.util.Optional;

public class HumanPlayer extends Player {
    public HumanPlayer(Team playerTeam) {
        setPlayerTeam(playerTeam);
        resetPlayer();
        setPlayerType(PlayerType.USER);
    }

    @Override
    public Optional<Move> getPlayerMove(Board board) {
        return Optional.empty();
    }
}
