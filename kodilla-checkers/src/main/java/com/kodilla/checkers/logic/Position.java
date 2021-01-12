package com.kodilla.checkers.logic;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {

    public final int x;
    public final int y;
    public final Position origin;

    public Position(int x, int y) {
        this(null, x, y);
    }

    public Position(Position origin, int x, int y) {
        this.x = x;
        this.y = y;
        this.origin = origin;
    }

    public static int toBoard(double pixel) {
        return (int) (pixel + Game.TILE_SIZE / 2) / Game.TILE_SIZE;
    }

    public static boolean isBoardEdge(Position pos) {
        return pos.x == 0 || pos.y == 0 || pos.x == Game.BOARD_SIZE - 1 || pos.y == Game.BOARD_SIZE - 1;
    }

    public static Position getKillCoords(Move move) {
        Position attackOrigin = move.getOrigin();
        Position attackTarget = move.getTarget();

        int enemyX = (attackOrigin.x + attackTarget.x) / 2;
        int enemyY = (attackOrigin.y + attackTarget.y) / 2;
        return new Position(enemyX, enemyY);
    }

    public boolean isOutsideBoard() {
        return x < 0 || y < 0 || x >= Game.BOARD_SIZE || y >= Game.BOARD_SIZE;
    }

    public boolean isEnemyKingRow(Team team) {
        if (team == Team.BLACK) {
            return y == Game.BOARD_SIZE - 1; //white side
        } else {
            return y == 0; //black side
        }
    }

    // Coordinates methods named like geographic directions
    public Position NE() {
        return new Position(this, x + 1, y - 1);
    }

    public Position NW() {
        return new Position(this, x - 1, y - 1);
    }

    public Position SE() {
        return new Position(this, x + 1, y + 1);
    }

    public Position SW() {
        return new Position(this, x - 1, y + 1);
    }

    public Position getNextOnPath() {
        int xDiff = x - origin.x;
        int yDiff = y - origin.y;

        int xDest = x + xDiff;
        int yDest = y + yDiff;

        return new Position(origin, xDest, yDest);
    }

    public boolean isPlaySquare() {
        return (x + y) % 2 != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position that = (Position) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
