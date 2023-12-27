package game_Impl;

import common.ModuleAbs;

public final class ModuleImpl extends ModuleAbs {

    public ModuleImpl() {
    }
    
    @Override
    protected void initializeAgents() {
        addAgent(new GameManagerAgent());
    }

    @Override
    protected void onStart() {
        // Do nothing.
    }

    @Override
    protected void registerServices() {
//        registerService(new GameManagementService());
    }
}