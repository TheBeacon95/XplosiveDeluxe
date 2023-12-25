package entity_Impl.Monsters.MonsterEntities;

import common.Animation;
import common.Coordinates;
import common.Direction;
import entity_Impl.Monsters.Behaviors.HostileMovement;
import entity_Impl.Monsters.MonsterAbs;
import entity_Interfaces.MonsterType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yanick
 */
public final class Ghost extends MonsterAbs {

    public Ghost(Coordinates position) {
        super(position, MonsterType.Ghost);
        // Todo: create behaviors
        setMovementBehavior(new HostileMovement());
//        setCollisionBehavior(new KillPlayerBehavior());
//        setExplosionBehavior(new DeathOnExplodeBehavior());
    }
}