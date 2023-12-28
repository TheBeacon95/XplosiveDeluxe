package common;

/**
 * Lists the different directions.
 *
 * @author Yanick
 */
public enum Direction {
    NoDirection,
    Up,
    Right,
    Down,
    Left;

    public boolean isOpposite(Direction direction) {
        return direction == opposite();
    }
    
    public Direction opposite() {
        return switch (this) {
            case Up -> Down;
            case Right -> Left;
            case Down -> Up;
            case Left -> Right;
            default -> NoDirection;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case Up -> "Up";
            case Right -> "Right";
            case Down -> "Down";
            case Left -> "Left";
            default -> "";
        };
    }
}