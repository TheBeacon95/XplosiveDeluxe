package entity_Impl;

import entity_Impl.Monsters.MonsterFactory;
import common.Coordinates;
import entity_Impl.Collectables.CollectableFactory;
//import entity_Impl.Monsters.MonsterEntities.Ghost;
import entity_Impl.Players.Player;
import entity_Impl.Players.PlayerFactory;
import entity_Interfaces.CollectableType;
import entity_Interfaces.EntityManagementServiceIfc;
import entity_Interfaces.EntityNames;
import entity_Interfaces.MonsterType;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Yanick
 */
public class EntityManagementService implements EntityManagementServiceIfc {

    public EntityManagementService() {
        m_players = new ArrayList<>();
        m_monsters = new ArrayList<>();
//        m_monsters.add(new Ghost(new Coordinates(64, 64)));
        m_collectables = new ArrayList<>();
        m_monsterFactory = new MonsterFactory();
        m_collectableFactory = new CollectableFactory();
        m_playerFactory = new PlayerFactory();
        m_playerSkinPaths = new HashMap<>(); // Todo: setup skinPaths.
    }

//    @Override
//    public void registerEntity(EntityIfc entity) {
//        m_entities.add(entity);
//    }
    @Override
    public void createMonster(MonsterType monsterType, Coordinates position) {
        m_monsters.add(m_monsterFactory.createMonster(monsterType, position));
    }

    @Override
    public void createCollectable(CollectableType collectableType, Coordinates position) {
        m_collectables.add(m_collectableFactory.createCollectable(collectableType, position));
    }

    @Override
    public void createPlayer(String playerId, Coordinates position) throws IllegalArgumentException {
        // Todo: update this to have different skins.
        Player player = m_playerFactory.createPlayer(playerId, position, m_playerSkinPaths.get(playerId));
        m_players.add(player);
//        m_players.put(playerId, player); // Todo
    }

    @Override
    public void updateEntities() {
        updatePlayers();
        updateMonsters();
        updateCollectables();
    }

    @Override
    public void drawEntities(Graphics2D g2) {
        // Todo: Change this to draw in layers.
        for (EntityAbs collectable : m_collectables) {
            collectable.draw(g2);
        }
        for (EntityAbs monster : m_monsters) {
            monster.draw(g2);
        }
        for (EntityAbs player : m_players) {
            player.draw(g2);
        }
    }

    @Override
    public String getId() {
        return EntityNames.Services.EntityManagementService;
    }

    private void updatePlayers() {
        for (EntityAbs player : m_players) {
            player.update();
        }
    }

    private void updateMonsters() {
        for (EntityAbs monster : m_monsters) {
            monster.update();
        }
    }

    private void updateCollectables() {
        for (EntityAbs collectable : m_collectables) {
            collectable.update();
        }
    }

    private final ArrayList<EntityAbs> m_players;
    private final ArrayList<EntityAbs> m_monsters;
    private final ArrayList<EntityAbs> m_collectables;
    private final MonsterFactory m_monsterFactory;
    private final CollectableFactory m_collectableFactory;
    private final PlayerFactory m_playerFactory;
    private final HashMap<String, String> m_playerSkinPaths;
}
