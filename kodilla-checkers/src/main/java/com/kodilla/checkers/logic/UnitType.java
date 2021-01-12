package com.kodilla.checkers.logic;

public enum UnitType {
    PAWN(1), KING(2);

    public final int layers;

    UnitType(int layers) {
        this.layers = layers;
    }
}
