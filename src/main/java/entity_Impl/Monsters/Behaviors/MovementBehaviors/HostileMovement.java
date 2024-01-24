package entity_Impl.Monsters.Behaviors.MovementBehaviors;

import common.Coordinates;
import level_Interfaces.LevelNames;
import common.Direction;
import common.ServiceManager;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.*;
import java.util.List;
import level_Interfaces.MovementServiceIfc;

/**
 * Hostile movement is for entities that chase the nearest player.
 *
 * @author Yanick
 */
public class HostileMovement implements MovementBehaviorIfc {

    public HostileMovement() {
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
    }

    @Override
    public final Direction getNextMovementDirection(MonsterAbs monster) {
        Coordinates globalPosition = monster.getGlobalPosition();
        Coordinates gridPosition = monster.getGridPosition();
        Direction newDirection = Direction.NoDirection;
        int blockSegments = m_movementService.getBlockSegments();
        if (m_movementService.isBetweenCells(globalPosition)) {
            newDirection = monster.getDirection();
        }
        else {
            List<Coordinates> nextFreeCells = getNextFreeCells(gridPosition, monster.getDirection());
            float distance = Float.MAX_VALUE;
            Coordinates closestPlayerPosition = m_entityManagementService.getClosestPlayerPosition(globalPosition);
            for (Coordinates cell : nextFreeCells) {
                Coordinates globalCellPosition = new Coordinates(cell.x * blockSegments, cell.y * blockSegments);
                float distanceToNewCell = Coordinates.getDistance(globalCellPosition, closestPlayerPosition);
                if (distance > distanceToNewCell) {
                    newDirection = monster.getGridPosition().getDirection(cell);
                    distance = distanceToNewCell;
                }
            }
        }
        return newDirection;
    }

    protected List<Coordinates> getNextFreeCells(Coordinates gridPosition, Direction direction) {
        List<Coordinates> nextFreeCells = m_movementService.getAllFreeNeighboringCells(gridPosition);
        if (nextFreeCells.size() > 1) {
            // Todo: remove this. The MovementBehaviorIfc is in entity_Impl.
            nextFreeCells = MovementBehaviorIfc.eliminateLastCell(nextFreeCells, gridPosition, direction);
        }
        return nextFreeCells;
    }
    
    private final MovementServiceIfc m_movementService;
    private final EntityManagementServiceIfc m_entityManagementService;
}