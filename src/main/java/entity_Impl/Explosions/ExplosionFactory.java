package entity_Impl.Explosions;

import common.*;
import entity_Impl.*;
import entity_Interfaces.*;
import java.util.ArrayList;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class ExplosionFactory {

    public ExplosionFactory() {
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        Explosion.loadSprites();
    }

    public ArrayList<EntityAbs> createExplosion(Coordinates position, ExplosionType explosionType, int strength) {
        ArrayList<EntityAbs> explosions = new ArrayList<>();
        explosions.add(new Explosion(position, explosionType));
        explosions.addAll(createDirectionalExplosion(position, Direction.Up, strength, explosionType));
        explosions.addAll(createDirectionalExplosion(position, Direction.Right, strength, explosionType));
        explosions.addAll(createDirectionalExplosion(position, Direction.Down, strength, explosionType));
        explosions.addAll(createDirectionalExplosion(position, Direction.Left, strength, explosionType));
        return explosions;
    }

    private ArrayList<EntityAbs> createDirectionalExplosion(Coordinates initialPosition, Direction direction, int strength, ExplosionType explosionType) {
        Coordinates nextPosition = new Coordinates(initialPosition);
        int remainingStrength = strength;
        boolean isExplosionStopper;
        boolean isIndestructable;
        ArrayList<EntityAbs> newExplosions = new ArrayList<>();
        do {
            nextPosition.translate(direction, 1);
            isExplosionStopper = m_stageManagementService.isExplosionStopper(nextPosition);
            isIndestructable = m_stageManagementService.isIndestructible(nextPosition);
            m_stageManagementService.explode(nextPosition);
            remainingStrength--;
            if (!isIndestructable) {
                newExplosions.add(new Explosion(nextPosition, explosionType));
            }
        }
        while (!isExplosionStopper && remainingStrength > 0);

        return newExplosions;
    }

    private final StageManagementServiceIfc m_stageManagementService;
}
