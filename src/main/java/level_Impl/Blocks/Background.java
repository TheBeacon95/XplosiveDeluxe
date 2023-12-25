package level_Impl.Blocks;

import level_Impl.BlockAbs;

/**
 *
 * @author Yanick
 */
public class Background extends BlockAbs {
    
    public Background() {
        loadSprites("Background");
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