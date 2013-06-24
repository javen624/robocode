package clustering

import settings.Closures

/**
 * Created with IntelliJ IDEA.
 * User: chermashentsev
 * Date: 20.06.13
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
class Distance {
    Point to
    double distance

    public Distance (Point from, Point to, distMethod = Closures.defaultDistanceMethod){
        this.to = to
        distance = distMethod(from, to)
    }

    @Override
    String toString() {
        return distance.toString()
    }
}
