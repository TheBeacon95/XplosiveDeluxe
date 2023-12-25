package common;

public abstract class ModuleAbs extends AgentAbs {

    public ModuleAbs() {
        // Nothing to do here.
    }

    @Override
    public final void start() {
        for (AgentAbs agent: m_children) {
            agent.start();
        }
    }
}