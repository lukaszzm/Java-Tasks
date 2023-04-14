import java.util.*;
import java.util.stream.Collectors;

public class Lines implements LinesInterface {
    Set<Point> points;
    Set<Segment> segments;

    @Override
    public void addPoints(Set<Point> points) {
        this.points = points;
    }

    @Override
    public void addSegments(Set<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public List<Segment> findConnection(Point start, Point end) {
        Set<Point> usedPoints = new HashSet<>();
        List<Segment> connection = new LinkedList<>();

        if(checkConnection(start, end, usedPoints, connection)) connection.clear();

        return connection;
    }

    public boolean checkConnection(Point point, Point end, Set<Point> usedPoints, List<Segment> connection) {
        usedPoints.add(point);

        if(Objects.equals(point.getName(), end.getName())) return false;

        Set<Segment> neighbours = getNeighboursSegments(point);

        for (Segment segment : neighbours) {
            Point nextPoint = null;

            if (!usedPoints.contains(segment.getEndpoint1())) nextPoint = segment.getEndpoint1();
            if (!usedPoints.contains(segment.getEndpoint2())) nextPoint = segment.getEndpoint2();

            if (nextPoint != null) {
                connection.add(segment);
                if(checkConnection(nextPoint, end, usedPoints, connection)) {
                    connection.remove(segment);
                } else {
                    return false;
                }
            }

        }
        return true;
    }


    public Set<Segment> getNeighboursSegments(Point point) {
        return segments.stream().filter(el -> (el.getEndpoint1() == point) || (el.getEndpoint2() == point)).collect(Collectors.toSet());
    }

    @Override
    public Map<Point, Set<Segment>> getMapEndpointToSegments() {
        Map<Point, Set<Segment>> map = new HashMap<>();
        HashSet<Segment> repeatations = new HashSet<>();

        for (Point i : points) {
            map.put(i, getNeighboursSegments(i));
        }

        return map;
    }

    public Set<Point> getNeighboursWithoutPrev(Point point, Point start, Point prevPoint) {
        Set<Point> points = new HashSet<>();
        Set<Segment> neighbours = segments.stream().filter(el -> (el.getEndpoint1() == point) || (el.getEndpoint2() == point)).collect(Collectors.toSet());

        for (Segment i : neighbours) {
            if (i.getEndpoint2() == point) {
                if (i.getEndpoint1() != start && i.getEndpoint1() != prevPoint) points.add(i.getEndpoint1());
            } else {
                if (i.getEndpoint2() != start && i.getEndpoint2() != prevPoint) points.add(i.getEndpoint2());
            }
        }
        return points;
    }

    public void getPointsByLength(Point start, Point prevPoint,  Point point, int count, Set<Point> finalPoints) {
        Set<Point> points = getNeighboursWithoutPrev(point, start, prevPoint);

        if (count == 1) {
            finalPoints.addAll(points);
        } else {
            for (Point i : points) {
                getPointsByLength(start, point, i, count - 1, finalPoints);
            }
        }
    }

    @Override
    public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints() {
        Map<Point, Map<Integer, Set<Point>>> map = new HashMap<>();

        for (Point p : points) {
            Map<Integer, Set<Point>> insideMap = new HashMap<>();
            for (int i = 1; i <= 4; i++) {
                Set<Point> finalPoints = new HashSet<>();
                getPointsByLength(p, null, p, i , finalPoints);
                insideMap.put(i, finalPoints);
            }
            map.put(p, insideMap);
        }
        return map;
    }
}