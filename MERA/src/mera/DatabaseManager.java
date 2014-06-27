package mera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DatabaseManager 
{
	private static DatabaseManager instance;
	private Properties props;
	private ConnectionSource connectionSource;
	private Connection conn;
	private Properties getConf()
	{
		props = new Properties();
		try {
			props.load(new FileInputStream(new File("config/db.properties")));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return props;
	}
	
	private DatabaseManager() throws SQLException
	{
		getConf();
		if(props.containsKey("username") && props.containsKey("password"))
			connectionSource = new JdbcConnectionSource(
					props.getProperty("url"),
					props.getProperty("username"), 
					props.getProperty("password")
				);
		else
			connectionSource = new JdbcConnectionSource(props.getProperty("url"));
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(props.getProperty("url"),props.getProperty("username"),props.getProperty("password") );
	}
	
	public static DatabaseManager getInstance()
	{
		if(instance==null)
		{
			try {
				instance = new DatabaseManager();
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		return instance;
	}
	
	
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if (connectionSource != null)
			connectionSource.close();
		super.finalize();
	}
	
	public <T> List<T> getAll(Class<T> cl)
	{
		Dao<T, String> dao;
		try {
			dao = DaoManager.createDao(connectionSource, cl);
			return dao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<T>();
	}
	
	public boolean entityExists(String entity, String tableName)
	{
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM "+ tableName + " WHERE val='" + entity +"'");
			if (rs.next()) {
		        return true;
		    } else {
		        return false;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void disconnect()
	{
		connectionSource.closeQuietly();
	}
	
	public void connect() throws SQLException
	{
		if(props.containsKey("username") && props.containsKey("password"))
			connectionSource = new JdbcConnectionSource(
					props.getProperty("url"),
					props.getProperty("username"), 
					props.getProperty("password")
				);
		else
			connectionSource = new JdbcConnectionSource(props.getProperty("url"));
	}
	
	public void initdb()
	{
//		disconnect();
        ;
		try {
			Statement stat = conn.createStatement();
			stat.execute("DROP ALL OBJECTS");
			stat.execute("runscript from 'config/init/db.h2.sql'");
//			ResultSet rs = stat.executeQuery("SELECT * FROM category where category='NEP'");
//			if (rs.next()) {
//		        rs.last();
//		        System.out.println("total rows is : " + rs.getRow());
//		    } else {
//		        System.out.println("No Data");
//		    }
//			while (rs.next()) {
//	            System.out.println(rs.getString("val"));
//	        }
//			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
