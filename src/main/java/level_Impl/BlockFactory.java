package level_Impl;

import level_Impl.Blocks.*;

/**
 *
 * @author Yanick
 */
public class BlockFactory {
    
    public BlockAbs createBlock(BlockType type) { 
        return switch (type) {
            case Brick -> new Brick();
            case DeathBlock -> new DeathBlock();    
            case Wall -> new Wall();
            default -> null;
        };
    }
}