package dataLoader

import settings.Setting

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 23.06.13
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
class ImgFileFilter implements FileFilter {
    @Override
    boolean accept(File pathname) {
        if (pathname.isFile()){
            def indexBeforeExtension = pathname.getName().lastIndexOf(".")
            if (indexBeforeExtension < pathname.getName().length()-1){
                def extension = pathname.getName().substring(indexBeforeExtension+1)
                if (Setting.IMG_EXTENSIONS.contains(extension)){
                    return true
                }
                return false
            }
            return false
        }
        return false
    }
}
