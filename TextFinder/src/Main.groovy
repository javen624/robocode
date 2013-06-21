import javax.imageio.ImageIO
import java.awt.Color
import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.image.Raster

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 19.06.13
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
class Main {
    public static void main(String[] args) {
        Color color;
        Pixel[][] pixels;



        BufferedImage image = ImageLoader.getImage("C:\\Users\\Пекарь\\Robocode\\firstRobot\\TextFinder\\out\\production\\TextFinder\\1.jpg");

        BufferedImage croppedImage = image.getSubimage(0,0, 200, 200)



        ImageIO.write(croppedImage, "jpg", new File("C:\\Users\\Пекарь\\Robocode\\firstRobot\\TextFinder\\out\\production\\TextFinder\\cropped.jpg"))

       /* pixels = new Pixel[image.getWidth()][image.getHeight()]
       /* pixels.eachWithIndex{ Pixel[] entryArray, int i ->
            entry.eachWithIndex{ Pixel entry, int j ->
                color = new Color(image.getRGB(i,j))
                entry = new Pixel(red: color.red, blue: color.blue, green: color.green);
            }
        }     */     /*
        for (i in 0..image.getWidth()-1){
            for (j in 0..image.getHeight()-1){
                color = new Color(image.getRGB(i,j))
                pixels[i][j] = new Pixel(red: color.red, blue: color.blue, green: color.green, x: i, y: j);
               // println color.red + " " + color.green + " " + color.blue
            }
        }

        def blackPoints = new ArrayList<Point>()
        pixels.each {
            it.each {
            if (it == it.black)
                blackPoints.add(new Point(x: it.x, y: it.y))
            }
        }

        blackPoints.each{

        }                 */
    }



}
