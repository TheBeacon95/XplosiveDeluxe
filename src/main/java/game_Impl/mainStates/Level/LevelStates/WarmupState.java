package game_Impl.mainStates.Level.LevelStates;

import common.*;
import common.stateMachine.*;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class WarmupState extends StateAbs {

    public WarmupState() {
        super(STATE_NAME);
    }

    @Override
    public void enter() {
        m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);
        m_animationManagementService = (AnimationManagementServiceIfc) ServiceManager.getService(UiNames.Services.AnimationManagementService);
        m_startTime = System.nanoTime();
    }

    @Override
    public void run() {
        m_displayService.setOpacityLevel((float)((System.nanoTime() - m_startTime)) / WARMUP_TIME);
        m_displayService.draw();
    }

    @Override
    public void exit() {
        m_displayService.setOpacityLevel(1f);
        m_displayService.draw();
        m_animationManagementService.startAnimations();
    }

    public boolean hasTimerElapsed() {
        return m_startTime + WARMUP_TIME <= System.nanoTime();
    }

    private long m_startTime;
    private DisplayServiceIfc m_displayService;
    private AnimationManagementServiceIfc m_animationManagementService;
    private static final String STATE_NAME = "WarmupState";
    private static final long WARMUP_TIME = 2l * 1000 * 1000 * 1000;
}