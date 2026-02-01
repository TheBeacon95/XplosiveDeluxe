package entity_Impl.Players;

import common.*;
import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public final class PlayerKeyHandler {

    public void initialize(PlayerId playerId) {
        m_playerInput = ((InputManagementServiceIfc) ServiceManager.getService(UiNames.Services.InputManagementService)).getPlayerInput(playerId);
    }

    public Direction getPressedDirection() {
        // Todo: change this so that the newest direction is picked.
        Direction direction;
        if (m_playerInput.isUpPressed) {
            direction = Direction.Up;
        }
        else if (m_playerInput.isRightPressed) {
            direction = Direction.Right;
        }
        else if (m_playerInput.isDownPressed) {
            direction = Direction.Down;
        }
        else if (m_playerInput.isLeftPressed) {
            direction = Direction.Left;
        }
        else {
            direction = Direction.NoDirection;
        }
        return direction;
    }

    public boolean isFirePressed() {
        return m_playerInput.isFirePressed;
    }

    private PlayerInput m_playerInput;
}