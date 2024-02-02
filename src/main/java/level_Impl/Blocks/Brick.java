package level_Impl.Blocks;

import common.*;
import entity_Interfaces.*;
import java.util.Random;
import level_Impl.BlockAbs;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class Brick extends BlockAbs {
    
    public Brick(Coordinates position) {
        super(BlockType.Brick);
        m_random = new Random();
        m_position = position;
    }
    
    @Override
    public boolean isEatable() {
        return true;
    }
    
    @Override
    protected void onExplode() {
        m_didExplode = true;
    }
    
    @Override
    protected void onDestroyed() {
        if (m_didExplode && m_random.nextBoolean()) {
            EntityManagementServiceIfc service = (EntityManagementServiceIfc) ServiceManager.getService(EntityNames.Services.EntityManagementService);
            service.createCollectable(CollectableType.BombPlus, m_position);
        }
    }
    
    private boolean m_didExplode = false;
    private final Coordinates m_position;
    private final Random m_random;
}