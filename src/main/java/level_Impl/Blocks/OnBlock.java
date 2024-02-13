package level_Impl.Blocks;

import common.*;
import level_Impl.BlockAbs;
import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class OnBlock extends BlockAbs {

    public OnBlock() {
        super(BlockType.OnBlock);
        m_stageManagementService = (StageManagementServiceIfc) ServiceManager.getService(LevelNames.Services.StageManagementService);
        m_onAnimation = createAnimation("IdleOn");
        m_offAnimation = createAnimation("IdleOff");
    }

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean isWalkable() {
        return !m_stageManagementService.IsOnState();
    }

    @Override
    public boolean isPhaseable() {
        return !m_stageManagementService.IsOnState();
    }

    @Override
    public boolean canBlockExplosions() {
        return m_stageManagementService.IsOnState();
    }

    @Override
    public boolean isReplaceable() {
        return false;
    }

    @Override
    protected final Animation getCurrentAnimation() {
        if (m_stageManagementService.IsOnState()) {
            return m_onAnimation;
        }
        else {
            return m_offAnimation;
        }
    }

    private final StageManagementServiceIfc m_stageManagementService;
    private final Animation m_onAnimation;
    private final Animation m_offAnimation;
}