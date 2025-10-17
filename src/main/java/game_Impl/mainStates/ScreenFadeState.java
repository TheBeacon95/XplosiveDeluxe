package game_Impl.mainStates;

import common.*;
import common.stateMachine.*;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class ScreenFadeState extends StateAbs {

    public static ScreenFadeState createFadeInState(UiStateAbs uiState) {
        return new ScreenFadeState(true, uiState);
    }

    public static ScreenFadeState createFadeOutState() {
        return new ScreenFadeState(false, null);
    }

    @Override
    public void enter() {
        m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);
        m_uiState.setAsNextPanel();
        m_startTime = System.nanoTime();
    }

    @Override
    public void run() {
        float opacityLevel = (float)((System.nanoTime() - m_startTime)) / SCREEN_FADE_TIME;
        if (!m_isWarmup) {
            opacityLevel = 1f - opacityLevel;
        }
        m_displayService.setOpacityLevel(opacityLevel);
        m_displayService.draw();
    }

    @Override
    public void exit() {
        m_displayService.setOpacityLevel(m_isWarmup ? 1f : 0f);
        m_displayService.draw();
    }

    public boolean isFadeDone() {
        return m_startTime + SCREEN_FADE_TIME <= System.nanoTime();
    }

    private ScreenFadeState(boolean isWarmup, UiStateAbs uiState) {
        super(STATE_NAME);
        m_isWarmup = isWarmup;
        m_uiState = uiState;
    }

    private final UiStateAbs m_uiState;
    private final boolean m_isWarmup;
    private long m_startTime;
    private DisplayServiceIfc m_displayService;
    private static final String STATE_NAME = "WarmupState";
    private static final long SCREEN_FADE_TIME = 2l * 1000 * 1000 * 1000;
}