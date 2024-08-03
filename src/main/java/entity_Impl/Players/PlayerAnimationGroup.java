package entity_Impl.Players;

import common.*;
import entity_Impl.*;
import entity_Interfaces.*;
import java.awt.image.BufferedImage;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class PlayerAnimationGroup extends AnimationGroupAbs {
    private PlayerIfc m_player;
    private PlayerStatusIfc m_playerStatus;
    private final static String GROUP_NAME = "PlayerAnimationGroup";
    private final AnimationManagementServiceIfc m_animationManagementService;

    public PlayerAnimationGroup() {
        m_animationManagementService = ((AnimationManagementServiceIfc) ServiceManager.getService(UiNames.Services.AnimationManagementService));
    }

    public void attachPlayer(PlayerIfc player) {
        m_player = player;
        m_playerStatus = player.getStatus();
    }

    private void setAnimationGroup(PlayerEffect effect, EntityAnimationGroup animationGroup) {
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

            EntityAnimationGroup animationGroup = new EntityAnimationGroup();
//            animationGroup.attachPlayer((MovingEntityAbs) m_player);
            animationGroup.setSprites(folderPath + "/" + effect.name());
            setAnimationGroup(effect, animationGroup);
        }

        m_animationManagementService.setAnimation(GROUP_NAME + "_" + folderPath, copy());
    }

    @Override
    public PlayerAnimationGroup copy() {
        PlayerAnimationGroup copy = new PlayerAnimationGroup();
        insertCopies(copy);
        return copy;
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