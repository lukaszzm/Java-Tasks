public interface SimpleDrawing {
    /**
     * The method which allows to transfer an object Geometry.
     *
     * @param input object Geometry
     */
    public void setCanvasGeometry(Geometry input);

    /**
     * The method orders the drawing of a single section.
     * The beginning of the first segment is indicated by the Geometry object.
     * The next segment starts where the previous one ended.
     * The first point of the new segment overwrites the last point of the previous one.
     *
     * @param segment - Segment to be drawn
     */
    public void draw(Segment segment);

    /**
     * The method returns 2D - array which represents actual state of Picture,
     * before assignment of Geometry, method returns null.
     *
     * @return 2D - array contains picture
     */
    public int[][] getPainting();

    /**
     * The method cleans canvas (sets all values to 0)
     */
    public void clear();
}