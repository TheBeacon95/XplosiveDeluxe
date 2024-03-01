package ui_Impl;

import common.*;
import java.util.HashMap;
import ui_Interfaces.AnimationManagementServiceIfc;
import ui_Interfaces.UiNames;

/**
 * This agent is responsible for creating and updating the game frame.
 *
 * @author Yanick
 */
public final class AnimationManagementService implements AnimationManagementServiceIfc {

    public AnimationManagementService() {
        m_animations = new HashMap<>();
    }

    @Override
    public AnimationIfc getAnimation(String animationId) {
        if (animationId == null || animationId.isEmpty() || !m_animations.containsKey(animationId)) {
            return null;
        }
        else {
            return m_animations.get(animationId);
        }
    }

    @Override
    public void setAnimation(String animationId, AnimationIfc animation) {
        if (animationId == null || animationId.isEmpty() || m_animations.containsKey(animationId)) {
            return;
        }

        m_animations.put(animationId, animation);
    }

    @Override
    public String getId() {
        return UiNames.Services.AnimationManagementService;
    }

    @Override
    public void initializeService() {
        // Do nothing.
    }

    private final HashMap<String, AnimationIfc> m_animations;
}