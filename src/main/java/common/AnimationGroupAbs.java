package common;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yanick
 */
public abstract class AnimationGroupAbs implements AnimationIfc {
private final HashMap<String, AnimationIfc> m_animations;

    public AnimationGroupAbs() {
        m_animations = new HashMap<>();
    }

    public abstract void setSprites(String folderPath);

    protected final void add(String animationName, AnimationIfc animation) {
        if (animationName == null || animationName.isEmpty() || m_animations.containsKey(animationName)) {
            return;
        }

        m_animations.put(animationName, animation);
    }

    protected final AnimationIfc get(String animationName) {
        if (animationName == null || animationName.isEmpty() || !m_animations.containsKey(animationName)) {
            return null;
        }
        else {
            return m_animations.get(animationName);
        }
    }

    protected final void clear() {
        m_animations.clear();
    }

    protected final void insertCopies(AnimationGroupAbs animationGroup) {
        for (Map.Entry<String, AnimationIfc> entry: m_animations.entrySet()) {
            animationGroup.add(entry.getKey(), entry.getValue().copy());
        }
    }

    @Override
    public abstract BufferedImage getSpriteToDraw();
}