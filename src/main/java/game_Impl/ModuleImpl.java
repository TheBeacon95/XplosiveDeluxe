package game_Impl;

import common.ModuleAbs;
import common.ServiceManager;
import game_Interfaces.GameManagementServiceIfc;

public final class ModuleImpl extends ModuleAbs {

    public ModuleImpl() {
    }
    
    @Override
    protected void initializeAgents() {
        addAgent(new GameManagerAgent());
    }

    @Override
    protected void initializeServices(ServiceManager serviceManager) {
//        serviceManager.registerService(new GameManagementService());
    }
}