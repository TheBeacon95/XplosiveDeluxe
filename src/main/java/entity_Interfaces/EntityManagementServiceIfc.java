package entity_Interfaces;

import common.Coordinates;
import common.ServiceIfc;
import java.awt.Graphics2D;
import java.util.List;

/**
 * This service allows for components to update the entities.
 * @author Yanick
 */
public interface EntityManagementServiceIfc extends ServiceIfc {
    
    /**
     * Creates a Monster and registers it.
     * @param monsterType The type of Monster to be created
     * @param position Where the Monster is placed
     */
    void createMonster(MonsterType monsterType, Coordinates position);
    
    /**
     * Creates a Collectable and registers it.
     * @param collectableType The type of Collectable to be created
     * @param position Where the Collectable will be placed
     */
    void createCollectable(CollectableType collectableType, Coordinates position);
    
    /**
     * Creates a player and registers them.
     * @param playerId There are maximally 4 Players. This ID represents which player it is.
     * @param position Where the Player will be placed.
     * @throws IllegalArgumentException 
     */
    void createPlayer(String playerId, Coordinates position) throws IllegalArgumentException;
    
    /**
     * Creates an explosion of any type.
     * @param explosionType the type of the explosion
     * @param strength it range of the explosion
     * @param position where the explosion originates
     */
    void createExplosion(ExplosionType explosionType, int strength, Coordinates position);
    
    /**
     * Retrieves the position of all players.
     * @return the position of all players
     */
    List<Coordinates> getAllPlayerPositions();

    /**
     * Shows if a specific cell currently has an explosion in it.
     * @param gridPosition
     * @return 
     */
    boolean isExplosionHere(Coordinates gridPosition);
    
    /**
     * Updates all Entities.
     */
    void updateEntities();
    
    /**
     * Draws all Entities in a specific order.
     * @param g2 
     */
    void drawEntities(Graphics2D g2);
    
    /**
     * Clears out all entities so a new level can start.
     */
    public void clearAllEntities();

    /**
     * Starts all entities.
     */
    public void startEntities();
    
    /**
     * 
     * @param position
     * @return the coordinates of the closest player.
     */
    Coordinates getClosestPlayerPosition(Coordinates position);
}