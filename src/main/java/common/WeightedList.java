package common;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Yanick
 * @param <T>
 */
public class WeightedList<T extends Enum<T>> {

    public WeightedList() {
        m_weightList = new HashMap<>();
        m_combinedWeight = 0;
        m_random = new Random();
    }

    public void addItem(T item, int weight) {
        if (!m_weightList.containsKey(item)) {
            m_weightList.put(item, weight);
            m_combinedWeight += weight;
        }
    }

    public T getRandomItem() {
        int value = m_random.nextInt(m_combinedWeight);
        T item = null;
        for (Map.Entry<T, Integer> weightListEntry : m_weightList.entrySet()) {
            if (value < weightListEntry.getValue()) {
                item = weightListEntry.getKey();
                break;
            }
            else {
                value -= weightListEntry.getValue();
            }
        }
        return item;
    }

    private final HashMap<T, Integer> m_weightList;
    private final Random m_random;
    private int m_combinedWeight;
}
