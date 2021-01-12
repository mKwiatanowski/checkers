package com.kodilla.checkers.GUI;


import com.kodilla.checkers.logic.Position;
import com.kodilla.checkers.logic.Game;
import com.kodilla.checkers.logic.Unit;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Unit unit;
    private final boolean light;

    public Tile(Position position) {
        setWidth(Game.TILE_SIZE);
        setHeight(Game.TILE_SIZE);

        light = (position.x + position.y) % 2 == 0;
        resetTileColor();

        relocate(position.x * Game.TILE_SIZE, position.y * Game.TILE_SIZE);
    }

    public void resetTileColor() {
        if (light) {
            setFill(Color.WHITE);
        } else {
            setFill(Color.BLACK);
        }
    }

    public boolean hasUnit() {
        return unit != null;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void highlightAttackDestination() {
        setFill(Color.RED);
    }

    public void highlightMoveDestination() {
        setFill(Color.BLUE);
    }

    public void highlightUnit() {
        setFill(Color.GREEN);
    }

    public void highlightAIMove() {
        setFill(Color.ORANGE);
    }
}