package entity_Impl.Monsters.MonsterEntities;

import common.Coordinates;
import common.ServiceManager;
import entity_Impl.Monsters.Behaviors.CollisionBehaviors.KillPlayerBehavior;
import entity_Impl.Monsters.Behaviors.ExplosionBehaviors.DieBehavior;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.HostileMovement;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.*;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public final class Fuzzy extends MonsterAbs {

    public Fuzzy(Coordinates position) {
        super(position, MonsterType.Fuzzy);
        // Todo: create behaviors
        setMovementBehavior(new HostileMovement());
        setCollisionBehavior(new KillPlayerBehavior());
        setExplosionBehavior(new DieBehavior());
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
        int blockSegments = ((MovementServiceIfc) ServiceManager.getService(LevelNames.Services.MovementService)).getBlockSegments();
        THRESHHOLD = 3.5f * blockSegments;
    }

    @Override
    protected Speed getSpeed() {
        float distanceToClosestPlayer = Coordinates.getDistance(m_globalPosition, m_entityManagementService.getClosestPlayerPosition(m_globalPosition));
        return distanceToClosestPlayer > THRESHHOLD ? Speed.NormalMonster : Speed.DefaultPlayer;
    }
    
    private final EntityManagementServiceIfc m_entityManagementService;
    private final float THRESHHOLD;
}