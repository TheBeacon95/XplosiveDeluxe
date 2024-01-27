package entity_Impl.Monsters.Behaviors.MovementBehaviors;

import common.*;
import level_Interfaces.*;
import java.util.List;
import java.util.Random;

/**
 * Hostile movement is for entities that chase the nearest player.
 *
 * @author Yanick
 */
public class BrickEaterMovement extends HostileMovement {

    public BrickEaterMovement() {
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
        m_random = new Random();
    }

    @Override
    protected List<Coordinates> getNextFreeCells(Coordinates gridPosition, Direction direction) {        
        List<Coordinates> nextFreeCells = m_movementService.getAllFreeNeighboringCells(gridPosition);
        List<Coordinates> nextEatableCells = m_movementService.getAllEatableNeighboringCells(gridPosition);
        
        int freeCellsCount = nextFreeCells.size();
        int eatableCellsCount = nextEatableCells.size();
        
        if (freeCellsCount + eatableCellsCount > 1) {
            nextFreeCells = MovementBehaviorAbs.eliminateLastCell(nextFreeCells, gridPosition, direction);
            nextEatableCells = MovementBehaviorAbs.eliminateLastCell(nextEatableCells, gridPosition, direction);
        }
        
        if (nextFreeCells.isEmpty()) {
            return nextEatableCells;
        }
        else if (nextEatableCells.isEmpty()) {
            return nextFreeCells;
        }
        else {
            return m_random.nextBoolean() ? nextFreeCells : nextEatableCells;
        }
    }

    private final MovementServiceIfc m_movementService;
    private final Random m_random;
}