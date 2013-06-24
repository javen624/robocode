package clients

import clustering.Cluster
import clustering.Distance
import clustering.Point
import dataLoader.ImageFromFileLoader
import dataLoader.ImageProcessor
import dataLoader.ImageToFileUnload
import settings.Setting

import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 23.06.13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
class ImagesSplitter {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis()
        long timeFinish
        def loader = new ImageFromFileLoader()
        def unloader = new ImageToFileUnload()

        def imagesFiles = loader.getAllImagesByRootDir(Setting.ROOT_DIR_WITH_IMAGES)
        timeFinish = System.currentTimeMillis()
        println "Images get for " + (timeFinish - timeStart) + " millisec"
        timeStart = System.currentTimeMillis()
        println "$imagesFiles.size images finded"
        BufferedImage image
        ImageProcessor imageProc
        imagesFiles.each{
            println it
            def tempImg = loader.getImage(it.toString())

            image = tempImg.getSubimage(0,53,tempImg.width-5, tempImg.height - 103)
            imageProc = new ImageProcessor(image)
            imageProc.getPixelsFromImage()

            timeFinish = System.currentTimeMillis()
            println "Pixeles getted for " + (timeFinish - timeStart) + " millisec"
            timeStart = System.currentTimeMillis()

            imageProc.getBlackPointsFromPixels()

            timeFinish = System.currentTimeMillis()
            println "Black points get for " + (timeFinish - timeStart) + " millisec"
            timeStart = System.currentTimeMillis()

            imageProc.getDistancesForBlackPoints()

            timeFinish = System.currentTimeMillis()
            println "Distances get for " + (timeFinish - timeStart) + " millisec"
            timeStart = System.currentTimeMillis()

            def clusters = Cluster.createClusters(imageProc.blackPoints, imageProc.distances)

            timeFinish = System.currentTimeMillis()
            println "Clusters get for " + (timeFinish - timeStart) + " millisec"
            timeStart = System.currentTimeMillis()

            unloader.clustersUnload(image, clusters, it.toString())
        }
       /*
        clustering.Point point

        int count = 0
        clusters.each {
            int x = it.p1.x
            int y = it.p1.y
            int width = it.p2.x-it.p1.x
            int height = it.p2.y - it.p1.y
            if (width > 0 && height > 0 && it.points.size > 50)
                ImageIO.write(image.getSubimage(x, y, width+1, height+1),"jpg", new File("src\\" + count++ +".jpg"))
        }
        println(clusters)    */
    }
}
