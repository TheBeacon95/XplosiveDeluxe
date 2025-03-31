package level_Impl.Blocks;

import level_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class OnOffSwitch extends OnOffBlockAbs {

    public OnOffSwitch(SwitchType switchType) {
        super(switch (switchType) {
            case OnSwitch -> BlockType.OnSwitch;
            case OffSwitch -> BlockType.OffSwitch;
            case OnOffSwitch -> BlockType.OnOffSwitch;
        }, switchType != SwitchType.OffSwitch);
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public boolean canBlockExplosions() {
        return true;
    }

    @Override
    public void onExplode() {
        m_stageManagementService.toggleOnOffState();
    }

    public enum SwitchType {
        OnSwitch,
        OffSwitch,
        OnOffSwitch
    }
}