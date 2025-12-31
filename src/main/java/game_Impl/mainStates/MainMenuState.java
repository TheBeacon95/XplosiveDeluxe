package game_Impl.mainStates;

import ui_Interfaces.MenuInput;
import entity_Interfaces.MonsterType;
import game_Impl.*;
import static ui_Interfaces.MenuInput.Down;
import static ui_Interfaces.MenuInput.None;
import static ui_Interfaces.MenuInput.Select;
import static ui_Interfaces.MenuInput.Up;
import game_Impl.mainStates.GamePanels.*;
import game_Interfaces.*;
import java.util.EnumSet;
import ui_Interfaces.GamePanelAbs;

// Todo: Finish implementing the menu.

/**
 * Shows the game menu.
 * @author Yanick
 */
public final class MainMenuState extends UiStateAbs {

    public MainMenuState() {
        super(STATE_NAME);
        m_menuPanel = new MainMenuPanel();
    }

    @Override
    public void enter() {
        // Load the menu settings from the last session.
        m_settings = MenuSettings.readLastSettings();
        if (m_settings == null) {
            m_settings = MenuSettings.defaultSettings();
        }
    }

    @Override
    public void run() {
        handleInput();
        updatePanel();
    }

    public boolean isStartGameSelected() {
        return m_isStartGameSelected;
    }

    @Override
    protected GamePanelAbs getGamePanel() {
        return m_menuPanel;
    }

    private void handleInput() {
        MenuInput input = m_menuInput.getCurrentInput();
        switch (input) {
            case Down -> m_menuSelection.down();
            case Up -> m_menuSelection.up();
            case Select -> onSelectClicked();
            case None -> {}
        }
    }

    private void updatePanel() {

    }

    private void onSelectClicked() {
        
    }

    private static final String STATE_NAME = "MainMenuState";
    private MenuSettings m_settings;
    private MenuKeyHandler m_menuInput;
    private final MainMenuPanel m_menuPanel;
    private boolean m_isStartGameSelected;
    private MenuSelection m_menuSelection;

    private enum MenuSelection {
        Players,
        Lives,
        LetsGo,
        Quit;

        public MenuSelection down() {
            return switch (this) {
                case Players -> Lives;
                case Lives -> LetsGo;
                case LetsGo -> Quit;
                case Quit -> Players;
            };
        }

        public MenuSelection up() {
            return switch (this) {
                case Players -> Quit;
                case Lives -> Players;
                case LetsGo -> Lives;
                case Quit -> LetsGo;
            };
        }
    }

    private static class MenuSettings {

        private MenuSettings() {
            m_activePlayers = EnumSet.noneOf(ActivePlayers.class);
            m_activeMonsters = EnumSet.noneOf(MonsterType.class);
        }

        static MenuSettings defaultSettings() {
            MenuSettings settings = new MenuSettings();

            settings.m_gameMode = GameMode.Adventure;
            settings.m_activePlayers.add(ActivePlayers.Player1Active);
            settings.m_lifeCount = 5;
            settings.m_difficulty = Difficulty.Advanced;
            settings.m_lastLevelId = "";
            settings.m_blockDensity = 4;

            return settings;
        }

        static MenuSettings readLastSettings() {
            // Todo: implement
            return null;
        }

        GameMode m_gameMode;

        // General settings
        EnumSet<ActivePlayers> m_activePlayers;

        int m_lifeCount;

        // Adventure Settings
        Difficulty m_difficulty;
        String m_lastLevelId;

        // Combative Settings
        int m_blockDensity;
        EnumSet<MonsterType> m_activeMonsters;
    }
}