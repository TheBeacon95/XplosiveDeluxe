package ui_Impl;

import common.ModuleAbs;

public final class ModuleImpl extends ModuleAbs {

    public ModuleImpl() {
    }
    
    @Override
    protected void initializeAgents() {
        // Do nothing.
    }

    @Override
    protected void onStart() {
        // Do nothing.
    }

    @Override
    protected void registerServices() {
        registerService(new ScreenService());
        registerService(new DisplayService());
        registerService(new InputService());
    }
}