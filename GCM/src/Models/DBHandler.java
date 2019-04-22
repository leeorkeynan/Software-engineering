package Models;

import Models.Interfaces.IClientDetails;
import Models.Interfaces.IDBData;
import Models.Interfaces.IDBHandler;
import Models.Interfaces.IMessage;

import java.sql.*;

import javax.net.ssl.SSLException;

public class DBHandler implements IDBHandler
{

    //region C'tor
    public DBHandler()
    {
        m_isConnected = false;
        Init();
    }
    //endregion

    //region Public Methods
    @Override
    public boolean HandleMassege(IMessage message) {
        return false;
    }

    @Override
    public void Init()
    {
        m_connection = null;
        m_statement = null;

        try
        {
            Class.forName(JDBC_DRIVER);
            m_connection = DriverManager.getConnection(DB_URL, USER, PASS);
            m_statement = m_connection.createStatement();
            m_isConnected = true;
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.out.println("SQLException: " + se.getMessage());
            System.out.println("SQLState: " + se.getSQLState());
            System.out.println("VendorError: " + se.getErrorCode());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (m_statement != null)
                {
                    m_statement.close();
                }
                if (m_connection != null)
                {
                    m_connection.close();
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }


    }

    @Override
    public boolean IsConnectedToDB()
    {
        return m_isConnected;
    }

    @Override
    public IDBData GetResoultsFromDB()
    {
        return m_resault;
    }

    @Override
    public int GetNumOfPurchases(int p_userID) {
        try {
            String getNumOfPurchasesQuery = "SELECT numOfPurchase FROM clients WHERE userId=" + p_userID;
            PreparedStatement statement = m_connection.prepareStatement(getNumOfPurchasesQuery);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                int numOfPurchase = results.getInt("numOfPurchase");
                statement.close();
                return numOfPurchase;
            }
            else
            {
                statement.close();
                return 0;
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.out.println("SQLException: " + se.getMessage());
            System.out.println("SQLState: " + se.getSQLState());
            System.out.println("VendorError: " + se.getErrorCode());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (m_statement != null) {
                    m_statement.close();
                }
                if (m_connection != null) {
                    m_connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public void IncreaseNumOfPurchases ( int p_userID, int p_value)
    {
        try
        {
            PreparedStatement statement = m_connection.prepareStatement("UPDATE clients SET numOfPurchase = numOfPurchase + "+ p_value + " WHERE userId = "+ p_userID);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.out.println("SQLException: " + se.getMessage());
            System.out.println("SQLState: " + se.getSQLState());
            System.out.println("VendorError: " + se.getErrorCode());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (m_statement != null) {
                    m_statement.close();
                }
                if (m_connection != null) {
                    m_connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return;
    }

    @Override
    public IClientDetails GetClientDetails ( int p_userID)
    {
        try {
            String getNumOfPurchasesQuery = "SELECT * FROM clients WHERE userId=" + p_userID;
            PreparedStatement statement = m_connection.prepareStatement(getNumOfPurchasesQuery);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                int numOfPurchase = results.getInt("numOfPurchase");
                ClientDetails details = new ClientDetails();
                details.userId = results.getInt("userId");
                details.FirstName = results.getString("firstName");
                details.LastName = results.getString("lastName");
                details.UserName = results.getString("userName");
                details.HasLicense = results.getBoolean("hasLicense");
                details.TimestampLicenseExp = results.getInt("timestampLicenseExp");
                details.NumOfPurchase = results.getInt("numOfPurchase");
                statement.close();
                return details;
            }
            else
            {
                statement.close();
                return null;
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.out.println("SQLException: " + se.getMessage());
            System.out.println("SQLState: " + se.getSQLState());
            System.out.println("VendorError: " + se.getErrorCode());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (m_statement != null) {
                    m_statement.close();
                }
                if (m_connection != null) {
                    m_connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }


    //endregion

    //region Private Methods


    //endregion

    //region Fields
    boolean m_isConnected;
    IDBData m_resault;
    private Connection m_connection;
    private Statement m_statement;
    private ResultSet m_result;

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    // update USER, PASS and DB URL according to credentials provided by the website:
    // https://remotemysql.com/
    // in future get those hardcoede string into separated config file.
    private final String DB = "n0LBO2gM5F";
    private final String DB_URL = "jdbc:mysql://remotemysql.com/"+ DB + "?useSSL=false";
    private final String USER = "n0LBO2gM5F";
    private final String PASS = "FYYphwfFm3";

    //endregion

}