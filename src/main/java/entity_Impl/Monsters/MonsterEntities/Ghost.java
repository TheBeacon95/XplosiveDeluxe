package entity_Impl.Monsters.MonsterEntities;

import common.Animation;
import common.Coordinates;
import common.Direction;
import entity_Impl.Monsters.Behaviors.HostileMovement;
import entity_Impl.Monsters.MonsterAbs;
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
        super(position);
        initSprites();
        // Todo: create behaviors
        setMovementBehavior(new HostileMovement());
//        setCollisionBehavior(new KillPlayerBehavior());
//        setExplosionBehavior(new DeathOnExplodeBehavior());
    }
    
    // Todo: move this to MovingEntityAbs
    private void initSprites(){
        ArrayList<BufferedImage> idleUpSprites = new ArrayList();
        ArrayList<BufferedImage> idleRightSprites = new ArrayList();
        ArrayList<BufferedImage> idleDownSprites = new ArrayList();
        ArrayList<BufferedImage> idleLeftSprites = new ArrayList();
        try {
            idleUpSprites.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Monsters/Ghost/Skin_0/Ghost_Idle_Up_0.png")));
            idleRightSprites.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Monsters/Ghost/Skin_0/Ghost_Idle_Right_0.png")));
            idleDownSprites.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Monsters/Ghost/Skin_0/Ghost_Idle_Down_0.png")));
            idleLeftSprites.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Monsters/Ghost/Skin_0/Ghost_Idle_Left_0.png")));
        }
        catch (IOException ex) {
            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
        }
        m_idleAnimations.put(Direction.Up, new Animation(idleUpSprites));
        m_idleAnimations.put(Direction.Right, new Animation(idleRightSprites));
        m_idleAnimations.put(Direction.Down, new Animation(idleDownSprites));
        m_idleAnimations.put(Direction.Left, new Animation(idleLeftSprites));
        
        m_movementAnimations.put(Direction.Up, new Animation(idleUpSprites));
        m_movementAnimations.put(Direction.Right, new Animation(idleRightSprites));
        m_movementAnimations.put(Direction.Down, new Animation(idleDownSprites));
        m_movementAnimations.put(Direction.Left, new Animation(idleLeftSprites));
    }
}