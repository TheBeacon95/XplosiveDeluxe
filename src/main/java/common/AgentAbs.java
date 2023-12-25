package common;

import java.util.ArrayList;

/**
 *
 * @author Yanick
 */
public abstract class AgentAbs {
    public AgentAbs(){
        m_children = new ArrayList<>();
    }
    
    public final void initializeAgent(){
        initializeAgents();
        initializeServices(ServiceManager.getInstance());
    }
    
    protected final void addAgent(AgentAbs agent) {
        m_children.add(agent);
        agent.initializeAgent();
    }
    
    protected abstract void initializeAgents();
    protected abstract void initializeServices(ServiceManager serviceManager);
    protected abstract void start();
    
    protected final ArrayList<AgentAbs> m_children;
}