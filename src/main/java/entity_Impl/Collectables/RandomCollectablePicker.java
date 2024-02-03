package entity_Impl.Collectables;

import common.WeightedList;
import entity_Interfaces.CollectableType;

/**
 *
 * @author Yanick
 */
public class RandomCollectablePicker {

    public static RandomCollectablePicker CreatePickerForCombativeMode() {
        RandomCollectablePicker picker = new RandomCollectablePicker();
        picker.m_weightedList.addItem(CollectableType.BombPlus, 20);
        picker.m_weightedList.addItem(CollectableType.Shield, 20);
        picker.m_weightedList.addItem(CollectableType.SpeedPlus, 20);
        picker.m_weightedList.addItem(CollectableType.StrengthPlus, 20);

        return picker;
    }

    public static RandomCollectablePicker CreatePickerForLevelMode() {
        RandomCollectablePicker picker = new RandomCollectablePicker();
        picker.m_weightedList.addItem(CollectableType.BombPlus, 20);
        picker.m_weightedList.addItem(CollectableType.LifePlus, 1);
        picker.m_weightedList.addItem(CollectableType.Shield, 20);
        picker.m_weightedList.addItem(CollectableType.SpeedPlus, 20);
        picker.m_weightedList.addItem(CollectableType.StrengthPlus, 20);

        return picker;
    }

    public CollectableType pickCollectable() {
        return m_weightedList.getRandomItem();
    }

    private RandomCollectablePicker() {
        m_weightedList = new WeightedList<>();
    }

    private final WeightedList<CollectableType> m_weightedList;
}
