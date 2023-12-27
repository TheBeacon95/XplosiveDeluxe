package common;

import java.util.ArrayList;

/**
 *
 * @author Yanick
 */
public abstract class AgentAbs {
    public AgentAbs(){
        m_children = new ArrayList<>();
        m_services = new ArrayList<>();
    }
    
    public final void initializeAgent(){
        m_serviceManager = ServiceManager.getInstance();
        initializeAgents();
        registerServices();
        for (ServiceIfc service: m_services) {
            service.initializeService();
        }
    }
    
    protected final void addAgent(AgentAbs agent) {
        m_children.add(agent);
        agent.initializeAgent();
    }

    public final void start() {
        for (AgentAbs agent: m_children) {
            agent.start();
            agent.onStart();
        }
    }
    
    protected final void registerService(ServiceIfc service) {
        m_services.add(service);
        m_serviceManager.registerService(service);
    }
    
    protected abstract void initializeAgents();
    protected abstract void registerServices();
    protected abstract void onStart();
    
    private ServiceManager m_serviceManager;
    private final ArrayList<AgentAbs> m_children;
    private final ArrayList<ServiceIfc> m_services;
}