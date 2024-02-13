package level_Impl;

import common.Coordinates;
import level_Impl.Blocks.*;
import level_Interfaces.BlockType;

/**
 *
 * @author Yanick
 */
public class BlockFactory {

    public BlockAbs createBlock(BlockType type, Coordinates position) {
        return switch (type) {
            case Brick -> new Brick(position);
            case DeathBlock -> new DeathBlock();
            case Wall -> new Wall();
            case Bomb -> new Bomb();
            case OnBlock -> new OnBlock();
            default -> null;
        };
    }

    public BlockAbs createBomb(Coordinates position) {
        return new Bomb(position);
    }
}