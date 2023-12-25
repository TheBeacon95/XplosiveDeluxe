package entity_Interfaces;

import common.Coordinates;
import common.ServiceIfc;
import java.awt.Graphics2D;

/**
 * This service allows for components to update the entities.
 * @author Yanick
 */
public interface EntityManagementServiceIfc extends ServiceIfc {
    
//    /**
//     * Allows for an Entity to be registered to the service.
//     * When UpdateEntities is called, each registered Entity will be updated.
//     * @param entity 
//     */
//    void registerEntity(EntityIfc entity);
    
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
     * Updates all Entities.
     */
    void updateEntities();
    
    /**
     * Draws all Entities in a specific order.
     * @param g2 
     */
    void drawEntities(Graphics2D g2);
}