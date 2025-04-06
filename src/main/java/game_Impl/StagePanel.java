package game_Impl;

import common.*;
import java.awt.Graphics2D;
import level_Interfaces.*;
import ui_Interfaces.*;

public class StagePanel extends GamePanelAbs {

    private StageManagementServiceIfc m_stageManagementService;

    public StagePanel() {
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        InputServiceIfc inputService = (InputServiceIfc) ServiceManager.getService(UiNames.Services.InputService);
        inputService.loadKeyInputs();
        for (KeyHandlerIfc keyHandler: inputService.getAllPlayerInputs()) {
            addKeyListener(keyHandler);
        }
    }

    @Override
    protected final void onPaintComponent(Graphics2D g2) {
         m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        if (m_stageManagementService.isReady()) {
            m_stageManagementService.draw(g2);
        }
    }
}