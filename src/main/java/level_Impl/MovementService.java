package level_Impl;

import common.*;
import entity_Impl.Monsters.Behaviors.MovementBehaviors.MovementBehaviorIfc;
import entity_Interfaces.*;
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
                Coordinates gridPosition = roundDownPositionToGridValue(position);
                List<Coordinates> nextCells = getAllFreeNeighboringCells(gridPosition);
                if (nextCells.size() > 1) {
                    // Todo: remove this. The MovementBehaviorIfc is in entity_Impl.
                    nextCells = MovementBehaviorIfc.eliminateLastCell(nextCells, gridPosition, direction);
                }
                float distance = Float.MAX_VALUE;
                Coordinates closestPlayerPosition = m_entityManagementService.getClosestPlayerPosition(position);
                for (Coordinates cell : nextCells) {
                    Coordinates globalCellPosition = new Coordinates(cell.x * BLOCK_SEGMENTS, cell.y * BLOCK_SEGMENTS);
                    float distanceToNewCell = Coordinates.getDistance(globalCellPosition, closestPlayerPosition);
                    if (distance > distanceToNewCell) {
                        newDirection = gridPosition.getDirection(cell);
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
    public Direction convertDesiredDirection(Coordinates position, Direction desiredDirection, boolean isGhost) {
        boolean isTryingToMoveVertically = desiredDirection.isVertical();
        boolean isTryingToMoveHorizontally = desiredDirection.isHorizontal();
        boolean isBetweenHorizontalCells = isBetweenHorizontalCells(position);
        boolean isBetweenVerticalCells = isBetweenVerticalCells(position);

        // If the player is between horizontal cells and trying to move horizontally, they may always move. (and vice versa)
        if ((isTryingToMoveHorizontally && isBetweenHorizontalCells) || (isTryingToMoveVertically && isBetweenVerticalCells)) {
            return desiredDirection;
        }
        // If the player is horizontal cells and trying to move perpendicularly to them, move them into the direction of the free cell if there is one.
        else if ((isTryingToMoveHorizontally && isBetweenVerticalCells) || (isTryingToMoveVertically && isBetweenHorizontalCells)) {
            Coordinates floorCell = roundDownPositionToGridValue(position);
            floorCell.translate(desiredDirection, 1);
            Coordinates ceilCell = roundUpPositionToGridValue(position);
            ceilCell.translate(desiredDirection, 1);
            return getFreeDirection(floorCell, ceilCell, isGhost, isBetweenVerticalCells);
        }
        // If the player is not between cells, move them in the desired direction, if its free.
        else if (!isBetweenHorizontalCells && !isBetweenVerticalCells) {
            Coordinates gridPosition = roundDownPositionToGridValue(position);
            Coordinates nextTile = new Coordinates(gridPosition);
            nextTile.translate(desiredDirection, 1);
            return (isWalkable(nextTile) || (isGhost && isPhaseable(nextTile))) ? desiredDirection : Direction.NoDirection;
        }
        // Unexpected case: The direction is neither horizontal nor vertical.
        else {
            // Todo: "Assert go"
            return Direction.NoDirection;
        }
    }
    
    private boolean isBetweenCells(Coordinates position) {
        return isBetweenHorizontalCells(position) || isBetweenVerticalCells(position);
    }

    private List<Coordinates> getAllFreeNeighboringCells(Coordinates gridPosition) {
        List<Coordinates> freeNeighboringCells = new ArrayList<>();
        
        for (Coordinates neighbor: gridPosition.getNeighboringCells()) {
            if (isWalkable(neighbor) && !hasExplosion(neighbor)) {
                freeNeighboringCells.add(neighbor);
            }
        }
        return freeNeighboringCells;
    }
    
    private boolean hasExplosion(Coordinates gridPosition) {
        return m_entityManagementService.isExplosionHere(gridPosition);
    }
    
    @Override
    public int getBlockSegments() {
        return BLOCK_SEGMENTS;
    }

    @Override
    public void initializeService() {
        m_entityManagementService = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
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

    private Direction getFreeDirection(Coordinates floorCell, Coordinates ceilCell, boolean isGhost, boolean isBetweenVerticalCells) {
        boolean isFloorCellWalkable = isGhost ? isPhaseable(floorCell) : isWalkable(floorCell);
        boolean isCeilCellWalkable = isGhost ? isPhaseable(ceilCell) : isWalkable(ceilCell);

        Direction direction;
        if (isFloorCellWalkable) {
            direction = isBetweenVerticalCells ? Direction.Up : Direction.Left;
        }
        else if (isCeilCellWalkable) {
            direction = isBetweenVerticalCells ? Direction.Down : Direction.Right;
        }
        else {
            direction = Direction.NoDirection;
        }
        return direction;
    }
    
    private boolean isWalkable(Coordinates cell) {
        var blocks = m_stage.getBlocks();
        return !blocks.containsKey(cell) || blocks.get(cell).isWalkable();
    }
    
    private boolean isPhaseable(Coordinates cell) {
        var blocks = m_stage.getBlocks();
        return !blocks.containsKey(cell) || blocks.get(cell).isPhaseable();
    }
    
    private Coordinates roundUpPositionToGridValue(Coordinates position) {
        boolean isXOnGrid = (position.x / BLOCK_SEGMENTS) * 8 == position.x;
        boolean isYOnGrid = (position.y / BLOCK_SEGMENTS) * 8 == position.y;
        
        int gridValueX = isXOnGrid ? position.x / BLOCK_SEGMENTS : (position.x / BLOCK_SEGMENTS) + 1;
        int gridValueY = isYOnGrid ? position.y / BLOCK_SEGMENTS : (position.y / BLOCK_SEGMENTS) + 1;
        return new Coordinates(gridValueX, gridValueY);
    }
    
    private Coordinates roundDownPositionToGridValue(Coordinates position) {
        return new Coordinates(position.x / BLOCK_SEGMENTS, position.y / BLOCK_SEGMENTS);
    }
    
    private Stage m_stage;
    private EntityManagementServiceIfc m_entityManagementService;
    
    private static MovementService s_instance;
    
    // Shows how many steps can be taken between two blocks.
    private final static int BLOCK_SEGMENTS = 8;
}