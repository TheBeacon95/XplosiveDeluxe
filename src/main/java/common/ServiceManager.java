package common;

import java.util.HashMap;
import java.util.Map;

// TODO everything...
public class ServiceManager {

    ServiceManager() {
        m_services = new HashMap<>();
    }

    public static ServiceManager getInstance() {
        if (s_instance == null) {
            s_instance = new ServiceManager();
        }
        return s_instance;
    }

    public void registerService(ServiceIfc service) {
        String serverId = service.getId();
        try {
            if (m_services.get(serverId) == null) {
                m_services.put(serverId, service);
            }
            else {
                System.err.println(serverId + " was already registered to the ServiceManager.");
            }
        }
        catch (NullPointerException e) {
            System.err.println("serviceName in ServiceManager.registerService cannot be null.");
        }
    }
    
    public static ServiceIfc getService(String serviceName) {
        ServiceIfc service = null;
        try {
            service = s_instance.m_services.get(serviceName);
            if (service == null) {
                System.err.println(serviceName + " was is not registered to the ServiceManager.");
            }
        }
        catch (NullPointerException e) {
            System.err.println("serviceName in ServiceManager.getService cannot be null.");
        }
        return service;
    }

    private Map<String, ServiceIfc> m_services;

    private static ServiceManager s_instance;
}
