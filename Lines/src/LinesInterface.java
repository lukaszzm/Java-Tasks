import java.util.*;

public interface LinesInterface {

    /**
     * Point
     */
    public interface Point {
        /**
         * Name of the point, each point have unique name.
         *
         * @return Name of the point
         */
        public String getName();
    }

    /**
     * Segment. The segment has no direction.
     */
    public interface Segment {
        /**
         * First endpoint of the segment.
         *
         * @return First endpoint of the segment
         */
        public Point getEndpoint1();

        /**
         * Second endpoint of the segment.
         *
         * @return Second endpoint of the segment
         */
        public Point getEndpoint2();
    }

    /**
     * Metoda pozwala na dodanie zbioru punktĂłw.
     * Method allows to add set of points.
     *
     * @param points Set of points
     */
    public void addPoints(Set<Point> points);

    /**
     * Metoda pozwala na dodanie zbioru odcinków łączących punkty.
     * Method allows to add set of segments.
     *
     * @param segments Set of segments
     */
    public void addSegments(Set<Segment> segments);

    /**
     * The method searches for a connection between a pair of points.
     * Any correct connection given pair and without a loop will be considered correct.
     * If there is no connection the method returns a list of size 0.
     *
     * @param start Start point
     * @param end   End point
     * @return List of segments leading from start to end.
     */
    public List<Segment> findConnection(Point start, Point end);

    /**
     * The method generates a map in which the key is a point.
     * The point points to a set of segments
     * in which one of the endpoints is that point.
     *
     * @return Map of links between points and segments
     */
    public Map<Point, Set<Segment>> getMapEndpointToSegments();

    /**
     * The method generates a map whose key is a point.
     * The point is the starting point of the paths. In the map indicated by the point,
     * there are to be points that can be reached through a route containing: 1, 2, 3 or 4 segments.
     * If the route of a certain length does not exist the set of points is an empty set (0).
     *
     * @return Map of the links between points with another points
     */
    public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints();

}