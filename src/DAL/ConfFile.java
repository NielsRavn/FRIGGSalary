package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfFile {

    private final String PROPERTIES_FILE_URL = "FireAndRescueDatabaseConfig.properties", DATABASE_ADDRESS_KEY = "DatabaseAddress",
            DATABASE_NAME_KEY = "DatabaseName", DATABASE_USER_KEY = "DatabaseUser", DATABASE_PASSWORD_KEY = "DatabasePassword";
    
    private Properties prop;
    private static ConfFile instance = null;
    
    /**
     * gives the instance of the config file, so that the may only ever be one instance
     * @return confFile instance.
     * @throws FileNotFoundException if the config file does not exist
     * @throws IOException if the config file is formated wrong. 
     */
    public static ConfFile getInstance() throws FileNotFoundException, IOException
    {
        if (instance == null)
        {
            instance = new ConfFile();
        }
        return instance;
    }
    
    /**
     * creates a new config file and loads the properties if it exists
     * @throws FileNotFoundException if the config file does not exist
     * @throws IOException if the config file is formated wrong. 
     */
    private ConfFile() throws FileNotFoundException, IOException{
        prop = new Properties();
        if(configFileExists()){
            loadProperties();
        }
    }
    
    /**
     * checks if the config file exitsts
     * @return true if the config file exists false otherwise.
     */
    public boolean configFileExists(){
        return new File(PROPERTIES_FILE_URL).exists();
    }
    
    /**
     * 
     * @return the address of the server as a string
     */
    public String getServerName() {
        return prop.getProperty(DATABASE_ADDRESS_KEY);
    }

    /**
     * 
     * @return the name of the database
     */
    public String getDatabseName() {
        return prop.getProperty(DATABASE_NAME_KEY);
    }

    /**
     * 
     * @return the user login name
     */
    public String getDBUser() {
        return prop.getProperty(DATABASE_USER_KEY);
    }
    
    /**
     * 
     * @return the password for the database user.
     */
    public String getDBPassword() {
        return prop.getProperty(DATABASE_PASSWORD_KEY);
    }
    
    /**
     * sets the addres of the databse server
     * @param serverAddress the new databse server address
     * @throws FileNotFoundException if the config file does not exist
     * @throws IOException if the config file is formated wrong.
     */
    public void setServerAddress(String serverAddress) throws FileNotFoundException, IOException {
        prop.setProperty(DATABASE_ADDRESS_KEY, serverAddress);
        saveProperties();
    }

    /**
     * sets the name of the databse
     * @param databaseName the new database name
     * @throws FileNotFoundException if the config file does not exist
     * @throws IOException if the config file is formated wrong.
     */
    public void setDatabaseName(String databaseName) throws FileNotFoundException, IOException {
        prop.setProperty(DATABASE_NAME_KEY, databaseName);
        saveProperties();
    }
    
    /**
     * sets the user name of the databse
     * @param dbUser the new user name
     * @throws FileNotFoundException if the config file does not exist
     * @throws IOException if the config file is formated wrong.
     */
    public void setDbUser(String dbUser) throws FileNotFoundException, IOException {
        prop.setProperty(DATABASE_USER_KEY, dbUser);
        saveProperties();
    }
    
    /**
     * sets the password for the databse
     * @param dbPassword the new password
     * @throws FileNotFoundException if the config file does not exist
     * @throws IOException if the config file is formated wrong.
     */
    public void setDbPassword(String dbPassword) throws FileNotFoundException, IOException {
        prop.setProperty(DATABASE_PASSWORD_KEY, dbPassword);
        saveProperties();
    }
    
    /**
     * creates the configfile with all the elements, used for initial setup.
     * @param databaseAddress databse server address
     * @param databaseName databse name
     * @param databaseUser database user
     * @param databasePassword database password
     * @param musicFolderPath file path to the music folder
     * @throws FileNotFoundException if the config file does not exist
     * @throws IOException if the config file is formated wrong. 
     */
    public void createConfigFile(String databaseAddress, String databaseName, String databaseUser, String databasePassword) throws FileNotFoundException, IOException{
        prop.setProperty(DATABASE_ADDRESS_KEY, databaseAddress);
        prop.setProperty(DATABASE_NAME_KEY, databaseName);
        prop.setProperty(DATABASE_USER_KEY, databaseUser);
        prop.setProperty(DATABASE_PASSWORD_KEY, databasePassword);
        saveProperties();
    }
    
    /**
     * saves the properties file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void saveProperties() throws FileNotFoundException, IOException{
        FileOutputStream fops = new FileOutputStream(PROPERTIES_FILE_URL);
        prop.store(fops, "Properties for Projeckt kraken");
        fops.close();
    }

    /**
     * loads the properties file from memmory.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadProperties() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(PROPERTIES_FILE_URL);
        prop.load(fis);
        fis.close();
    }
}
