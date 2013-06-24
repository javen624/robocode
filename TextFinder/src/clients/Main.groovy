package clients

import clustering.Cluster
import clustering.Distance
import clustering.Pixel
import clustering.Point

import java.awt.Color
import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: chermashentsev
 * Date: 20.06.13
 * Time: 14:59
 * To change this template use File | settings.Setting | File Templates.
 */
class Main {
    public static void main(String[] args) {
        /*File dir1 = new File ("src\\sl")
        File dir2 = new File("src\\img")
        println dir2.listFiles().size()
        long[][] tableOfDiff = new long[dir1.listFiles().size()][dir2.listFiles().size()]
        dir1.listFiles().eachWithIndex { im1, i ->
            dir2.listFiles().eachWithIndex { im2, j ->
                tableOfDiff[i][j] = compareImages(ImageIO.read(im1), ImageIO.read(im2), compareTwoImages) //?: Long.MAX_VALUE
            }
        }
        def findedPair = [:]
        tableOfDiff.each {
            it.each {entry ->
                print entry +" "
            }
            println()
        }
        tableOfDiff.eachWithIndex { entries, int i ->
            entries.eachWithIndex { long entry, int j ->
                if (entry == 0 ){
                    findedPair[i] = j
                    entry = Long.MAX_VALUE
                }
            }
        }
        def min = Long.MAX_VALUE, c = -1
        println findedPair
        for (int i in 0..dir1.listFiles().size()-1){
            min = Long.MAX_VALUE; c = -1
            for (int j in 0..dir2.listFiles().size()-1){
                if (!findedPair.containsKey(i) && !findedPair.containsValue(j)){
                    if (min > tableOfDiff[i][j]){
                        min = tableOfDiff[i][j]
                        c = j;
                    }
                }
            }
            if (c >= 0)
                findedPair[i] = c
        }
        println findedPair

        */

        /*
          */



    }





    static def firstAlgToCompareImage = {BufferedImage image1, BufferedImage image2 ->
        def difMatrix = new long[image1.width][image1.height]
        for (i in 0..Math.min(image1.width, image2.width)-1){
            for (j in 0..Math.min(image2.height, image1.height)-1){
                difMatrix[i][j] = Math.abs(image1.getRGB(i, j) - image2.getRGB(i, j))
            }
        }
        long sum = 0

        difMatrix.each{itArr ->
            itArr.each {
                sum += it
            }
        }
        sum / difMatrix.size()
    }
    static def compareImages(BufferedImage image1, BufferedImage image2, Closure algorith){
        return algorith(image1, image2)
    }



}
