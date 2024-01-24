package entity_Impl.Monsters.Behaviors.MovementBehaviors;

import level_Interfaces.LevelNames;
import common.Direction;
import common.ServiceManager;
import entity_Impl.Monsters.MonsterAbs;
import level_Interfaces.MovementServiceIfc;

/**
 * Hostile movement is for entities that chase the nearest player.
 *
 * @author Yanick
 */
public class RandomMovement implements MovementBehaviorIfc {

    public RandomMovement() {
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
    }

    @Override
    public Direction getNextMovementDirection(MonsterAbs monster) {
        return null;
    }

    private final MovementServiceIfc m_movementService;
}