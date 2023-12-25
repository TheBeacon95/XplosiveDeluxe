package level_Impl.Blocks;

import level_Impl.BlockAbs;

/**
 *
 * @author Yanick
 */
public class Wall extends BlockAbs {
    
    public Wall() {
        loadSprites("Wall");
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