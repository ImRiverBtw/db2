package nl.hva.ict.data.MySQL;

import nl.hva.ict.MainApplication;
import nl.hva.ict.data.Data;
import nl.hva.ict.data.Identifable;

import java.sql.*;
import java.util.Properties;

/**
 * Een abstracte class om de verbinding te maken met de MySQL server.
 * @param <T> Gescchikt voor alle models.
 * @author HBO-ICT
 */
public abstract class MySQL<T extends Identifable> implements Data<T> {

    protected Connection connection;
    private Properties properties;

    /**
     * De constructor
     */
    public MySQL() {
        connect();
    }


    /**
     * Met deze class worden de DB credentials opgehaald en in een properties  object gezet.
     * @return
     */
    private Properties getProperties() {

        // Als er geen properties object is maak hem dan aan
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", MainApplication.getMysqlUsername());
            properties.setProperty("password", MainApplication.getMysqlPassword());
        }
        return properties;
    }

    // connect database
    private void connect() {
        // Heb je wel je credentials ingevoerd in MainApplication?
       if (MainApplication.getMysqlHost().equals("") || MainApplication.getMysqlUsername().equals("") || MainApplication.getMysqlPassword().equals(""))
        return;

       // Maak alleen verbinding als deze niet bestaat.
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection(MainApplication.getMysqlHost(), getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Kan geen verbinding maken : " + e.toString());
            }
        }
    }

    /**
     * Sluit MySQL verbinding
     */
    public void disconnect() {
        // Alleen als er een verbinding is. Anders sluit je een reeds gesloten verbinding en dat geeft gedoe.
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Methode om een PreparedStatement (met de ? in de query) te maken. Deze methodes worden in de child (sub) class aangeroepen
     * @param sql Je SQL code
     * @return Een resultset met je data
     * @throws SQLException Een error als het niet gaat.
     */
    public PreparedStatement getStatement(String sql) throws SQLException {
        if (this.connection == null) {
            connect();
        }
        return connection.prepareStatement(sql);
    }


    /**
     * Methode om een update PreparedStatement (met de ? in de query) te maken. Deze methodes worden in de child (sub) class aangeroepen
     * @param ps je SQL code
     * @throws SQLException Een error als het niet gaat.
     */
    public void executeUpdatePreparedStatement(PreparedStatement ps) throws SQLException {
        if (this.connection == null) {
            connect();
        }
        ps.executeUpdate();
    }



    /**
     * Methode om een select PreparedStatement (met de ? in de query) te maken. Deze methodes worden in de child (sub) class aangeroepen
     * @param ps je SQL code
     * @throws SQLException Een error als het niet gaat.
     */
    public ResultSet executeSelectPreparedStatement(PreparedStatement ps) throws SQLException {
        if (this.connection == null) {
            connect();
        }
        return ps.executeQuery();
    }

}
