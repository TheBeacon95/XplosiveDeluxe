package entity_Impl.Players;

import common.*;
import entity_Impl.*;
import entity_Interfaces.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yanick
 */
public class PlayerAnimationGroup extends AnimationGroupAbs {
    private final PlayerIfc m_player;
    private final PlayerStatusIfc m_playerStatus;

    public PlayerAnimationGroup(PlayerIfc player) {
        m_player = player;
        m_playerStatus = player.getStatus();
    }

    public void setAnimationGroup(PlayerEffect effect, MovingEntityAnimationGroup animationGroup) {
        add(effect.name(), animationGroup);
    }

    @Override
    public BufferedImage getSpriteToDraw() {
        return get(m_playerStatus.getEffect().name()).getSpriteToDraw();
    }

    @Override
    public void setSprites(String folderPath) {
        //"Sprites/Players/Skin_0/"
        for (PlayerEffect effect: PlayerEffect.values()) {
            if (!hasAnimation(effect)) {
                continue;
            }

            MovingEntityAnimationGroup animationGroup = new MovingEntityAnimationGroup((MovingEntityAbs) m_player);
            setAnimationGroup(effect, animationGroup);
        }

        // TODO: implement adding animations to the service.
//        m_animationManagementService.setAnimation(GROUP_NAME + "_" + folderPath, this);
    }

    @Override
    public MovingEntityAnimationGroup copy() {
        // TODO: implement adding animations to the service.
        return null;
    }

    private boolean hasAnimation(PlayerEffect effect) {
        return switch (effect) {
            case None -> true;
            case Shield -> true;
            case Slow -> false;
            case Ghost -> true;
            case Nausea -> true;
            case BrickMaker -> false;
            case BombCrazy -> false;
            case FirePower -> true;
        };
    }
}