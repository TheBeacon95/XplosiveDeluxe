package entity_Impl.Monsters.Behaviors;

import level_Interfaces.LevelNames;
import level_Interfaces.StageManagementServiceIfc;
import common.Coordinates;
import common.Direction;
import common.ServiceManager;
import entity_Impl.Monsters.MonsterAbs;
import java.util.List;

/**
 * Hostile movement is for entities that chase the nearest player.
 *
 * @author Yanick
 */
public class HostileMovement implements MovementBehaviorIfc {

    public HostileMovement() {
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
    }

    @Override
    public Direction getNextMovementDirection(MonsterAbs monster) {
        Coordinates position = monster.getPosition();
        Direction currentDirection = monster.getDirection();
        Direction newDirection = Direction.Up;
        if (m_stageManagementService != null) {
            boolean isBetweenCells = m_stageManagementService.isBetweenCells(position);

            if (isBetweenCells) {
                newDirection = currentDirection;
            }
            else {
                List<Coordinates> nextCells = m_stageManagementService.getAllFreeNeighboringCells(position);
                nextCells = MovementBehaviorIfc.eliminateLastCell(nextCells, position, currentDirection);
                float distance = Float.MAX_VALUE;
                for (Coordinates cell : nextCells) {
                    float distanceToNewCell = Coordinates.getDistance(position, cell);
                    if (distance > distanceToNewCell) {
                        newDirection = position.getDirection(cell);
                        distance = distanceToNewCell;
                    }
                }
            }
        }
        return newDirection;
    }

    private StageManagementServiceIfc m_stageManagementService;
}