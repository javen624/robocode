package clustering
/**
 * Created with IntelliJ IDEA.
 * User: chermashentsev
 * Date: 20.06.13
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
class Pixel {
    Integer x
    Integer y
    Integer red
    Integer green
    Integer blue
    static def equalsClosure
    final static BLACK_PIXEL = new Pixel(red: 0, green: 0, blue: 0)






    static setEqualsClosure(Closure closure){
        equalsClosure = closure
    }

    @Override
    String toString() {
        return "x = {$x} y = {$y}"
    }

    @Override
    boolean equals(Object obj) {
        Pixel p = (Pixel) obj
        if (
                Math.abs(p.blue - this.blue) <  settings.Setting.COLOR_OFFSET &&
                        Math.abs(p.red - this.red) < settings.Setting.COLOR_OFFSET &&
                        Math.abs(p.green - this.green) < settings.Setting.COLOR_OFFSET
        )
            return true
        else
            return false
    }
}
