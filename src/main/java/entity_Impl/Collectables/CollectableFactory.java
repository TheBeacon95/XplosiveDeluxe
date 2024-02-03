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
            case BombPlus ->
                new BombPlus(position);
            case LifePlus ->
                new LifePlus(position);
            case StrengthPlus ->
                new StrengthPlus(position);
            case SpeedPlus ->
                new SpeedPlus(position);
            case Shield ->
                new Shield(position);
            case Cobweb ->
                new Cobweb(position);
            default ->
                null;
        };
    }
}
