package com.kodilla.checkers.logic;


import com.kodilla.checkers.gui.Board;

import java.util.Optional;

public abstract class Player {

    private PlayerType playerType;
    private boolean isPlayersTurn;
    private Team playerTeam;

    abstract Optional<Move> getPlayerMove(Board board);

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public boolean isPlayerHuman() {
        return playerType == PlayerType.USER;
    }

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        isPlayersTurn = playersTurn;
    }

    public Team getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }

    public void switchTurn() {
        isPlayersTurn = !isPlayersTurn;
    }

    public void resetPlayer() {
        isPlayersTurn = playerTeam == Team.BLACK;
    }
}
