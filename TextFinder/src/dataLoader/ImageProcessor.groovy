package dataLoader

import clustering.Cluster
import clustering.Distance
import clustering.Pixel
import clustering.Point

import java.awt.Color
import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 23.06.13
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
class ImageProcessor {
    BufferedImage image
    Pixel[][] pixels
    def blackPoints = [] as ArrayList<Point>
    def distances = [:]  as HashMap<clustering.Pixel, clustering.Distance[]>
    ImageProcessor(BufferedImage image){
        setImage(image)
    }
    def setImage(BufferedImage image){
        if (image != null){
            this.image = image
        }
        else throw NullPointerException()
    }

    def getPixelsFromImage(){
        println "getpixelstart"
        Pixel[][] pixels;
        Color color;
        pixels = new Pixel[image.getWidth()][image.getHeight()]

        for (i in 0..image.getWidth()-1){
            for (j in 0..image.getHeight()-1){
                color = new Color(image.getRGB(i,j))
                pixels[i][j] = new Pixel(red: color.red, blue: color.blue, green: color.green, x: i, y: j)
            }
        }

        this.pixels = pixels
        return this.pixels
    }


    def getBlackPointsFromPixels(Pixel[][] pixels = this.pixels){
        if (pixels != null){
            for (int i = 0; i < pixels.size(); i++){
                for (int j = 0; j < pixels[i].size(); j++){
                    if (Pixel.BLACK_PIXEL == pixels[i][j])
                        blackPoints.add(new Point(x: pixels[i][j].x, y: pixels[i][j].y))
                }
            }
          /*  pixels.each{
                it.each {
                    if (Pixel.BLACK_PIXEL == it)
                        blackPoints.add(new Point(x: it.x, y: it.y))
                }
            }                */


        return blackPoints
        }
        throw NullPointerException("You must first call getPixelsFromImage")
    }

    def getDistancesForBlackPoints(ArrayList<Point> blackPoints = this.blackPoints){
        def arr = []
        println (blackPoints.size())
        for (int i = 0; i < blackPoints.size(); i++){
            for (int j = 0; j < blackPoints.size(); j++){
                def d = new clustering.Distance(blackPoints.getAt(i), blackPoints.getAt(j))
                if (d.distance > 0 && d.distance < 50){
                    arr.add(d)
                }
            }

            distances.put(blackPoints.getAt(i), arr)
            arr = []

        }
        /*
        blackPoints.each { it ->
            blackPoints.each{ secondIt ->
                def d = new clustering.Distance(it, secondIt)
                if (d.distance > 0 && d.distance < 50)
                    arr.add(d)
            }
            distances.put(it, arr)
            arr = []
        }             */
        return this.distances
    }


}
