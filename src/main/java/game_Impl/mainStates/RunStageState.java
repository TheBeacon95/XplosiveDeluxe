package game_Impl.mainStates;

import common.*;
import entity_Interfaces.*;
import game_Impl.mainStates.GamePanels.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import level_Interfaces.*;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class RunStageState extends UiStateAbs {

    public RunStageState() {
        super(STATE_NAME);
        m_stagePanel = new StagePanel();
        areServicesInitialized = false;
    }

    @Override
    public void enter() {
        initializeServices();
        m_displayService.setPanel(m_stagePanel);

        m_isPauseRequested = false;
        m_inputManagementService.activatePlayerInputs(m_stagePanel);
        m_stagePanel.getInputMap().put(KeyStroke.getKeyStroke("ESC"), "pauseAction");
        m_stagePanel.getActionMap().put("pauseAction", m_pauseAction);
    }

    @Override
    public void run() {
        if (m_stageManagementService.isReady()) {
            m_stageManagementService.updateStage();
            m_entityManagementService.updateEntities();
            m_displayService.draw();
        }
    }

    @Override
    public void exit() {
        m_inputManagementService.activatePlayerInputs(m_stagePanel);
        m_stagePanel.getInputMap().remove(KeyStroke.getKeyStroke("ESC"));
        m_stagePanel.getActionMap().remove("pauseAction");
    }

    public boolean IsPauseRequested() {
        return m_isPauseRequested;
    }

    public boolean areWinOrLoseConditionsMet() {
        return m_stageManagementService.isWin() || m_stageManagementService.isLose();
    }

    public boolean areGameOverConditionsMet() {
        return m_stageManagementService.isGameOver();
    }

    @Override
    protected GamePanelAbs getGamePanel() {
        return m_stagePanel;
    }

    private void initializeServices() {
        if (!areServicesInitialized) {
            m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
            m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
            m_displayService = (DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService);
            m_inputManagementService = (InputManagementServiceIfc) ServiceManager.getService(UiNames.Services.InputManagementService);
            areServicesInitialized = true;
        }
    }

    public static final String STATE_NAME = "RunStageState";

    private StageManagementServiceIfc m_stageManagementService;
    private EntityManagementServiceIfc m_entityManagementService;
    private DisplayServiceIfc m_displayService;
    private InputManagementServiceIfc  m_inputManagementService;
    private boolean areServicesInitialized;  // TODO: remove this. This shouldn't be necessary.

    private final StagePanel m_stagePanel;
    private boolean m_isPauseRequested;

    private final Action m_pauseAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            m_isPauseRequested = true;
        }
    };
}