package ui_Impl.PlayerActions;

import ui_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class PlayerActionManager {

    public PlayerActionManager() {
        m_player1Input = new PlayerInput();
        m_player1UpButtonPressed = new UpButtonPressed(m_player1Input);
        m_player1UpButtonReleased = new UpButtonReleased(m_player1Input);
        m_player1RightButtonPressed = new RightButtonPressed(m_player1Input);
        m_player1RightButtonReleased = new RightButtonReleased(m_player1Input);
        m_player1DownButtonPressed = new DownButtonPressed(m_player1Input);
        m_player1DownButtonReleased = new DownButtonReleased(m_player1Input);
        m_player1LeftButtonPressed = new LeftButtonPressed(m_player1Input);
        m_player1LeftButtonReleased = new LeftButtonReleased(m_player1Input);
        m_player1FireButtonPressed = new FireButtonPressed(m_player1Input);
        m_player1FireButtonReleased = new FireButtonReleased(m_player1Input);

        m_player2Input = new PlayerInput();
        m_player2UpButtonPressed = new UpButtonPressed(m_player2Input);
        m_player2UpButtonReleased = new UpButtonReleased(m_player2Input);
        m_player2RightButtonPressed = new RightButtonPressed(m_player2Input);
        m_player2RightButtonReleased = new RightButtonReleased(m_player2Input);
        m_player2DownButtonPressed = new DownButtonPressed(m_player2Input);
        m_player2DownButtonReleased = new DownButtonReleased(m_player2Input);
        m_player2LeftButtonPressed = new LeftButtonPressed(m_player2Input);
        m_player2LeftButtonReleased = new LeftButtonReleased(m_player2Input);
        m_player2FireButtonPressed = new FireButtonPressed(m_player2Input);
        m_player2FireButtonReleased = new FireButtonReleased(m_player2Input);

        m_player3Input = new PlayerInput();
        m_player3UpButtonPressed = new UpButtonPressed(m_player3Input);
        m_player3UpButtonReleased = new UpButtonReleased(m_player3Input);
        m_player3RightButtonPressed = new RightButtonPressed(m_player3Input);
        m_player3RightButtonReleased = new RightButtonReleased(m_player3Input);
        m_player3DownButtonPressed = new DownButtonPressed(m_player3Input);
        m_player3DownButtonReleased = new DownButtonReleased(m_player3Input);
        m_player3LeftButtonPressed = new LeftButtonPressed(m_player3Input);
        m_player3LeftButtonReleased = new LeftButtonReleased(m_player3Input);
        m_player3FireButtonPressed = new FireButtonPressed(m_player3Input);
        m_player3FireButtonReleased = new FireButtonReleased(m_player3Input);

        m_player4Input = new PlayerInput();
        m_player4UpButtonPressed = new UpButtonPressed(m_player4Input);
        m_player4UpButtonReleased = new UpButtonReleased(m_player4Input);
        m_player4RightButtonPressed = new RightButtonPressed(m_player4Input);
        m_player4RightButtonReleased = new RightButtonReleased(m_player4Input);
        m_player4DownButtonPressed = new DownButtonPressed(m_player4Input);
        m_player4DownButtonReleased = new DownButtonReleased(m_player4Input);
        m_player4LeftButtonPressed = new LeftButtonPressed(m_player4Input);
        m_player4LeftButtonReleased = new LeftButtonReleased(m_player4Input);
        m_player4FireButtonPressed = new FireButtonPressed(m_player4Input);
        m_player4FireButtonReleased = new FireButtonReleased(m_player4Input);
    }

    public final PlayerInput m_player1Input;
    public final UpButtonPressed m_player1UpButtonPressed;
    public final UpButtonReleased m_player1UpButtonReleased;
    public final RightButtonPressed m_player1RightButtonPressed;
    public final RightButtonReleased m_player1RightButtonReleased;
    public final DownButtonPressed m_player1DownButtonPressed;
    public final DownButtonReleased m_player1DownButtonReleased;
    public final LeftButtonPressed m_player1LeftButtonPressed;
    public final LeftButtonReleased m_player1LeftButtonReleased;
    public final FireButtonPressed m_player1FireButtonPressed;
    public final FireButtonReleased m_player1FireButtonReleased;

    public final PlayerInput m_player2Input;
    public final UpButtonPressed m_player2UpButtonPressed;
    public final UpButtonReleased m_player2UpButtonReleased;
    public final RightButtonPressed m_player2RightButtonPressed;
    public final RightButtonReleased m_player2RightButtonReleased;
    public final DownButtonPressed m_player2DownButtonPressed;
    public final DownButtonReleased m_player2DownButtonReleased;
    public final LeftButtonPressed m_player2LeftButtonPressed;
    public final LeftButtonReleased m_player2LeftButtonReleased;
    public final FireButtonPressed m_player2FireButtonPressed;
    public final FireButtonReleased m_player2FireButtonReleased;

    public final PlayerInput m_player3Input;
    public final UpButtonPressed m_player3UpButtonPressed;
    public final UpButtonReleased m_player3UpButtonReleased;
    public final RightButtonPressed m_player3RightButtonPressed;
    public final RightButtonReleased m_player3RightButtonReleased;
    public final DownButtonPressed m_player3DownButtonPressed;
    public final DownButtonReleased m_player3DownButtonReleased;
    public final LeftButtonPressed m_player3LeftButtonPressed;
    public final LeftButtonReleased m_player3LeftButtonReleased;
    public final FireButtonPressed m_player3FireButtonPressed;
    public final FireButtonReleased m_player3FireButtonReleased;

    public final PlayerInput m_player4Input;
    public final UpButtonPressed m_player4UpButtonPressed;
    public final UpButtonReleased m_player4UpButtonReleased;
    public final RightButtonPressed m_player4RightButtonPressed;
    public final RightButtonReleased m_player4RightButtonReleased;
    public final DownButtonPressed m_player4DownButtonPressed;
    public final DownButtonReleased m_player4DownButtonReleased;
    public final LeftButtonPressed m_player4LeftButtonPressed;
    public final LeftButtonReleased m_player4LeftButtonReleased;
    public final FireButtonPressed m_player4FireButtonPressed;
    public final FireButtonReleased m_player4FireButtonReleased;
}