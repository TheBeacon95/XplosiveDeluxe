package common;

/**
 * USed to keep coordinates together.
 *
 * @author Yanick
 */
public final class Coordinates {

    public Coordinates() {
        this(0, 0);
    }

    public Coordinates(int xCoordinate, int yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
    }

    public Coordinates(Coordinates coordinates) {
        this(coordinates.x, coordinates.y);
    }

    public void translate(Direction direction, int distance) {
        switch (direction) {
            case Up ->
                y -= distance;
            case Right ->
                x += distance;
            case Down ->
                y += distance;
            case Left ->
                x -= distance;
            default -> {
            }
        }
    }

    /**
     * Gets the direction to the next cell if it's exactly horizontal or
     * vertical.
     *
     * @param targetCell
     * @return
     */
    public Direction getDirection(Coordinates targetCell) {
        if (x == targetCell.x) {
            return y > targetCell.y ? Direction.Up : Direction.Down;
        }
        else if (y == targetCell.y) {
            return x > targetCell.x ? Direction.Left : Direction.Right;
        }
        return Direction.NoDirection;
    }

    /**
     * Gets the absolute distance between two points
     *
     * @param point1
     * @param point2
     * @return
     */
    public static float getDistance(Coordinates point1, Coordinates point2) {
        int distanceX = point1.x - point2.x;
        int distanceY = point1.y - point2.y;

        return (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }
    
    public Coordinates scale(int factor) {
        return new Coordinates(x*factor, y*factor); // Todo: change to make downward scalable.
    }

    public int x;
    public int y;
}