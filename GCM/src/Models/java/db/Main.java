package Models.java.db;

import Models.Interfaces.IDBData;
import Models.Interfaces.IDBHandler;
import Models.Interfaces.IMessage;

import java.sql.*;

import javax.net.ssl.SSLException;

public class Main {
	static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	// update USER, PASS and DB URL according to credentials provided by the website:
	// https://remotemysql.com/
	// in future get those hardcoede string into separated config file.
	static private final String DB = "SNww8MwQQJ";
	static private final String DB_URL = "jdbc:mysql://remotemysql.com/"+ DB + "?useSSL=false";
	static private final String USER = "SNww8MwQQJ";
	static private final String PASS = "UssP2rFvtK";

	public static void main(String[] args) throws SSLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			//region Task 5.a

			//Query and parameters
			double _newPriceValue = 999;
			int _flightNumber = 387;
			String _updatePriceQuery = "UPDATE flights SET price = ? WHERE num = ?";

			// update flights table:
			PreparedStatement _priceuUpdateStatment = conn.prepareStatement(_updatePriceQuery);
			_priceuUpdateStatment.setDouble(1,_newPriceValue);
			_priceuUpdateStatment.setInt(2,_flightNumber);
			_priceuUpdateStatment.executeUpdate();
			SQLWarning warn = _priceuUpdateStatment.getWarnings();
			if (warn != null)
			{
				System.err.println("warning: "+warn.getMessage());
			}
			_priceuUpdateStatment.close();

			//endregion

			//region Task 5.b

			//get record from table
			String _getFlightQuery = "SELECT price FROM flights WHERE num="+_flightNumber;
			PreparedStatement _getPriceStatment = conn.prepareStatement(_getFlightQuery);
			ResultSet rs = _getPriceStatment.executeQuery();
			if(rs.next())
			{
				double _updatedPrice = rs.getDouble("price");
				//print update results
				System.out.println("Flight no. " + _flightNumber + " price was up updated to " + _updatedPrice );
			}
			_getPriceStatment.close();
			//endregion

			System.out.println("\t============");

			//region Task 5.c

			Statement _updateStatment = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet _resultSet = _updateStatment.executeQuery("SELECT * FROM flights WHERE distance > 1000");
			warn = _updateStatment.getWarnings();
			if (warn != null)
			{
				System.err.println("warning: " + warn.getMessage());
			}
			while(_resultSet.next())
			{

				_resultSet.updateDouble("price",_resultSet.getDouble("price")+50);
				_resultSet.updateRow();

			}
			_resultSet.first();
			do
			{
				System.out.println("\tnumber:\t" + _resultSet.getInt("num")+
						"\tdistance:\t" + _resultSet.getInt("distance")+
						"\tprice:\t" + _resultSet.getDouble("price"));
			} while (_resultSet.next());

			_updateStatment.close();

			//endregion
			System.out.println("\t============");

			//region Task 5.d
			PreparedStatement _priceUpdatePS = conn.prepareStatement("UPDATE flights SET price = price + 50 WHERE distance > 1000");
			_priceUpdatePS.executeUpdate();
			rs = _priceUpdatePS.executeQuery("SELECT * FROM flights WHERE distance > 1000");
			warn = _priceUpdatePS.getWarnings();
			if (warn != null)
			{
				System.err.println("warning: " + warn.getMessage());
			}
			while(rs.next())
			{
				System.out.println("\tnumber:\t" + rs.getInt("num")+
						"\tdistance:\t" + rs.getInt("distance")+
						"\tprice:\t" + rs.getDouble("price"));
			}

			_priceUpdatePS.close();

			//endregion
			System.out.println("\t============");

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
            System.out.println("SQLState: " + se.getSQLState());
            System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}