package com.mazenk.snake.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the snake/player
 */
public class Snake {
    private Position head;
    // first element is last part, last element is next to head
    private List<Position> body;
    // current facing direction
    private Direction direction;
    // the last position that was removed as a result of moving
    private Position lastRemoved;

    public Snake() {
        this.head = new Position(1, 1);
        this.body = new ArrayList<>();
        this.direction = Direction.RIGHT;
    }

    /**
     * Progresses the snake in the current direction
     */
    public void move() {
        // add the head to the end to fill the gap from moving
        body.add(head);
        // remove the tail of the snake to maintain size
        lastRemoved = body.remove(0);

        this.head = direction.move(this.head);
    }

    /**
     * Returns whether the snake has collided
     * with a given position (either through body or head)
     */
    public boolean hasCollided(Position pos) {
        return head.equals(pos) || body.contains(pos);
    }

    /**
     * Returns whether the snake has collided with itself
     */
    public boolean hasCollidedWithSelf() {
        return body.contains(head);
    }

    /**
     * Grows the tail of the snake. Requires that
     * move has already been called.
     */
    public void grow() {
        body.add(0, lastRemoved);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position getHead() {
        return head;
    }

    public List<Position> getBody() {
        return body;
    }
}
