import org.codehaus.groovy.runtime.typehandling.LongMath

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: chermashentsev
 * Date: 20.06.13
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
class Main {
    public static void main(String[] args) {
        File dir1 = new File ("src\\sl")
        File dir2 = new File("src\\img")
        println dir2.listFiles().size()
        long[][] tableOfDiff = new long[dir1.listFiles().size()][dir2.listFiles().size()]
        dir1.listFiles().eachWithIndex { im1, i ->
            dir2.listFiles().eachWithIndex { im2, j ->
                tableOfDiff[i][j] = compareImages(ImageIO.read(im1), ImageIO.read(im2), secondAlgToCompareImage) //?: Long.MAX_VALUE
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

       /* def image = ImageIO.read(new File("src\\2\\1.jpg"))
        image = image.getSubimage(0,30,image.width, image.height-50)
        def blackPoints = getBlackPoints(getPixelsFromImage(image))

        def distances = [:]  as HashMap<Pixel, Distance[]>


        blackPoints.each { it ->
            def d = blackPoints.collect{ secondIt ->
                new Distance(it, secondIt)
            }
            distances.put(it, d)
        }
        println "Black points size $blackPoints.size"
        Point point
        def clusters = []
        while (!blackPoints.empty){
            Cluster c = new Cluster()
            point = blackPoints.poll()

            c.add(point)
            findNeighbors(point, c, distances, blackPoints, 1)
            c.findExtreme()
            println "Cluster added"
            clusters.add(c)
        }
        int count = 0
        clusters.each {
            int x = it.p1.x
            int y = it.p1.y
            int width = it.p2.x-it.p1.x
            int height = it.p2.y - it.p1.y
            if (width > 0 && height > 0 && it.points.size > 50)
                ImageIO.write(image.getSubimage(x, y, width+1, height+1),"jpg", new File("src\\" + count++ +".jpg"))
        }
        println(clusters)       */

    }


    static def findNeighbors(Point point, Cluster cluster, HashMap<Pixel, Distance[]> distances, PriorityQueue points, k){

        //println k
        distances.get(point).each {
            def a = it.distance < 50, b = it.distance > 0, c = !cluster.points.contains(it.to)
          //  if (c)
        //        println c
            if (a && b && c){
                cluster.add(it.to)
                points.remove(it.to)
                findNeighbors(it.to, cluster, distances, points, ++k)
            }
        }

    }
    static def secondAlgToCompareImage = {BufferedImage image1, BufferedImage image2 ->
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

    static def getPixelsFromImage(BufferedImage image){
        println "getpixelstart"
        Pixel[][] pixels;
        Color color;
        pixels = new Pixel[image.getWidth()][image.getHeight()]

        for (i in 0..image.getWidth()-1){
            for (j in 0..image.getHeight()-1){
                color = new Color(image.getRGB(i,j))
                pixels[i][j] = new Pixel(red: color.red, blue: color.blue, green: color.green, x: i, y: j)
                /*pixel.metaClass.equals={Object obj ->
                        Pixel p = (Pixel)obj
                        if (p.x == pixel.x && p.y == pixel.x)
                            return true
                        return false
                    }; */

            }
        }

        return pixels
    }
    static def getBlackPoints(Pixel[][] pixels){
        def blackPoints = new PriorityQueue(1000, Point.getComparator())
        pixels.each {
            it.each {
                if (it.BLACK_PIXEL == it)
                    blackPoints.add(new Point(x: it.x, y: it.y))
            }
        }
        return blackPoints
    }

}
