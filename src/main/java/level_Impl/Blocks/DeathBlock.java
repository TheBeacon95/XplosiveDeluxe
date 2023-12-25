package level_Impl.Blocks;

import level_Impl.BlockAbs;

/**
 *
 * @author Yanick
 */
public class DeathBlock extends BlockAbs {
    
    public DeathBlock() {
        loadSprites("DeathBlock");
    }
    
    @Override
    public boolean isWalkable() {
        return false;
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