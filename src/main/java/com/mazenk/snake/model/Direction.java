package com.mazenk.snake.model;

/**
 * Represents a facing direction
 * in game
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    RIGHT(1, 0),
    LEFT(-1, 0);

    private int dx;
    private int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Moves a position one increment
     * in the facing direction
     */
    public Position move(Position pos) {
        return new Position (
                pos.getX() + dx,
                pos.getY() + dy
        );
    }
}
