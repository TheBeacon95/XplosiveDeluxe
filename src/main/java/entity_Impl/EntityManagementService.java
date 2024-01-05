package entity_Impl;

import entity_Impl.Monsters.*;
import common.*;
import entity_Impl.Collectables.*;
import entity_Impl.Explosions.*;
//import entity_Impl.Monsters.MonsterEntities.Ghost;
import entity_Impl.Players.*;
import entity_Interfaces.*;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Yanick
 */
public class EntityManagementService implements EntityManagementServiceIfc {

    public EntityManagementService() {
        m_players = new ArrayList<>();
        m_monsters = new ArrayList<>();
        m_collectables = new ArrayList<>();
        m_explosions = new HashMap<>();
    }
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
    public void createExplosion(ExplosionType explosionType, int strength, Coordinates position) {
        ArrayList<EntityAbs> newExplosions = m_explosionFactory.createExplosion(position, explosionType, strength);
        for (EntityAbs newExplosion: newExplosions) {
            if (m_explosions.containsKey(newExplosion.getGridPosition())) {
                m_explosions.remove(newExplosion.getGridPosition());
            }
            m_explosions.put(newExplosion.getGridPosition(), newExplosion);
        }
    }
    
    @Override
    public void removeExplosion(Coordinates position) {
        if (m_explosions.containsKey(position)) {
            m_explosions.remove(position);
        }
    }

    @Override
    public List<Coordinates> getAllPlayerPositions() {
        ArrayList<Coordinates> positions = new ArrayList<>();
        for (EntityAbs player: m_players) {
            positions.add(player.getGlobalPosition());
        }
        return positions;
    }
    
    @Override
    public boolean isExplosionHere(Coordinates position) {
//        m_explosions.get
        return false;
    }
    
    @Override
//    public void onEntityDied(EntityIfc entity) {
//        if (entity != null) {
//            onPlayerDied((EntityAbs) entity);
//        }
//        else if ((MonsterAbs) entity != null) {
//            onMonstererDied((EntityAbs) entity);
//        }
//        
//    }
//
    public void onEntityDied(PlayerIfc entity) {
        if (m_players.contains((EntityAbs) entity)) {
            m_players.remove((EntityAbs) entity);
        }
    }

    @Override
    public void onEntityDied(MonsterIfc entity) {
        if (m_monsters.contains((EntityAbs) entity)) {
            m_monsters.remove((EntityAbs) entity);
        }
    }

    @Override
    public void updateEntities() {
        updatePlayers();
        updateMonsters();
        updateCollectables();
        updateExplosions();
        checkCollisions();
    }
    
    private void checkCollisions() {
        for (EntityAbs monster: new ArrayList<>(m_monsters)) {
            for (EntityAbs player: new ArrayList<>(m_players)) {
                if (monster.isColliding(player)) {
                    monster.collide(player);
                }
            }
        }
        for (EntityAbs explosion: new ArrayList<>(m_explosions.values())) {
            for (EntityAbs player: new ArrayList<>(m_players)) {
                if (explosion.isColliding(player)) {
                    explosion.collide(player);
                }
            }
        }
        for (EntityAbs explosion: new ArrayList<>(m_explosions.values())) {
            for (EntityAbs monster: new ArrayList<>(m_monsters)) {
                if (explosion.isColliding(monster)) {
                    explosion.collide(monster);
                }
            }
        }
    }

    @Override
    public void drawEntities(Graphics2D g2) {
        // Todo: Change this to draw in layers.
        for (EntityAbs collectable : new ArrayList<>(m_collectables)) {
            collectable.draw(g2);
        }
        for (EntityAbs monster : new ArrayList<>(m_monsters)) {
            monster.draw(g2);
        }
        for (EntityAbs player : new ArrayList<>(m_players)) {
            player.draw(g2);
        }
        for (EntityAbs explosion : new ArrayList<>(m_explosions.values())) {
            explosion.draw(g2);
        }
    }

    @Override
    public void initializeService() {
        m_monsterFactory = new MonsterFactory();
        m_collectableFactory = new CollectableFactory();
        m_explosionFactory = new ExplosionFactory();
        m_playerFactory = new PlayerFactory();
        m_playerSkinPaths = new HashMap<>();
        // Todo: setup skinPaths.
        m_playerSkinPaths.put("Player_1", "Sprites/Players/Skin_0/");
        m_playerSkinPaths.put("Player_2", "Sprites/Players/Skin_1/");
        m_playerSkinPaths.put("Player_3", "Sprites/Players/Skin_2/");
        m_playerSkinPaths.put("Player_4", "Sprites/Players/Skin_3/");
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

    private void updateExplosions() {
        for (EntityAbs explosion : new ArrayList<>(m_explosions.values())) {
            explosion.update();
        }
    }

    private final ArrayList<EntityAbs> m_players;
    private final ArrayList<EntityAbs> m_monsters;
    private final ArrayList<EntityAbs> m_collectables;
    private final HashMap<Coordinates, EntityAbs> m_explosions;
    private MonsterFactory m_monsterFactory;
    private ExplosionFactory m_explosionFactory;
    private CollectableFactory m_collectableFactory;
    private PlayerFactory m_playerFactory;
    private HashMap<String, String> m_playerSkinPaths;
}