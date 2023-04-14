/**
 * Position in 2D - space with numerical id of the pawn.
 */
public interface PawnPosition extends Position {
    /**
     * Unique id of the pawn
     *
     * @return id
     */
    public int pawnId();
}