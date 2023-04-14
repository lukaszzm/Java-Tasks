public interface Geometry {
    /**
     * Canvas size (2D - array). For simplicity, we assume that the array is square.
     *
     * @return size of canvas size
     */
    public int getSize();

    /**
     * The first coordinate (index) of the position from which the first segment is drawn.
     * The first segment starts at the position:
     * <tt>canvas[ getInitialFirstCoordinate() ][ getInitialSecondCoordinate() ]</tt>
     *
     * @return The first coordinate (index) of the position from which the first segment is drawn
     */
    public int getInitialFirstCoordinate();

    /**
     * The second coordinate (index) of the position from which the first segment is drawn.
     * The first segment starts at the position:
     * <tt>canvas[ getInitialFirstCoordinate() ][ getInitialSecondCoordinate() ]</tt>
     *
     * @return The second coordinate (index) of the position from which the first segment is drawn
     */
    public int getInitialSecondCoordinate();
}