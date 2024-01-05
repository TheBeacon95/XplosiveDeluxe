package entity_Impl.Monsters.Behaviors.MovementBehaviors;

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

    static List<Coordinates> eliminateLastCell(List<Coordinates> nextCells, Coordinates position, Direction direction) {
        List<Coordinates> newCells = new ArrayList<>();
        Coordinates oppositeCell = new Coordinates(position);
        oppositeCell.translate(direction.opposite(), 1);
        for (Coordinates cell : nextCells) {
            if (!cell.equals(oppositeCell)) {
                newCells.add(cell);
            }
        }
        return newCells;
    }
}