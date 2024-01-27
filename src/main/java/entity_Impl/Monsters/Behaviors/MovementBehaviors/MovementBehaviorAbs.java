package entity_Impl.Monsters.Behaviors.MovementBehaviors;

import common.*;
import entity_Impl.Monsters.MonsterAbs;
import java.util.ArrayList;
import java.util.List;
import level_Interfaces.*;

/**
 * Defines a common interface for all monster movements.
 * @author Yanick
 */
public abstract class MovementBehaviorAbs {

    /**
     * Gets the direction in which the monster will move next.
     * @param monster the monster that needs its next direction.
     * @return the direction, the monster will move in.
     */
    public abstract Direction getNextMovementDirection(MonsterAbs monster);

    protected static List<Coordinates> eliminateLastCell(List<Coordinates> nextCells, Coordinates position, Direction direction) {
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
    
    protected List<Coordinates> getNextFreeCells(Coordinates gridPosition, Direction direction) {
        MovementServiceIfc movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
        List<Coordinates> nextFreeCells = movementService.getAllFreeNeighboringCells(gridPosition);
        if (nextFreeCells.size() > 1) {
            // Todo: remove this. The MovementBehaviorIfc is in entity_Impl.
            nextFreeCells = MovementBehaviorAbs.eliminateLastCell(nextFreeCells, gridPosition, direction);
        }
        return nextFreeCells;
    }
}