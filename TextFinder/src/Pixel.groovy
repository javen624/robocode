/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 19.06.13
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
class Pixel {
    int red;
    int green;
    int blue;
    int x;
    int y;

    static final Pixel fone = new Pixel(red:215,green:234, blue:249)
    static final Pixel black = new Pixel(red: 0, green: 0, blue: 0)

    @Override
    boolean equals(Object obj) {
        Pixel p = (Pixel) obj
        if (
                Math.abs(p.blue - this.blue) < 20 &&
                Math.abs(p.red - this.red) < 20 &&
                Math.abs(p.green - this.green) < 20
        )
            return true
        else
            return false
    }
}
