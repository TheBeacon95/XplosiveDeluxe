package entity_Impl;

import common.ModuleAbs;
import common.ServiceManager;

public final class ModuleImpl extends ModuleAbs {

    public ModuleImpl() {

    }

    @Override
    protected void initializeAgents() {
        // Do nothing
    }

    @Override
    protected void initializeServices(ServiceManager serviceManager) {
        serviceManager.registerService(new EntityManagementService());
    }
}