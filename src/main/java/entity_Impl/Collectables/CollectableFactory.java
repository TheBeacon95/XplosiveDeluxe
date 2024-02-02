package entity_Impl.Collectables;

import common.Coordinates;
import entity_Interfaces.*;

/**
 *
 * @author Yanick
 */
public class CollectableFactory {
    public CollectableAbs createCollectable(CollectableType type, Coordinates position) {
        return switch (type) {
            case BombPlus -> new BombPlus(position);
            default -> null;
        };
    }
}
