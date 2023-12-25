package entity_Impl.Monsters.Behaviors;

import common.Coordinates;
import common.Direction;
import entity_Impl.Monsters.MonsterAbs;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a common interface for all monster movements.
 *
 * @author Yanick
 */
public interface MovementBehaviorIfc {

    /**
     * Gets the direction in which the monster will move next.
     *
     * @param monster the monster that needs its next direction.
     * @return the direction, the monster will move in.
     */
    Direction getNextMovementDirection(MonsterAbs monster);
    
    static List<Coordinates> eliminateLastCell(List<Coordinates> cells, Coordinates currentPosition, Direction direction) {
        ArrayList<Coordinates> newCells = new ArrayList<>();
        for (Coordinates cell: cells) {
            if (!currentPosition.getDirection(cell).isOpposite(direction)) {
                newCells.add(cell);
            }
        }
        return newCells;
    }
}