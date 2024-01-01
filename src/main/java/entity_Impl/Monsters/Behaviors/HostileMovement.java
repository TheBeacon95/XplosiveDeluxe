package entity_Impl.Monsters.Behaviors;

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
public class HostileMovement implements MovementBehaviorIfc {

    public HostileMovement() {
        m_movementService = (MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService);
    }

    @Override
    public Direction getNextMovementDirection(MonsterAbs monster) {
        return m_movementService.getNextHostileDirection(monster.getGlobalPosition(), monster.getDirection());
    }

    private final MovementServiceIfc m_movementService;
}