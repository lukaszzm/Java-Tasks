import java.sql.SQLException;

/**
 * SentenceGenerator interface
 *
 */
public interface SentenceGenerator {
    /**
     * Method transfers the location of the SQLite database file.
     *
     * @param filename SQLite database file
     */
    public void setDatabaseFile(String filename) throws SQLException;

    /**
     * Method generates a sentence
     *
     * @param sentenceID id of Sentence
     * @return Generated sentence
     */
    public String generateSentence(int sentenceID);
}
