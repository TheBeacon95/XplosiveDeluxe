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
        return switch (this) {
            case Up -> direction == Down;
            case Right -> direction == Left;
            case Down -> direction == Up;
            case Left -> direction == Right;
            default -> false;
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