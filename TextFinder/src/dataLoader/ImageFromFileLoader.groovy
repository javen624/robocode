package dataLoader

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 23.06.13
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
class ImageFromFileLoader extends ImageLoader{
    def getListFileImagesFromDir (dirPath){
        return new File(dirPath).listFiles(new ImgFileFilter())
    }
    def getAllImagesByRootDir(String rootDirPath, ArrayList result = [] as ArrayList<File>){
        println result.size()
        def root = new File(rootDirPath)
        if (root.isDirectory()){
            def files = root.listFiles()
            files.each{
                if (it.isDirectory())
                    getAllImagesByRootDir(rootDirPath + "\\" + it.name, result)
                else
                    result.add(it)
            }
            return result
        }

    }
    @Override
    BufferedImage getImage(path) {
        return ImageIO.read(new File(path))
    }
}
