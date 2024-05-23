package aziendaviaggi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {

    private static Statement statement;

    public void connect() throws ClassNotFoundException, SQLException {
        try {
            String hostname = "localhost";
            String port = "1433";
            String sqlInstanceName = "PC-Nicola\\SQLEXPRESS";
            String sqlDatabase = "AziendaViaggi";
            String sqlUser = "sa";
            String sqlPassword = "nicola";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionURL = "jdbc:sqlserver://" + hostname + ":" + port + ";instance=" + sqlInstanceName
                    + ";databaseName=" + sqlDatabase+";encrypt=true;trustServerCertificate=true";

            Connection connection = DriverManager.getConnection(connectionURL, sqlUser, sqlPassword);
            statement = connection.createStatement();
            System.out.println("Connessione stabilita.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement() {
        return statement;
    }
}
