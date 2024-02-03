package level_Impl.Blocks;

import level_Impl.BlockAbs;
import level_Interfaces.BlockType;

/**
 *
 * @author Yanick
 */
public class Wall extends BlockAbs {

    public Wall() {
        super(BlockType.Wall);
    }

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public boolean isPhaseable() {
        return false;
    }
}
