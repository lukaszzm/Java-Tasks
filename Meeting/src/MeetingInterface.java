import java.util.List;
import java.util.Set;

/**
 * Interface of the program that determines the position of the pawn.
 */
public interface MeetingInterface {
    /**
     * The method adds a list of positions where the pawns are initially located.
     * Since the order of the pawns determines the order of movement a list is used.
     *
     * @param positions Position of pawn
     */
    public void addPawns(List<PawnPosition> positions);

    /**
     * The method sets up a rallying point - this is the point the pawns are trying to reach.
     *
     * @param meetingPointPosition Position of rallying point
     */
    public void addMeetingPoint(Position meetingPointPosition);

    /**
     * Movement of pawns. The method ends when none of the pawns can move closer to the rallying point.
     */
    public void move();

    /**
     * The method returns the final position of all pawns.
     *
     * @return Final position of all pawns
     */
    public Set<PawnPosition> getAllPawns();

    /**
     * The method returns position of pawns that are adjacent to the pawn with the given pawnId.
     *
     * @param pawnId id of the pawn
     * @return Neighbors of the given pawn
     */
    public Set<PawnPosition> getNeighbours(int pawnId);
}
