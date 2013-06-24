package clustering
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
        int maxX = -1, maxY = -1, minX=Integer.MAX_VALUE, minY = Integer.MAX_VALUE
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
    static def createClusters(ArrayList<Point> blackPoints, distances){
        def clusters = []
        def point
        while (!blackPoints.empty){
            clustering.Cluster c = new clustering.Cluster()
            point = blackPoints.get(blackPoints.size()-1)
            blackPoints.remove(blackPoints.size() - 1)
            blackPoints.trimToSize()

            c.add(point)
            findNeighbors(point, c, distances, blackPoints, 1)
            c.findExtreme()
            println "clustering.Cluster added"
            clusters.add(c)
        }
        return clusters
    }
    static def findNeighbors(Point point, Cluster cluster, HashMap<Pixel, Distance[]> distances, ArrayList<Point> points, k){

        //println k
        distances.get(point).each {
            def a = it.distance < 50, b = it.distance > 0, c = !cluster.points.contains(it.to)
            //  if (c)
            //        println c
            if (a && b && c){
                cluster.add(it.to)
                points.remove(it.to)
                findNeighbors(it.to, cluster, distances, points, ++k)
            }
        }

    }

    @Override
    String toString() {
        "size: $points.size points: $points left point $p1 right point $p2\n"
    }
}