public interface Vector {
    /**
     * Method returns assigned value at position idx.
     *
     * @param idx position
     * @return value assigned at position idx
     */
    public int getValue(int idx);

    /**
     * actual Vector size
     *
     * @return vector size
     */
    public int getSize();
}
