package level_Impl.Blocks;

import common.*;
import level_Impl.BlockAbs;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public abstract class OnOffBlockAbs extends BlockAbs {

    public OnOffBlockAbs(BlockType type, boolean isOnType) {
        super(type);
        m_isOnType = isOnType;
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_onAnimation = createAnimation("IdleOn");
        m_offAnimation = createAnimation("IdleOff");
    }

    @Override
    public final boolean isDestructible() {
        return false;
    }

    @Override
    public abstract boolean isWalkable();

    @Override
    public final boolean isPhaseable() {
        return isWalkable();
    }

    @Override
    public abstract boolean canBlockExplosions();

    @Override
    public final boolean isReplaceable() {
        return false;
    }

    @Override
    protected final Animation getCurrentAnimation() {
        if (m_isOnType != m_stageManagementService.IsOnState()) {
            return m_onAnimation;
        }
        else {
            return m_offAnimation;
        }
    }

    protected final boolean m_isOnType;
    protected final StageManagementServiceIfc m_stageManagementService;
    private final Animation m_onAnimation;
    private final Animation m_offAnimation;
}