package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

public abstract class DatabaseConnection {
    
    SQLServerDataSource ds;
    
    /**
     * Creates a new databaseConnection object
     * @throws FileNotFoundException if the config file doesn't exist
     * @throws IOException if an I/O exception of some sort has occurred.
     */
    public DatabaseConnection() throws FileNotFoundException, IOException{
        ConfFile cf = ConfFile.getInstance();
        ds = new SQLServerDataSource();
        ds.setServerName(cf.getServerName());
        ds.setDatabaseName(cf.getDatabseName());
        ds.setUser(cf.getDBUser());
        ds.setPassword(cf.getDBPassword());
    }
    
    /**
     * gets a connection to the database, REMEMBER to close it!
     * @return a connection to the database
     * @throws SQLServerException if te connection cant be made.
     */
    protected Connection getConnection() throws SQLServerException{
        return ds.getConnection();
    }
    
    
}