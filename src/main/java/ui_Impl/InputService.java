package ui_Impl;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import level_Interfaces.Level;
import ui_Interfaces.InputServiceIfc;
import ui_Interfaces.KeyHandlerIfc;
import ui_Interfaces.UiNames;

/**
 *
 * @author Yanick
 */
public class InputService implements InputServiceIfc {

    public InputService() {
        m_playerHandlers = new HashMap<>();
        m_playerHandlers.put("Player_1", new KeyHandler());
        m_playerHandlers.put("Player_2", new KeyHandler());
        m_playerHandlers.put("Player_3", new KeyHandler());
        m_playerHandlers.put("Player_4", new KeyHandler());
    }

    @Override
    public void loadKeyInputs() {
        m_playerHandlers.clear();
        FileInputStream fileInputStream;
        try {
            for (int i = 1; i <= 4; i++) {
                fileInputStream = new FileInputStream("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\KeyHandlers\\Player_" + i + ".xpd");
                KeyHandler keyHandler = null;
                try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                    keyHandler = (KeyHandler) objectInputStream.readObject();
                    m_playerHandlers.put("Player_" + i, keyHandler);
                }
            }
        }
        catch (Exception ex) {
            m_playerHandlers.clear();
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public KeyHandlerIfc getInput(String playerId) {
        if (m_playerHandlers.size() != 4) {
            return null;
        }
        return m_playerHandlers.get(playerId);
    }

    @Override
    public List<KeyHandlerIfc> getAllPlayerInputs() {
        if (m_playerHandlers.size() != 4) {
            return null;
        }
        return new ArrayList<>(m_playerHandlers.values());
    }

    @Override
    public void initializeService() {
        // Do nothing
    }

    @Override
    public String getId() {
        return UiNames.Services.InputService;
    }

    private final HashMap<String, KeyHandler> m_playerHandlers;
}
