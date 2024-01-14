package level_Impl;

import common.Coordinates;
import level_Interfaces.BlockType;
import level_Impl.Blocks.*;

/**
 *
 * @author Yanick
 */
public class BlockFactory {
    
    public BlockFactory() {
        for (BlockType type: BlockType.values()) {
            BlockAbs.loadSprites(type.name());
        }
    }
    
    public BlockAbs createBlock(BlockType type) { 
        return switch (type) {
            case Brick -> new Brick();
            case DeathBlock -> new DeathBlock();    
            case Wall -> new Wall();
            case Bomb -> new Bomb();
            default -> null;
        };
    }
    
    public BlockAbs createBomb(Coordinates position) {
        return new Bomb(position);
    }
}