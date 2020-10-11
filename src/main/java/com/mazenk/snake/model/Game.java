package com.mazenk.snake.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the SnakeGame as a whole
 */
public class Game {
    public static final int TICKS_PER_SECOND = 10;
    private final Snake snake = new Snake();
    private final Set<Position> food = new HashSet<>();
    private int score = 0;
    private boolean ended = false;
    private final int maxX;
    private final int maxY;

    public Game(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

        food.add(generateRandomPosition());
    }

    /**
     * Progresses the game state, moving the snake, and handling
     * food
     */
    public void tick() {
        snake.move();

        if (snake.hasCollidedWithSelf() || isOutOfBounds(snake.getHead())) {
            ended = true;
            return;
        }

        handleFood();

        if (food.isEmpty()) {
            spawnNewFood();
        }
    }

    /**
     * Spawns a new food item into a valid
     * position in the game
     */
    public void spawnNewFood() {
        Position pos = generateRandomPosition();

        while (!isValidPosition(pos)) {
            pos = generateRandomPosition();
        }

        food.add(pos);
    }

    /**
     * Returns whether a given position is
     * out of the game frame
     */
    public boolean isOutOfBounds(Position pos) {
        return pos.getX() < 0 ||
                pos.getY() < 0 ||
                pos.getX() > maxX ||
                pos.getY() > maxY;
    }

    /**
     * Returns whether a given position is in bounds
     * and not already occupied
     */
    public boolean isValidPosition(Position pos) {
        return  !isOutOfBounds(pos) &&
                !food.contains(pos) &&
                !snake.hasCollided(pos);
    }

    /**
     * Checks for food that the snake has eaten,
     * grows the snake and increases score if food is found
     */
    private void handleFood() {
        Position eatenFood = food.stream()
                .filter(snake::hasCollided)
                .findFirst()
                .orElse(null);

        if (eatenFood == null) {
            return;
        }

        food.remove(eatenFood);
        score++;
        snake.grow();
    }

    /**
     * Generates a random position.
     * Guaranteed to be in bounds but not necessarily valid
     */
    private Position generateRandomPosition() {
        return new Position (
                ThreadLocalRandom.current().nextInt(maxX),
                ThreadLocalRandom.current().nextInt(maxY)
        );
    }

    public Snake getSnake() {
        return snake;
    }

    public Set<Position> getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }

    public boolean isEnded() {
        return ended;
    }
}
