package mera;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mera.data.Pattern;

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
	
	protected <T> void insertAll(T[] items)
	{
		Dao<T, String> dao;
		try {
			dao = DaoManager.createDao(connectionSource, items[0].getClass());
			for(T item : items)
			{
				dao.create(item);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
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
	
	public boolean entityBeginsWith(String entity, String tableName)
	{
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM "+ tableName + " WHERE val LIKE'" + entity +"%'");
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
		try {
			Statement stat = conn.createStatement();
			stat.execute("DROP ALL OBJECTS");
			stat.execute("runscript from 'config/init/db.h2.sql'");
			
			initPatterns();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Initiating DB failed");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void initPatterns()
	{
		insertAll(getPatternsFromFile("CERTAIN"));
		insertAll(getPatternsFromFile("POSSIBLE"));
		insertAll(getPatternsFromFile("IMPOSSIBLE"));
	}
	
	private Pattern[] getPatternsFromFile(String type)
	{
		String[] lines = FileInterface.getInstance().getLines("config/init/patterns/"+type.toLowerCase()+"_patterns");
		Pattern[] p = new Pattern[lines.length];
		for(int i = 0 ; i < lines.length ; i++)
		{
			p[i] = new Pattern(lines[i], type);
		}
			
		
		return p;
	}
	
}
