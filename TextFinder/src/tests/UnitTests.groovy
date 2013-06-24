package tests

import dataLoader.ImageFromFileLoader
import dataLoader.ImgFileFilter
import org.junit.Test
import settings.Setting

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 23.06.13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */

class UnitTests {
    @Test
    void testImageFilter(){
        assert new File(Setting.TEST_IMAGES_ROOT_DIR).listFiles(new ImgFileFilter()).size() == 20
    }
}
