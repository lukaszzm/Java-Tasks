/**
 * Archivizer interface
 */
public interface ArchivizerInterface {

    /**
     * Compress a directory named dir into a filename file.
     *
     * @param dir      directory with data
     * @param filename output file
     * @return         size (in bytes) of output file
     */
    public int compress(String dir, String filename);

    /**
     * Decompress a filename file into directory named dir.
     *
     * @param filename file with archive
     * @param dir      directory where the files will go
     */
    public void decompress(String filename, String dir);
}