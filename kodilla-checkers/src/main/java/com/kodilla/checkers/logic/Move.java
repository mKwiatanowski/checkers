package com.kodilla.checkers.logic;



import com.kodilla.checkers.messages.InvalidMoveError;

public class Move {

    private final Position origin;
    private final Position target;
    private final MoveType type;
    private boolean kingCreated;

    private InvalidMoveError InvalidMoveError = null;

    public Move(Position origin, Position target, MoveType type) {
        this.origin = origin;
        this.target = target;
        this.type = type;
    }

    public MoveType getType() {
        return type;
    }

    public boolean isKingCreated() {
        return kingCreated;
    }

    public void createKing() {
        kingCreated = true;
    }

    public Position getOrigin() {
        return origin;
    }

    public Position getTarget() {
        return target;
    }

    public InvalidMoveError getInvalidMoveError() {
        return InvalidMoveError;
    }

    public void setInvalidMoveExplanation(InvalidMoveError InvalidMoveError) {
        this.InvalidMoveError = InvalidMoveError;
    }
}

