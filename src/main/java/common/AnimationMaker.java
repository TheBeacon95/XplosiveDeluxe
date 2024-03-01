package common;

import entity_Impl.EntityAbs;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yanick
 */
public final class AnimationMaker {

    public static Animation createAnimation(String folderPath, String animationName) {
        ArrayList<BufferedImage> sprites = new ArrayList<>();
        int i = 0;
        InputStream resourceStream = AnimationMaker.class.getClassLoader().getResourceAsStream(folderPath + "/" + animationName + "_" + i + ".png");
        while (resourceStream != null) {
            BufferedImage nextUpSprite = load(resourceStream);
            sprites.add(nextUpSprite);
            i++;
            resourceStream = AnimationMaker.class.getClassLoader().getResourceAsStream(folderPath + "/" + animationName + "_" + i + ".png");
        }
        return new Animation(sprites);
    }

    private static BufferedImage load(InputStream fileStream) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(fileStream);
        }
        catch (IOException ex) {
            Logger.getLogger(EntityAbs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return sprite;
    }
}
