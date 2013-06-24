package settings

import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 23.06.13
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
class Closures {
    /**
     * Closure must return distance between two points. Used in Distance
     */
    static def defaultDistanceMethod = {f, t -> return Math.sqrt(Math.pow(f.x-t.x, 2) + Math.pow(f.y - t.y, 2))}

    /**
     * Algorithm for compare two images. Used in ImageComparator
     */
    static def compareTwoImages = {BufferedImage image1, BufferedImage image2 ->
        if (Math.abs(image1.height - image2.height) < 5 && Math.abs(image1.width - image2.width) < 5){
            def sum1 = 0, avg1 = 0, sum2 = 0, avg2 = 0
            for (i in 0..image1.width-1)
                for(j in 0..image1.height-1)
                    sum1 += image1.getRGB(i,j)
            avg1 = sum1/(image1.width * image1.height)
            for (i in 0..image2.width-1)
                for(j in 0..image2.height-1)
                    sum2 += image2.getRGB(i,j)
            avg2 = sum2/(image2.width * image2.height)
            return Math.abs(avg1 - avg2)
        }
        return Long.MAX_VALUE
    }
}
