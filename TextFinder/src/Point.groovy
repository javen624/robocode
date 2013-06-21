/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 19.06.13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
class Point {
    int x
    int y

    static Comparator<Point>  getComparator(){
        return new Comparator<Point>() {
            @Override
            int compare(Point o1, Point o2) {
                if (o1.y < o2.y || (o1.y == o2.y && o1.x < o2.x))
                    return -1
                else if (o1.y != o2.y && o1.x != o2.x)
                    return 1
                else return 0
            }
        }
    }
    @Override
    String toString() {
        return x + " " + y
    }
}
