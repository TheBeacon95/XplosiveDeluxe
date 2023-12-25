package game_Impl.gameStates;

import common.stateMachine.StateAbs;
import entity_Interfaces.MonsterType;
import game_Interfaces.ActivePlayers;
import game_Interfaces.Difficulty;
import game_Interfaces.GameMode;
import java.util.EnumSet;

// Todo: Finish implementing the menu.

/**
 * Shows the game menu.
 * @author Yanick
 */
public final class ShowMenuState extends StateAbs {
    
    public ShowMenuState() {
        super(STATE_NAME);
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
        
    }
    
    private static final String STATE_NAME = "ShowMenuState";
    private MenuSettings m_settings;
    
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