package com.sullivan.jez.jdbc;

public class Actor 
{
	int actor_id;
	String first_name;
	String last_name;
	String last_update;
	
	public Actor(int actor_id,String first_name,String last_name,String last_update)
	{
		this.actor_id = actor_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.last_update = last_update;
	}
	
	public String toString()
	{
		String toPrint;
		
		toPrint = actor_id + " " + first_name + " " + last_name + " " + last_update;
		
		return toPrint;
	}
}
