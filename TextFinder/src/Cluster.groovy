/**
 * Created with IntelliJ IDEA.
 * User: chermashentsev
 * Date: 20.06.13
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
class Cluster {
    def points = [] as ArrayList;
    Point p1;
    Point p2;

    void add(Point p){
        points.add(p)
    }

    void findExtreme(){
        int maxX = -1, maxY = -1, minX=10000, minY = 10000
        points.each{it ->
            if (it.x > maxX)
                maxX = it.x
            if (it.y > maxY)
                maxY = it.y
            if (it.x < minX)
                minX = it.x
            if (it.y < minY)
                minY = it.y
        }
        p1 = new Point(x: minX, y: minY)
        p2 = new Point(x: maxX, y: maxY)
    }

    @Override
    String toString() {
        "size: $points.size points: $points left point $p1 right point $p2\n"
    }
}