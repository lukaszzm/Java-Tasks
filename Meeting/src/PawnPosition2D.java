import java.util.List;

// https://www.baeldung.com/java-record-keyword

/**
 * Position in 2D - space with numerical id of the pawn.
 */
public record PawnPosition2D(int pawnId, int x, int y) implements PawnPosition {
}
