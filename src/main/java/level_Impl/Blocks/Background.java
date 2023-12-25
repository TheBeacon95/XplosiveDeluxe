package level_Impl.Blocks;

import level_Impl.BlockAbs;
import level_Impl.BlockType;

/**
 *
 * @author Yanick
 */
public class Background extends BlockAbs {
    
    public Background() {
        super(BlockType.Background);
    }
    
    @Override
    public boolean isWalkable() {
        return true;
    }
    
    @Override
    public boolean isPhaseable() {
        return true;
    }
    
    @Override
    public boolean canBlockExplosions() {
        return false;
    }
}