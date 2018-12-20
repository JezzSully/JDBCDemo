package com.sullivan.jez.jdbc;

import java.util.List;

public class Main 
{
	public static void main(String[] args)
	{
		JDBC myDatabase = new JDBC();
		
		List<Actor> actors;
		
		actors = myDatabase.read("actor");
		
		for(Actor a : actors)
		{
			System.out.println(a);
		}
		
	}
}
