package dataLoader

import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 23.06.13
 * Time: 13:48
 * To change this template use File | Settings | File Templates.
 */
class ImageComparator {
    def algorithm
    public ImageComparator (Closure alg){
        this.setAlgorithm (alg)
    }
    public void setAlgorithm (Closure alg){
        if (alg != null && alg instanceof Closure)
            this.algorithm = alg
        else
            throw new ClassCastException("Algorithm must be closure")
    }
    def compareImages(BufferedImage image1, BufferedImage image2){
        return algorithm(image1, image2)
    }
}
