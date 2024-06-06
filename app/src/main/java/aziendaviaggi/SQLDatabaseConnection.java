package aziendaviaggi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class represents a SQL database connection.
 */
public class SQLDatabaseConnection {

    private static Statement statement;

    /**
     * Connects to the SQL database.
     *
     * @throws ClassNotFoundException if the SQL Server driver class is not found.
     * @throws SQLException           if a database access error occurs.
     */
    public void connect() {
        try {
            final String hostname = "localhost";
            final String port = "1433";
            final String sqlInstanceName = "PC-Nicola\\SQLEXPRESS";
            final String sqlDatabase = "AziendaViaggi";
            final String sqlUser = "sa";
            final String sqlPassword = "nicola";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            final String connectionURL = "jdbc:sqlserver://" + hostname + ":" + port + ";instance=" + sqlInstanceName
                    + ";databaseName=" + sqlDatabase + ";encrypt=true;trustServerCertificate=true";

            final Connection connection = DriverManager.getConnection(connectionURL, sqlUser, sqlPassword);
            statement = connection.createStatement();
            System.out.println("Connessione al DataBase stabilita.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the statement object associated with the database connection.
     *
     * @return the statement object.
     */
    public static Statement getStatement() {
        return statement;
    }
}
