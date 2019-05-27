package DB;

import java.sql.*;
//importing the Global object java file
import GlobalVariables.GlobalVarClass;

public class DBConnect
{
	GlobalVarClass objGlobal = new GlobalVarClass();

	public void Connect()
	{
			try {
			 Class.forName(objGlobal.strSQLite);
			 objGlobal.conn = DriverManager.getConnection(objGlobal.strDBPath);
			 objGlobal.conn.setAutoCommit(false);
			 System.out.println("Opened database successfully");
				} catch ( Exception e ) 
				{
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 System.exit(0);
				}
	}	
	//This method will execute the sql queries to fetch/update data.
		public void exeQuery(String query) throws SQLException
		{
			objGlobal.stmt = null;
			objGlobal.stmt = objGlobal.conn.createStatement();
			objGlobal.stmt.executeUpdate(query);
			objGlobal.stmt.close();
			objGlobal.conn.commit();
		}
}