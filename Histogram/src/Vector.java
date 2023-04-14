/**
 * Object representation of vector
 */
public interface Vector {
    /**
     * Returns the value stored at the idx position.
     * Allowed idx values from 0 to getSize() - 1.
     *
     * @param idx position
     * @return value stored at the idx position
     */
    public int getValue(int idx);

    /**
     * Size of vector.
     *
     * @return vector size
     */
    public int getSize();
}