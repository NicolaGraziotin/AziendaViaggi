package aziendaviaggi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {

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

            Connection conn = DriverManager.getConnection(connectionURL, sqlUser, sqlPassword);
            System.out.println("Connessione stabilita.");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
