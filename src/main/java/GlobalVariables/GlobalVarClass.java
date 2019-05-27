package GlobalVariables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GlobalVarClass 
{
	public Statement stmt;
	public Connection conn;
	public ResultSet rs;
	public String strSQLite = "org.sqlite.JDBC";
	//Path for IMDB database
	public String strDBPath = "jdbc:sqlite:C:\\Users\\lenovo\\eclipse-workspace\\junit\\IMDB.sqlite";
	public String strQuery;
	public String strFilePath = "C:\\Users\\lenovo\\eclipse-workspace\\junit\\MovieList.csv";

}
