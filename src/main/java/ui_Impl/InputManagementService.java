package ui_Impl;

import common.PlayerId;
import common.ServiceManager;
import entity_Interfaces.*;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import ui_Impl.MenuActions.*;
import ui_Impl.PlayerActions.*;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class InputManagementService implements InputManagementServiceIfc {

    @Override
    public String getId() {
        return UiNames.Services.InputManagementService;
    }

    @Override
    public void initializeService() {
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
    }

    @Override
    public void loadPlayerInputs() {
        // Todo: Implement this. For now, default keys are used.
        m_player1UpPressed = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        m_player1RightPressed = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        m_player1DownPressed = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        m_player1LeftPressed = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        m_player1FirePressed = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0);

        m_player1UpReleased = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
        m_player1RightReleased = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
        m_player1DownReleased = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
        m_player1LeftReleased = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
        m_player1FireReleased = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0, true);

        m_player2UpPressed = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        m_player2RightPressed = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        m_player2DownPressed = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        m_player2LeftPressed = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        m_player2FirePressed = KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, 0);

        m_player2UpReleased = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true);
        m_player2RightReleased = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true);
        m_player2DownReleased = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true);
        m_player2LeftReleased = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true);
        m_player2FireReleased = KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, 0, true);

        m_player3UpPressed = KeyStroke.getKeyStroke(KeyEvent.VK_I, 0);
        m_player3RightPressed = KeyStroke.getKeyStroke(KeyEvent.VK_L, 0);
        m_player3DownPressed = KeyStroke.getKeyStroke(KeyEvent.VK_K, 0);
        m_player3LeftPressed = KeyStroke.getKeyStroke(KeyEvent.VK_J, 0);
        m_player3FirePressed = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);

        m_player3UpReleased = KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, true);
        m_player3RightReleased = KeyStroke.getKeyStroke(KeyEvent.VK_L, 0, true);
        m_player3DownReleased = KeyStroke.getKeyStroke(KeyEvent.VK_K, 0, true);
        m_player3LeftReleased = KeyStroke.getKeyStroke(KeyEvent.VK_J, 0, true);
        m_player3FireReleased = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0, true);

        m_player4UpPressed = KeyStroke.getKeyStroke(KeyEvent.VK_T, 0);
        m_player4RightPressed = KeyStroke.getKeyStroke(KeyEvent.VK_H, 0);
        m_player4DownPressed = KeyStroke.getKeyStroke(KeyEvent.VK_G, 0);
        m_player4LeftPressed = KeyStroke.getKeyStroke(KeyEvent.VK_F, 0);
        m_player4FirePressed = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);

        m_player4UpReleased = KeyStroke.getKeyStroke(KeyEvent.VK_T, 0, true);
        m_player4RightReleased = KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, true);
        m_player4DownReleased = KeyStroke.getKeyStroke(KeyEvent.VK_G, 0, true);
        m_player4LeftReleased = KeyStroke.getKeyStroke(KeyEvent.VK_F, 0, true);
        m_player4FireReleased = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true);
    }

    @Override
    public void savePlayerInputs() {
        // Todo: Implement this. For now, default keys are used.
    }

    @Override
    public void activatePlayerInputs(JPanel panel) {
        PlayerActionManager manager = new PlayerActionManager();

        //Player 1
        setAction(panel, m_player1UpPressed, UiNames.PlayerInputs.PLAYER1_UP_PRESSED, manager.m_player1UpButtonPressed);
        setAction(panel, m_player1RightPressed, UiNames.PlayerInputs.PLAYER1_RIGHT_PRESSED, manager.m_player1RightButtonPressed);
        setAction(panel, m_player1DownPressed, UiNames.PlayerInputs.PLAYER1_DOWN_PRESSED, manager.m_player1DownButtonPressed);
        setAction(panel, m_player1LeftPressed, UiNames.PlayerInputs.PLAYER1_LEFT_PRESSED, manager.m_player1LeftButtonPressed);
        setAction(panel, m_player1FirePressed, UiNames.PlayerInputs.PLAYER1_FIRE_PRESSED, manager.m_player1FireButtonPressed);

        setAction(panel, m_player1UpReleased, UiNames.PlayerInputs.PLAYER1_UP_RELEASED, manager.m_player1UpButtonReleased);
        setAction(panel, m_player1RightReleased, UiNames.PlayerInputs.PLAYER1_RIGHT_RELEASED, manager.m_player1RightButtonReleased);
        setAction(panel, m_player1DownReleased, UiNames.PlayerInputs.PLAYER1_DOWN_RELEASED, manager.m_player1DownButtonReleased);
        setAction(panel, m_player1LeftReleased, UiNames.PlayerInputs.PLAYER1_LEFT_RELEASED, manager.m_player1LeftButtonReleased);
        setAction(panel, m_player1FireReleased, UiNames.PlayerInputs.PLAYER1_FIRE_RELEASED, manager.m_player1FireButtonReleased);

        m_player1Input = manager.m_player1Input;

        //Player 2
        setAction(panel, m_player2UpPressed, UiNames.PlayerInputs.PLAYER2_UP_PRESSED, manager.m_player2UpButtonPressed);
        setAction(panel, m_player2RightPressed, UiNames.PlayerInputs.PLAYER2_RIGHT_PRESSED, manager.m_player2RightButtonPressed);
        setAction(panel, m_player2DownPressed, UiNames.PlayerInputs.PLAYER2_DOWN_PRESSED, manager.m_player2DownButtonPressed);
        setAction(panel, m_player2LeftPressed, UiNames.PlayerInputs.PLAYER2_LEFT_PRESSED, manager.m_player2LeftButtonPressed);
        setAction(panel, m_player2FirePressed, UiNames.PlayerInputs.PLAYER2_FIRE_PRESSED, manager.m_player2FireButtonPressed);

        setAction(panel, m_player2UpReleased, UiNames.PlayerInputs.PLAYER2_UP_RELEASED, manager.m_player2UpButtonReleased);
        setAction(panel, m_player2RightReleased, UiNames.PlayerInputs.PLAYER2_RIGHT_RELEASED, manager.m_player2RightButtonReleased);
        setAction(panel, m_player2DownReleased, UiNames.PlayerInputs.PLAYER2_DOWN_RELEASED, manager.m_player2DownButtonReleased);
        setAction(panel, m_player2LeftReleased, UiNames.PlayerInputs.PLAYER2_LEFT_RELEASED, manager.m_player2LeftButtonReleased);
        setAction(panel, m_player2FireReleased, UiNames.PlayerInputs.PLAYER2_FIRE_RELEASED, manager.m_player2FireButtonReleased);

        m_player2Input = manager.m_player2Input;

        //Player 3
        setAction(panel, m_player3UpPressed, UiNames.PlayerInputs.PLAYER3_UP_PRESSED, manager.m_player3UpButtonPressed);
        setAction(panel, m_player3RightPressed, UiNames.PlayerInputs.PLAYER3_RIGHT_PRESSED, manager.m_player3RightButtonPressed);
        setAction(panel, m_player3DownPressed, UiNames.PlayerInputs.PLAYER3_DOWN_PRESSED, manager.m_player3DownButtonPressed);
        setAction(panel, m_player3LeftPressed, UiNames.PlayerInputs.PLAYER3_LEFT_PRESSED, manager.m_player3LeftButtonPressed);
        setAction(panel, m_player3FirePressed, UiNames.PlayerInputs.PLAYER3_FIRE_PRESSED, manager.m_player3FireButtonPressed);

        setAction(panel, m_player3UpReleased, UiNames.PlayerInputs.PLAYER3_UP_RELEASED, manager.m_player3UpButtonReleased);
        setAction(panel, m_player3RightReleased, UiNames.PlayerInputs.PLAYER3_RIGHT_RELEASED, manager.m_player3RightButtonReleased);
        setAction(panel, m_player3DownReleased, UiNames.PlayerInputs.PLAYER3_DOWN_RELEASED, manager.m_player3DownButtonReleased);
        setAction(panel, m_player3LeftReleased, UiNames.PlayerInputs.PLAYER3_LEFT_RELEASED, manager.m_player3LeftButtonReleased);
        setAction(panel, m_player3FireReleased, UiNames.PlayerInputs.PLAYER3_FIRE_RELEASED, manager.m_player3FireButtonReleased);

        m_player3Input = manager.m_player3Input;

        //Player 4
        setAction(panel, m_player4UpPressed, UiNames.PlayerInputs.PLAYER4_UP_PRESSED, manager.m_player4UpButtonPressed);
        setAction(panel, m_player4RightPressed, UiNames.PlayerInputs.PLAYER4_RIGHT_PRESSED, manager.m_player4RightButtonPressed);
        setAction(panel, m_player4DownPressed, UiNames.PlayerInputs.PLAYER4_DOWN_PRESSED, manager.m_player4DownButtonPressed);
        setAction(panel, m_player4LeftPressed, UiNames.PlayerInputs.PLAYER4_LEFT_PRESSED, manager.m_player4LeftButtonPressed);
        setAction(panel, m_player4FirePressed, UiNames.PlayerInputs.PLAYER4_FIRE_PRESSED, manager.m_player4FireButtonPressed);

        setAction(panel, m_player4UpReleased, UiNames.PlayerInputs.PLAYER4_UP_RELEASED, manager.m_player4UpButtonReleased);
        setAction(panel, m_player4RightReleased, UiNames.PlayerInputs.PLAYER4_RIGHT_RELEASED, manager.m_player4RightButtonReleased);
        setAction(panel, m_player4DownReleased, UiNames.PlayerInputs.PLAYER4_DOWN_RELEASED, manager.m_player4DownButtonReleased);
        setAction(panel, m_player4LeftReleased, UiNames.PlayerInputs.PLAYER4_LEFT_RELEASED, manager.m_player4LeftButtonReleased);
        setAction(panel, m_player4FireReleased, UiNames.PlayerInputs.PLAYER4_FIRE_RELEASED, manager.m_player4FireButtonReleased);

        m_player4Input = manager.m_player4Input;
        m_entityManagementService.activatePlayerInputs();
    }

    @Override
    public void deactivatePlayerInputs(JPanel panel) {
        //Player 1
        removeAction(panel, m_player1UpPressed, UiNames.PlayerInputs.PLAYER1_UP_PRESSED);
        removeAction(panel, m_player1RightPressed, UiNames.PlayerInputs.PLAYER1_RIGHT_PRESSED);
        removeAction(panel, m_player1DownPressed, UiNames.PlayerInputs.PLAYER1_DOWN_PRESSED);
        removeAction(panel, m_player1LeftPressed, UiNames.PlayerInputs.PLAYER1_LEFT_PRESSED);
        removeAction(panel, m_player1FirePressed, UiNames.PlayerInputs.PLAYER1_FIRE_PRESSED);

        removeAction(panel, m_player1UpReleased, UiNames.PlayerInputs.PLAYER1_UP_RELEASED);
        removeAction(panel, m_player1RightReleased, UiNames.PlayerInputs.PLAYER1_RIGHT_RELEASED);
        removeAction(panel, m_player1DownReleased, UiNames.PlayerInputs.PLAYER1_DOWN_RELEASED);
        removeAction(panel, m_player1LeftReleased, UiNames.PlayerInputs.PLAYER1_LEFT_RELEASED);
        removeAction(panel, m_player1FireReleased, UiNames.PlayerInputs.PLAYER1_FIRE_RELEASED);

        m_player1Input = null;

        //Player 2
        removeAction(panel, m_player2UpPressed, UiNames.PlayerInputs.PLAYER2_UP_PRESSED);
        removeAction(panel, m_player2RightPressed, UiNames.PlayerInputs.PLAYER2_RIGHT_PRESSED);
        removeAction(panel, m_player2DownPressed, UiNames.PlayerInputs.PLAYER2_DOWN_PRESSED);
        removeAction(panel, m_player2LeftPressed, UiNames.PlayerInputs.PLAYER2_LEFT_PRESSED);
        removeAction(panel, m_player2FirePressed, UiNames.PlayerInputs.PLAYER2_FIRE_PRESSED);

        removeAction(panel, m_player2UpReleased, UiNames.PlayerInputs.PLAYER2_UP_RELEASED);
        removeAction(panel, m_player2RightReleased, UiNames.PlayerInputs.PLAYER2_RIGHT_RELEASED);
        removeAction(panel, m_player2DownReleased, UiNames.PlayerInputs.PLAYER2_DOWN_RELEASED);
        removeAction(panel, m_player2LeftReleased, UiNames.PlayerInputs.PLAYER2_LEFT_RELEASED);
        removeAction(panel, m_player2FireReleased, UiNames.PlayerInputs.PLAYER2_FIRE_RELEASED);

        m_player2Input = null;

        //Player 3
        removeAction(panel, m_player3UpPressed, UiNames.PlayerInputs.PLAYER3_UP_PRESSED);
        removeAction(panel, m_player3RightPressed, UiNames.PlayerInputs.PLAYER3_RIGHT_PRESSED);
        removeAction(panel, m_player3DownPressed, UiNames.PlayerInputs.PLAYER3_DOWN_PRESSED);
        removeAction(panel, m_player3LeftPressed, UiNames.PlayerInputs.PLAYER3_LEFT_PRESSED);
        removeAction(panel, m_player3FirePressed, UiNames.PlayerInputs.PLAYER3_FIRE_PRESSED);

        removeAction(panel, m_player3UpReleased, UiNames.PlayerInputs.PLAYER3_UP_RELEASED);
        removeAction(panel, m_player3RightReleased, UiNames.PlayerInputs.PLAYER3_RIGHT_RELEASED);
        removeAction(panel, m_player3DownReleased, UiNames.PlayerInputs.PLAYER3_DOWN_RELEASED);
        removeAction(panel, m_player3LeftReleased, UiNames.PlayerInputs.PLAYER3_LEFT_RELEASED);
        removeAction(panel, m_player3FireReleased, UiNames.PlayerInputs.PLAYER3_FIRE_RELEASED);

        m_player3Input = null;

        //Player 4
        removeAction(panel, m_player4UpPressed, UiNames.PlayerInputs.PLAYER4_UP_PRESSED);
        removeAction(panel, m_player4RightPressed, UiNames.PlayerInputs.PLAYER4_RIGHT_PRESSED);
        removeAction(panel, m_player4DownPressed, UiNames.PlayerInputs.PLAYER4_DOWN_PRESSED);
        removeAction(panel, m_player4LeftPressed, UiNames.PlayerInputs.PLAYER4_LEFT_PRESSED);
        removeAction(panel, m_player4FirePressed, UiNames.PlayerInputs.PLAYER4_FIRE_PRESSED);

        removeAction(panel, m_player4UpReleased, UiNames.PlayerInputs.PLAYER4_UP_RELEASED);
        removeAction(panel, m_player4RightReleased, UiNames.PlayerInputs.PLAYER4_RIGHT_RELEASED);
        removeAction(panel, m_player4DownReleased, UiNames.PlayerInputs.PLAYER4_DOWN_RELEASED);
        removeAction(panel, m_player4LeftReleased, UiNames.PlayerInputs.PLAYER4_LEFT_RELEASED);
        removeAction(panel, m_player4FireReleased, UiNames.PlayerInputs.PLAYER4_FIRE_RELEASED);

        m_player4Input = null;
        m_entityManagementService.activatePlayerInputs();
    }

    @Override
    public void activateMenuInputs(JPanel panel) {
        MenuActionManager manager = new MenuActionManager();

        setAction(panel, m_menuUpPressed, UiNames.MenuInputs.UP_PRESSED, manager.m_menuUpButtonPressed);
        setAction(panel, m_menuRightPressed, UiNames.MenuInputs.RIGHT_PRESSED, manager.m_menuRightButtonPressed);
        setAction(panel, m_menuDownPressed, UiNames.MenuInputs.DOWN_PRESSED, manager.m_menuDownButtonPressed);
        setAction(panel, m_menuLeftPressed, UiNames.MenuInputs.LEFT_PRESSED, manager.m_menuLeftButtonPressed);
        setAction(panel, m_menuFirePressed, UiNames.MenuInputs.SELECT_PRESSED, manager.m_menuFireButtonPressed);

        setAction(panel, m_menuUpReleased, UiNames.MenuInputs.UP_RELEASED, manager.m_menuUpButtonReleased);
        setAction(panel, m_menuRightReleased, UiNames.MenuInputs.RIGHT_RELEASED, manager.m_menuRightButtonReleased);
        setAction(panel, m_menuDownReleased, UiNames.MenuInputs.DOWN_RELEASED, manager.m_menuDownButtonReleased);
        setAction(panel, m_menuLeftReleased, UiNames.MenuInputs.LEFT_RELEASED, manager.m_menuLeftButtonReleased);
        setAction(panel, m_menuFireReleased, UiNames.MenuInputs.SELECT_RELEASED, manager.m_menuFireButtonReleased);
    }

    @Override
    public void deactivateMenuInputs(JPanel panel) {
        removeAction(panel, m_menuUpPressed, UiNames.MenuInputs.UP_PRESSED);
        removeAction(panel, m_menuRightPressed, UiNames.MenuInputs.RIGHT_PRESSED);
        removeAction(panel, m_menuDownPressed, UiNames.MenuInputs.DOWN_PRESSED);
        removeAction(panel, m_menuLeftPressed, UiNames.MenuInputs.LEFT_PRESSED);
        removeAction(panel, m_menuFirePressed, UiNames.MenuInputs.SELECT_PRESSED);

        removeAction(panel, m_menuUpReleased, UiNames.MenuInputs.UP_RELEASED);
        removeAction(panel, m_menuRightReleased, UiNames.MenuInputs.RIGHT_RELEASED);
        removeAction(panel, m_menuDownReleased, UiNames.MenuInputs.DOWN_RELEASED);
        removeAction(panel, m_menuLeftReleased, UiNames.MenuInputs.LEFT_RELEASED);
        removeAction(panel, m_menuFireReleased, UiNames.MenuInputs.SELECT_RELEASED);
    }

    @Override
    public PlayerInput getPlayerInput(PlayerId playerId) {
        return switch(playerId){
            case Player_1 -> m_player1Input;
            case Player_2 -> m_player2Input;
            case Player_3 -> m_player3Input;
            case Player_4 -> m_player4Input;
        };
    }

    @Override
    public MenuInput getPressedMenuButtons() {
        return m_menuInput;
    }

    private void setAction(JPanel panel, KeyStroke keyStroke, String actionId, AbstractAction action) {
        panel.getInputMap().put(keyStroke, actionId);
        panel.getActionMap().put(actionId, action);
    }

    private void removeAction(JPanel panel, KeyStroke keyStroke, String actionId) {
        panel.getInputMap().remove(keyStroke);
        panel.getActionMap().remove(actionId);
    }

    private EntityManagementServiceIfc m_entityManagementService;

    // Todo: Replace this and have it be read and written to / from JSON
    // Menu
    private MenuInput m_menuInput;

    private KeyStroke m_menuUpPressed;
    private KeyStroke m_menuRightPressed;
    private KeyStroke m_menuDownPressed;
    private KeyStroke m_menuLeftPressed;
    private KeyStroke m_menuFirePressed;

    private KeyStroke m_menuUpReleased;
    private KeyStroke m_menuRightReleased;
    private KeyStroke m_menuDownReleased;
    private KeyStroke m_menuLeftReleased;
    private KeyStroke m_menuFireReleased;

    // Player 1
    private PlayerInput m_player1Input;

    private KeyStroke m_player1UpPressed;
    private KeyStroke m_player1RightPressed;
    private KeyStroke m_player1DownPressed;
    private KeyStroke m_player1LeftPressed;
    private KeyStroke m_player1FirePressed;

    private KeyStroke m_player1UpReleased;
    private KeyStroke m_player1RightReleased;
    private KeyStroke m_player1DownReleased;
    private KeyStroke m_player1LeftReleased;
    private KeyStroke m_player1FireReleased;

    // Player 2
    private PlayerInput m_player2Input;

    private KeyStroke m_player2UpPressed;
    private KeyStroke m_player2RightPressed;
    private KeyStroke m_player2DownPressed;
    private KeyStroke m_player2LeftPressed;
    private KeyStroke m_player2FirePressed;

    private KeyStroke m_player2UpReleased;
    private KeyStroke m_player2RightReleased;
    private KeyStroke m_player2DownReleased;
    private KeyStroke m_player2LeftReleased;
    private KeyStroke m_player2FireReleased;

    // Player 3
    private PlayerInput m_player3Input;

    private KeyStroke m_player3UpPressed;
    private KeyStroke m_player3RightPressed;
    private KeyStroke m_player3DownPressed;
    private KeyStroke m_player3LeftPressed;
    private KeyStroke m_player3FirePressed;

    private KeyStroke m_player3UpReleased;
    private KeyStroke m_player3RightReleased;
    private KeyStroke m_player3DownReleased;
    private KeyStroke m_player3LeftReleased;
    private KeyStroke m_player3FireReleased;

    // Player 4
    private PlayerInput m_player4Input;

    private KeyStroke m_player4UpPressed;
    private KeyStroke m_player4RightPressed;
    private KeyStroke m_player4DownPressed;
    private KeyStroke m_player4LeftPressed;
    private KeyStroke m_player4FirePressed;

    private KeyStroke m_player4UpReleased;
    private KeyStroke m_player4RightReleased;
    private KeyStroke m_player4DownReleased;
    private KeyStroke m_player4LeftReleased;
    private KeyStroke m_player4FireReleased;
}