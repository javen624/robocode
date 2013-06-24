package dataLoader

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 19.06.13
 * Time: 14:14
 * To change this template use File | settings.Setting | File Templates.
 */
abstract class ImageLoader {
    abstract BufferedImage getImage(def path);

}
