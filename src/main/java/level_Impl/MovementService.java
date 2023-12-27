package level_Impl;

import common.Coordinates;
import common.Direction;
import entity_Impl.Monsters.Behaviors.MovementBehaviorIfc;
import entity_Interfaces.EntityManagementServiceIfc;
import java.util.ArrayList;
import java.util.List;
import level_Interfaces.LevelNames;
import level_Interfaces.MovementServiceIfc;

/**
 *
 * @author Yanick
 */
public class MovementService implements MovementServiceIfc {

    public MovementService() {
        s_instance = this;
    }
    
    @Override
    public Direction getNextHostileDirection(Coordinates position, Direction direction) {
        Direction newDirection = Direction.NoDirection;
        // Todo: remove this if
            if (isBetweenCells(position)) {
                newDirection = direction;
            }
            else {
                position = new Coordinates(position.x / 8, position.y / 8);
                List<Coordinates> nextCells = getAllFreeNeighboringCells(position);
                nextCells = MovementBehaviorIfc.eliminateLastCell(nextCells, position, direction);
                float distance = Float.MAX_VALUE;
//                Coordinates closestPlayerPosition = getClosestPlayerPosition(position);
                Coordinates closestPlayerPosition = new Coordinates(7, 5);
                for (Coordinates cell : nextCells) {
                    float distanceToNewCell = Coordinates.getDistance(cell, closestPlayerPosition);
                    if (distance > distanceToNewCell) {
                        newDirection = position.getDirection(cell);
                        distance = distanceToNewCell;
                    }
                }
            }
        return newDirection;
    }

    @Override
    public Direction getNextRandomDirection(Coordinates position, Direction direction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Direction convertDesiredDirection(Coordinates position, Direction direction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isBetweenCells(Coordinates position) {
        return isBetweenHorizontalCells(position) || isBetweenVerticalCells(position);
    }

    @Override
    public List<Coordinates> getAllFreeNeighboringCells(Coordinates position) {
        List<Coordinates> freeNeighboringCells = new ArrayList<>();
        
        if (isWalkable(new Coordinates(position.x + 1, position.y))) {
            freeNeighboringCells.add(new Coordinates(position.x + 1, position.y));
        }
        if (isWalkable(new Coordinates(position.x, position.y + 1))) {
            freeNeighboringCells.add(new Coordinates(position.x, position.y + 1));
        }
        if (isWalkable(new Coordinates(position.x - 1, position.y))) {
            freeNeighboringCells.add(new Coordinates(position.x - 1, position.y));
        }
        if (isWalkable(new Coordinates(position.x, position.y - 1))) {
            freeNeighboringCells.add(new Coordinates(position.x, position.y - 1));
        }
        return freeNeighboringCells;
    }
    
    @Override
    public int getBlockSegments() {
        return BLOCK_SEGMENTS;
    }

    @Override
    public void initializeService() {
        // Nothing to do here.
    }

    @Override
    public String getId() {
        return LevelNames.Services.MovementService;
    }
    
    public void setStage(Stage stage) {
        m_stage = stage;
    }
    
    public static MovementService getInstance() {
        return s_instance;
    }

    private boolean isBetweenHorizontalCells(Coordinates position) {
        return position.x % BLOCK_SEGMENTS != 0;
    }

    private boolean isBetweenVerticalCells(Coordinates position) {
        return position.y % BLOCK_SEGMENTS != 0;
    }

    private Coordinates getClosestPlayerPosition(Coordinates position) {
        Coordinates closestPlayerPosition = null;
        float shortestDistance = Float.MAX_VALUE;
        for (Coordinates playerPosition: m_entityManagementServiceIfc.getAllPlayerPositions()) {
            float currentDistance = Coordinates.getDistance(position, playerPosition);
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestPlayerPosition = playerPosition;
            }
        }
        return closestPlayerPosition;
    }
    
    private boolean isWalkable(Coordinates cell) {
        var blocks = m_stage.getBlocks();
        return !blocks.containsKey(cell) || blocks.get(cell).isWalkable();
    }
    
    private Stage m_stage;
    private EntityManagementServiceIfc m_entityManagementServiceIfc;
    
    private static MovementService s_instance;
    
    // Shows how many steps can be taken between two blocks.
    private final static int BLOCK_SEGMENTS = 8;
}