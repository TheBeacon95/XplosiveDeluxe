package entity_Impl.Monsters.Behaviors.MovementBehaviors;

import common.Coordinates;
import level_Interfaces.LevelNames;
import common.*;
import entity_Impl.Monsters.MonsterAbs;
import java.util.List;
import java.util.Random;
import level_Interfaces.MovementServiceIfc;

/**
 * Hostile movement is for entities that chase the nearest player.
 *
 * @author Yanick
 */
public class RandomMovement extends MovementBehaviorAbs {

    public RandomMovement() {
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
        m_random = new Random();
    }

    @Override
    public Direction getNextMovementDirection(MonsterAbs monster) {
        Coordinates globalPosition = monster.getGlobalPosition();
        Coordinates gridPosition = monster.getGridPosition();
        Direction newDirection;
        if (m_movementService.isBetweenCells(globalPosition)) {
            newDirection = monster.getDirection();
        }
        else {
            List<Coordinates> nextFreeCells = getNextFreeCells(gridPosition, monster.getDirection());
            if (nextFreeCells.isEmpty()) {
                newDirection = Direction.NoDirection;
            }
            else {
                newDirection = monster.getGridPosition().getDirection(nextFreeCells.get(m_random.nextInt(nextFreeCells.size())));
            }
        }
        return newDirection;
    }

    private final MovementServiceIfc m_movementService;
    private final Random m_random;
}