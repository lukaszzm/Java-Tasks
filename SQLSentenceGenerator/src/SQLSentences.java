import java.sql.*;

class Person {
    private final String name;
    private final Integer sex;

    Person(String name, Integer sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public boolean isFemale() {
        return sex == 0;
    }
}

public class SQLSentences implements SentenceGenerator {
    Connection connection = null;

    @Override
    public void setDatabaseFile(String filename) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generateSentence(int zdanieID) {
        if (connection == null) return null;

        Integer[] IDs = getIDs(zdanieID);
        if (IDs == null) return null;

        Person person = getPerson(IDs[0]);
        String action = getAction(IDs[1]);
        String item = getItem(IDs[2]);

        if (person == null || action == null || item == null) return null;

        if (person.isFemale()) action = action + "a";

        return person.getName() + " " + action + " " + item + ".";
    }

    private Integer[] getIDs(int sentenceID) {
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT NameID, ActionID, ItemID FROM Sentence WHERE SentenceID = " + sentenceID);
            int nameID = rs.getInt("NameID");
            int actionID = rs.getInt("ActionID");
            int itemID = rs.getInt("ItemID");
            return new Integer[] { nameID, actionID, itemID};
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Person getPerson(int nameID) {
        try {
            String SQLquery = "SELECT Name, Sex FROM Name WHERE NameID = " + nameID;
            ResultSet rs = connection.createStatement().executeQuery(SQLquery);

            int sex = rs.getInt("Sex");
            String name = rs.getString("Name");

            return new Person(name, sex);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getAction(int actionID) {
        try {
            String SQLquery = "SELECT Name FROM Action WHERE ActionID = " + actionID;
            ResultSet rs = connection.createStatement().executeQuery(SQLquery);
            return rs.getString("Name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getItem(int itemID) {
        try {
            String SQLquery = "SELECT Name FROM Item WHERE ItemID = " + itemID;
            ResultSet rs = connection.createStatement().executeQuery(SQLquery);
            return rs.getString("Item");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
