package level_Impl;

import common.ModuleAbs;

public final class ModuleImpl extends ModuleAbs {

    public ModuleImpl() {

    }

    @Override
    protected void initializeAgents() {
        // Do nothing
    }

    @Override
    protected void onStart() {
        // Do nothing.
    }

    @Override
    protected void registerServices() {
        registerService(new StageManagementService());
        registerService(new MovementService());
//        registerService(new LevelManagementService());
    }
}