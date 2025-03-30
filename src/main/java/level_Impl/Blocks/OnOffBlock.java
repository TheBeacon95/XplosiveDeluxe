package level_Impl.Blocks;

import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class OnOffBlock extends OnOffBlockAbs {

    public OnOffBlock(boolean isOnType) {
        super(isOnType ? BlockType.OnBlock : BlockType.OffBlock, isOnType);
    }

    @Override
    public boolean isWalkable() {
        return m_isOnType == m_stageManagementService.IsOnState();
    }

    @Override
    public boolean canBlockExplosions() {
        return m_isOnType != m_stageManagementService.IsOnState();
    }
}