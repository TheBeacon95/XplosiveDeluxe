package ui_Impl;

import common.ServiceManager;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Logger;
import level_Interfaces.Level;
import ui_Interfaces.DisplayServiceIfc;
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
                    ((DisplayServiceIfc) ServiceManager.getService(UiNames.Services.DisplayService)).attachKeyHandler(keyHandler);
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
    public void initializeService() {
//        KeyHandler keyHandler1 = new KeyHandler();
//        keyHandler1.setUpKey(KeyEvent.VK_UP);
//        keyHandler1.setRightKey(KeyEvent.VK_RIGHT);
//        keyHandler1.setDownKey(KeyEvent.VK_DOWN);
//        keyHandler1.setLeftKey(KeyEvent.VK_LEFT);
//        keyHandler1.setFireKey(KeyEvent.VK_INSERT);
//        
//        KeyHandler keyHandler2 = new KeyHandler();
//        keyHandler2.setUpKey(KeyEvent.VK_W);
//        keyHandler2.setRightKey(KeyEvent.VK_D);
//        keyHandler2.setDownKey(KeyEvent.VK_S);
//        keyHandler2.setLeftKey(KeyEvent.VK_A);
//        keyHandler2.setFireKey(KeyEvent.VK_SHIFT);
//        
//        KeyHandler keyHandler3 = new KeyHandler();
//        keyHandler3.setUpKey(KeyEvent.VK_I);
//        keyHandler3.setRightKey(KeyEvent.VK_L);
//        keyHandler3.setDownKey(KeyEvent.VK_K);
//        keyHandler3.setLeftKey(KeyEvent.VK_J);
//        keyHandler3.setFireKey(KeyEvent.VK_BACK_SPACE);
//        
//        KeyHandler keyHandler4 = new KeyHandler();
//        keyHandler4.setUpKey(KeyEvent.VK_G);
//        keyHandler4.setRightKey(KeyEvent.VK_N);
//        keyHandler4.setDownKey(KeyEvent.VK_B);
//        keyHandler4.setLeftKey(KeyEvent.VK_V);
//        keyHandler4.setFireKey(KeyEvent.VK_SPACE);
//        
//        FileOutputStream outputStream1;
//        FileOutputStream outputStream2;
//        FileOutputStream outputStream3;
//        FileOutputStream outputStream4;
//        try {
//            outputStream1 = new FileOutputStream("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\KeyHandlers\\Player_1.xpd");
//            outputStream2 = new FileOutputStream("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\KeyHandlers\\Player_2.xpd");
//            outputStream3 = new FileOutputStream("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\KeyHandlers\\Player_3.xpd");
//            outputStream4 = new FileOutputStream("C:\\Users\\Yanick\\GitHub\\XplosiveDeluxe\\src\\main\\resources\\KeyHandlers\\Player_4.xpd");
//            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream1)) {
//                objectOutputStream.writeObject(keyHandler1);
//                objectOutputStream.flush();
//            }
//            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream2)) {
//                objectOutputStream.writeObject(keyHandler2);
//                objectOutputStream.flush();
//            }
//            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream3)) {
//                objectOutputStream.writeObject(keyHandler3);
//                objectOutputStream.flush();
//            }
//            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream4)) {
//                objectOutputStream.writeObject(keyHandler4);
//                objectOutputStream.flush();
//            }
//        }
//        catch (Exception ex) {
//            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
    }

    @Override
    public String getId() {
        return UiNames.Services.InputService;
    }

    private final HashMap<String, KeyHandler> m_playerHandlers;
}
