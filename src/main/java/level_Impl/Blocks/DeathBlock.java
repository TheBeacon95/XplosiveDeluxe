package level_Impl.Blocks;

import level_Impl.BlockAbs;
import level_Interfaces.BlockType;

/**
 *
 * @author Yanick
 */
public class DeathBlock extends BlockAbs {
    
    public DeathBlock() {
        super(BlockType.DeathBlock);
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