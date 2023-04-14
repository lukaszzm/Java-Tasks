import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Meeting implements MeetingInterface {
    public List<PawnPosition> positions;
    public Position meetingPoint;

    @Override
    public void addPawns(List<PawnPosition> positions) {
        this.positions = positions;
    }

    @Override
    public void addMeetingPoint(Position meetingPointPosition) {
        this.meetingPoint = meetingPointPosition;
    }

    @Override
    public Set<PawnPosition> getAllPawns() {
        return new HashSet<>(positions);
    }

    @Override
    public Set<PawnPosition> getNeighbours(int pawnId) {
        List<PawnPosition> element = positions.stream()
                .filter(item -> item.pawnId() == pawnId)
                .toList();

        int x = element.get(0).x();
        int y = element.get(0).y();

        List<PawnPosition> neighbours = positions.stream()
                .filter(item -> item.pawnId() != pawnId &&(Math.abs(item.x() - x) <= 1) && (Math.abs(item.y() - y) <= 1))
                .toList();

        return new HashSet<>(neighbours);
    }

    @Override
    public void move() {
        int round = 1;
        int moves = 1;
        while(moves > 0) {
            moves = 0;
            if ((round % 2) == 1) {
                for (int i = 0; i < positions.size(); i++) {
                    if (movePawn(positions.get(i), i)) {
                        moves++;
                    }
                }
            } else {
                for (int i = positions.size() - 1; i > -1; i--) {
                    if (movePawn(positions.get(i), i)) {
                        moves++;
                    }
                }
            }
            round++;
        }
    }

    public boolean movePawn(PawnPosition pawn, int index) {
        int pawnId = pawn.pawnId();
        int x = pawn.x();
        int y = pawn.y();
        int dx = meetingPoint.x() - x;
        int dy = meetingPoint.y() - y;

        if (dx == dy && dy == 0) {
            return false;
        }
        if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    x++;
                } else {
                    x--;
                }
            } else {
                if (dy > 0) {
                    y++;
                } else {
                    y--;
                }
        }
        if(!isEmptyPosition(x,y)) {
            return false;
        }
        positions.set(index, new PawnPosition2D(pawnId, x, y));
        return true;
    }

    public boolean isEmptyPosition(int x, int y) {
        List<PawnPosition> element = positions.stream()
                .filter(item -> item.x() == x && item.y() == y)
                .toList();
        return element.size() == 0;
    }


}