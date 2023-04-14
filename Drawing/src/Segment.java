public interface Segment {
    /**
     * Direction of drawing a segment. The direction is determined by indicating
     * the increment/decrement of the corresponding coordinate.
     * canvas[ first_index ][ second_index ]
     * </tt>
     *
     * <ul>
     * <li> 1 - incrementing first index
     * <li> 2 - incrementing second index
     * <li> -1 - decrementing first index
     * <li> -2 - decrementing second index
     * <li> Other values should be ignored.
     * </ul>
     *
     * @return Integer represents a direction
     */
    public int getDirection();

    /**
     * The method returns length of the segment. The length is a number of pixels included in the segment.
     *
     * @return Number of pixels included in the segment
     */
    public int getLength();

    /**
     * The method returns integer that represents a color of the segment being drawn.
     *
     * @return Color
     */
    public int getColor();
}