package ui_Impl;

import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class AnimationManagementService implements AnimationManagementServiceIfc {

    @Override
    public void startAnimations() {
        m_areAnimationsActive = true;
    }

    @Override
    public void pauseAllAnimations() {
        m_areAnimationsActive = false;
    }

    @Override
    public void initializeService() {
        m_areAnimationsActive = false;
    }

    @Override
    public String getId() {
        return UiNames.Services.AnimationManagementService;
    }

    @Override
    public boolean areAnimationsActive() {
        return true;
//        return m_areAnimationsActive;
    }

    private boolean m_areAnimationsActive;

}
