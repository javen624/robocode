import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 19.06.13
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
class ImageLoader {
    static BufferedImage getImage(String path){
        BufferedImage image = ImageIO.read(new File(path));
        return image;
    }


}
