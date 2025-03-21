package common;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public abstract class AnimationGroupAbs implements AnimationIfc {

    public AnimationGroupAbs() {
        m_animationMap = new HashMap<>();
        m_animationManagementService = (AnimationManagementServiceIfc) ServiceManager.getService(UiNames.Services.AnimationManagementService);
    }

    public void configureAnimation(String name, String animationID) {
        AnimationIfc animariotion = m_animationManagementService.getAnimation(animationID);
    }

    public abstract void configureAnimation(String filePath);

    @Override
    public abstract BufferedImage getSpriteToDraw();

    private AnimationIfc m_currentAnimation;
    private HashMap<String, AnimationIfc> m_animationMap;
    private final AnimationManagementServiceIfc m_animationManagementService;
}