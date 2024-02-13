package entity_Impl;

import common.*;
import entity_Impl.Collectables.*;
import entity_Impl.Explosions.*;
import entity_Impl.Monsters.*;
import entity_Impl.Players.*;
import entity_Interfaces.*;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author Yanick
 */
public class EntityManagementService implements EntityManagementServiceIfc {

    public EntityManagementService() {
        m_players = new ArrayList<>();
        m_monsters = new ArrayList<>();
        m_collectables = new ArrayList<>();
        m_explosions = new ArrayList<>();
        m_random = new Random();
        m_randomCollectablePicker = RandomCollectablePicker.CreatePickerForCombativeMode();
    }

    @Override
    public void createMonster(MonsterType monsterType, Coordinates position) {
        m_monsters.add(m_monsterFactory.createMonster(monsterType, position));
    }

    @Override
    public void createCollectable(CollectableType collectableType, Coordinates position) {
        m_collectables.removeIf(x -> x.getGridPosition().equals(position));
        m_collectables.add(m_collectableFactory.createCollectable(collectableType, position));
    }

    @Override
    public void createRandomCollectable(Coordinates position) {
        if (m_random.nextBoolean()) {
            CollectableType collectableType = m_randomCollectablePicker.pickCollectable();
            createCollectable(collectableType, position);
        }
    }

    @Override
    public void createPlayer(String playerId, Coordinates position) throws IllegalArgumentException {
        // Todo: update this to have different skins.
        m_players.add(m_playerFactory.createPlayer(playerId, position, m_playerSkinPaths.get(playerId)));
    }

    @Override
    public void createExplosion(ExplosionType explosionType, int strength, Coordinates position) {
        ArrayList<EntityAbs> newExplosions = m_explosionFactory.createExplosion(position, explosionType, strength);
        for (EntityAbs newExplosion : newExplosions) {
            m_explosions.removeIf(x -> x.getGridPosition().equals(newExplosion.getGridPosition()));
            m_explosions.add(newExplosion);
        }
    }

    @Override
    public List<Coordinates> getAllPlayerPositions() {
        ArrayList<Coordinates> positions = new ArrayList<>();
        for (EntityAbs player : m_players) {
            if (!player.isDieing()) {
                positions.add(player.getGlobalPosition());
            }
        }
        return positions;
    }

    @Override
    public boolean isExplosionHere(Coordinates gridPosition) {
        for (EntityAbs explosion : m_explosions) {
            if (explosion.getGridPosition().equals(gridPosition)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isCobwebHere(Coordinates gridPosition) {
        // Todo: improve
        Optional<EntityAbs> a = m_collectables.stream().filter(x -> x.getGridPosition().equals(gridPosition)).findFirst();
        boolean isCobweb = !a.isEmpty() && a.get() instanceof CollectableAbs && ((CollectableAbs) a.get()).getType() == CollectableType.Cobweb;
        return isCobweb;
    }

    @Override
    public synchronized void updateEntities() {
        ArrayList<Collection<EntityAbs>> entityGroups = new ArrayList<>();
        entityGroups.add(m_players);
        entityGroups.add(m_monsters);
        entityGroups.add(m_collectables);
        entityGroups.add(m_explosions);

        for (Collection<EntityAbs> list : entityGroups) {
            ArrayList<EntityAbs> deadEntities = new ArrayList<>();
            for (EntityAbs entity : list) {
                if (entity.isDead()) {
                    deadEntities.add(entity);
                }
                else {
                    entity.update();
                }
            }
            list.removeAll(deadEntities);
        }
        checkCollisions();
    }

    @Override
    public Coordinates getClosestPlayerPosition(Coordinates globalPosition) {
        Coordinates closestPlayerPosition = new Coordinates();
        float shortestDistance = Float.MAX_VALUE;
        for (Coordinates playerPosition : getAllPlayerPositions()) {
            float currentDistance = Coordinates.getDistance(globalPosition, playerPosition);
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestPlayerPosition = playerPosition;
            }
        }
        return closestPlayerPosition;
    }

    private void checkCollisions() {
        ArrayList<EntityAbs> allEntities = new ArrayList<>();
        allEntities.addAll(m_players);
        allEntities.addAll(m_monsters);
        allEntities.addAll(m_collectables);
        allEntities.addAll(m_explosions);
        for (EntityAbs entity1 : new ArrayList<>(allEntities)) {
            for (EntityAbs entity2 : new ArrayList<>(allEntities)) {
                if (entity1 == entity2) {
                    continue;
                }
                if (!entity2.isDieing() && entity1.isColliding(entity2)) {
                    entity1.collide(entity2);
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
        for (EntityAbs explosion : new ArrayList<>(m_explosions)) {
            explosion.draw(g2);
        }
    }

    @Override
    public void clearAllEntities() {
        m_players.clear();
        m_monsters.clear();
        m_collectables.clear();
        m_explosions.clear();
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

    private final ArrayList<EntityAbs> m_players;
    private final ArrayList<EntityAbs> m_monsters;
    private final ArrayList<EntityAbs> m_collectables;
    private final ArrayList<EntityAbs> m_explosions;
    private MonsterFactory m_monsterFactory;
    private ExplosionFactory m_explosionFactory;
    private CollectableFactory m_collectableFactory;
    private PlayerFactory m_playerFactory;
    private HashMap<String, String> m_playerSkinPaths;
    private final Random m_random;
    private final RandomCollectablePicker m_randomCollectablePicker;

    @Override
    public void startEntities() {
        for (EntityAbs player : new ArrayList<>(m_players)) {
            player.start();
        }
        for (EntityAbs monster : new ArrayList<>(m_monsters)) {
            monster.start();
        }
        for (EntityAbs collectable : new ArrayList<>(m_collectables)) {
            collectable.start();
        }
//        for (EntityAbs explosion : new ArrayList<>(m_explosions)) {
//            explosion.start();
//        }
    }
}
