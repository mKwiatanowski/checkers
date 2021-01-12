package com.kodilla.checkers.logic;



import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Unit extends StackPane {

    private UnitType type;
    private final Team team;
    private double mouseX, mouseY;
    private double currentX, currentY;

    public Unit(UnitType type, Team team, Position position) {
        this.team = team;
        this.type = type;

        move(position);
        PaintPawn();

        setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        setOnMouseDragged(event -> relocate(event.getSceneX() - mouseX + currentX, event.getSceneY() - mouseY + currentY));
    }

    public Team getTeam() {
        return team;
    }

    public int getCurrentX() {
        return Position.toBoard(currentX);
    }

    public int getCurrentY() {
        return Position.toBoard(currentY);
    }

    public Position getPos() {
        return new Position(getCurrentX(), getCurrentY());
    }

    public void move(Position targetPosition) {
        currentX = targetPosition.x * Game.TILE_SIZE;
        currentY = targetPosition.y * Game.TILE_SIZE;
        relocate(currentX, currentY);
    }

    public void abortMove() {
        relocate(currentX, currentY);
    }

    public boolean isBlack() {
        return team == Team.BLACK;
    }

    public boolean isWhite() {
        return team == Team.WHITE;
    }

    public ArrayList<Position> getAdjacentPositions() {
        ArrayList<Position> potentiallyAdjacentTiles = new ArrayList<>();

        Position origin = getPos();

        if (isKing() || isBlack()) {
            potentiallyAdjacentTiles.add(origin.SW());
            potentiallyAdjacentTiles.add(origin.SE());
        }
        if (isKing() || isWhite()) {
            potentiallyAdjacentTiles.add(origin.NW());
            potentiallyAdjacentTiles.add(origin.NE());
        }

        ArrayList<Position> validAdjacentTiles = new ArrayList<>();
        for (Position position : potentiallyAdjacentTiles) {
            if (!position.isOutsideBoard()) {
                validAdjacentTiles.add(position);
            }
        }

        return validAdjacentTiles;
    }

    public void toggleKing() {
        if (isKing()) {
            type = UnitType.PAWN;
            PaintPawn();
        } else {
            type = UnitType.KING;
            PaintKing();
        }
    }

    public boolean isKing() {
        return type == UnitType.KING;
    }

    private void PaintPawn() {
        Circle pawnShape = new Circle(Game.TILE_SIZE * 0.5);

        Image blackPawnGraphic = new Image("file:resources/black_pawn.png");
        Image whitePawnGraphic = new Image("file:resources/white_pawn.png");

        if (team == Team.BLACK) {
            pawnShape.setFill(new ImagePattern(blackPawnGraphic));
        } else if (team == Team.WHITE) {
            pawnShape.setFill(new ImagePattern(whitePawnGraphic));
        }

        getChildren().add(pawnShape);
    }

    private void PaintKing() {
        Circle kingShape = new Circle(Game.TILE_SIZE * 0.5);

        Image blackKingGraphic = new Image("file:resources/black_king_pawn.png");
        Image whiteKingGraphic = new Image("file:resources/white_king_pawn.png");

        if (team == Team.BLACK) {
            kingShape.setFill(new ImagePattern(blackKingGraphic));
        } else if (team == Team.WHITE) {
            kingShape.setFill(new ImagePattern(whiteKingGraphic));
        }

        getChildren().add(kingShape);
    }
}
