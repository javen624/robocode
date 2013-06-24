package dataLoader

import clustering.Cluster
import settings.Setting

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 23.06.13
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
class ImageToFileUnload extends ImageUnload{
    @Override
    void imgUnload(BufferedImage img, String pathToUnload) {
        ImageIO.write(img, "jpg", new File(pathToUnload))
    }

    void clustersUnload(BufferedImage image, clusters, String pathToSave){
        def count = 0
        clusters = clusters.sort(new Comparator<Cluster>(){
            @Override
            int compare(Cluster o1, Cluster o2) {
                if (o1.p1.y < o2.p1.y){
                    return -1
                }
                else if (o1.p1.y == o2.p1.y){
                    if (o1.p1.x < o2.p1.x){
                        return -1
                    }
                    else if (o1.p1.x > o2.p1.x)
                        return 1
                    else
                        return 0
                }
                else return 1
            }
        })
        clusters.each {
            int x = it.p1.x
            int y = it.p1.y
            int width = it.p2.x-it.p1.x
            int height = it.p2.y - it.p1.y
            if (width > 0 && height > 0 && it.points.size > 50)
                imgUnload(image.getSubimage(x, y, width+1, height+1),
                        pathToSave + "_" + count++ +".jpg")
        }
    }
}
