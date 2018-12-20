package com.sullivan.jez.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBC 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sakila";
	
	static final String USER = "root";
	static final String PASS = "mysql";
	
	private Connection conn = null;
	private Statement stmt = null;
	
	private void accessDB()
	{
		try 
		{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			System.out.println("Connection Established");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean create(String table, String field1, String value1)
	{
		accessDB();
		System.out.println("Create");
		
		boolean successful;
		
		try 
		{
			stmt = conn.createStatement();
			String sql = "INSERT INTO "+ table + " (" + field1 + ") ";
			sql = sql + "VALUES ('" + value1 + "')"; 
			stmt.executeUpdate(sql);
			successful = true;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			successful = false;
		} finally
		{
			close();
		}
		
		return successful;
		//close
	}
	
	public List<Actor> read(String table)
	{
		accessDB();
		System.out.println("Read");
		List<Actor> actors = new ArrayList<Actor>();
		try
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM " + table + " limit 5";
			ResultSet rs = stmt.executeQuery(sql);
			
			//TODO take list of columns?
			while(rs.next())
			{
				int id = rs.getInt("actor_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String last_update = rs.getString("last_update");
				
				Actor a = new Actor(id,first_name,last_name,last_update);
				actors.add(a);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			close();
		}
		return actors;
	}
	
	public boolean update(String table, String clause, String cValue, String newField, String newValue)
	{
		accessDB();	
		System.out.println("Update");
		
		boolean successful;
		
		try
		{
			stmt = conn.createStatement();
			String sql = "UPDATE " + table + " SET " + newField + " = '" + newValue;
			sql = sql + "' WHERE " + clause + " = '" + cValue + "'";
			stmt.executeQuery(sql);
			successful = true;
		} catch(SQLException e)
		{
			e.printStackTrace();
			successful = false;
		} finally
		{
			close();
		}
		
		return successful;
	}
	
	public boolean delete(String table, String clause, String value)
	{
		accessDB();
		System.out.println("Delete");
		
		boolean successful;
		
		try
		{
			stmt = conn.createStatement();
			String sql = "DELETE FROM " +  table + " WHERE " + clause + " = " + value;
			stmt.executeQuery(sql);
			successful = true;
		} catch (SQLException e)
		{
			e.printStackTrace();
			successful = false;
		} finally
		{
			close();
		}
		
		return successful;
	}
	
	private void close()
	{
		try 
		{
			if(stmt != null)
			{
				stmt.close();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			if(conn != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("Conection Closed.");
	}
}
