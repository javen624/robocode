/**
 * Created with IntelliJ IDEA.
 * User: chermashentsev
 * Date: 20.06.13
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
class Pixel {
    int x
    int y
    int red
    int green
    int blue
    static def equalsClosure
    final static BLACK_PIXEL = new Pixel(red: 0, green: 0, blue: 0)


    static{
        BLACK_PIXEL.metaClass.equals = {Object obj ->
            Pixel p = (Pixel) obj
            if (
                    Math.abs(p.blue - blue) < 25 &&
                    Math.abs(p.red - red) < 25 &&
                    Math.abs(p.green - green) < 25
            )
                return true
            else
                return false
        }
    }



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
        if (p.x == x && p.y == y)
            return true
        return false

    }
}
