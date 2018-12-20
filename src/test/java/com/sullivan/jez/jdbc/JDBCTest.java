package com.sullivan.jez.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class JDBCTest 
{
	JDBC myjdbc = new JDBC();
	
	public void setUp()
	{
		
	}
	
	@Test
	public void createTestPass1()
	{	
		assertTrue("",myjdbc.create("genres", "name", "Spooky"));	
	}
	
	@Test
	public void createTestFail1()
	{
		assertFalse("", myjdbc.create("notable", "notable", "novalue"));
	}
	
	@Test
	public void readTestPass1()
	{
		List<Actor> expectedActors = new ArrayList<Actor>();
		List<Actor> actualActors = new ArrayList<Actor>();
		Actor a = new Actor(1, "PENELOPE", "GUINESS", "2006-02-15 04:34:33.0");
		Actor b = new Actor(2, "NICK", "WAHLBERG", "2006-02-15 04:34:33.0");
		Actor c = new Actor(3, "ED", "CHASE", "2006-02-15 04:34:33.0");
		Actor d = new Actor(4, "JENNIFER", "DAVIS", "2006-02-15 04:34:33.0");
		Actor e = new Actor(5, "JOHNNY", "LOLLOBRIGIDA", "2006-02-15 04:34:33.0");
		Collections.addAll(expectedActors,a,b,c,d,e);
		
		actualActors = myjdbc.read("actor");
		
		for(int i = 0; i < actualActors.size(); i++)
		{
			assertEquals("", actualActors.get(i).actor_id, expectedActors.get(i).actor_id);
			assertEquals("", actualActors.get(i).first_name, expectedActors.get(i).first_name);
			assertEquals("", actualActors.get(i).last_name, expectedActors.get(i).last_name);
			assertEquals("", actualActors.get(i).last_update, expectedActors.get(i).last_update);
		}
	}
	
	@Test
	public void deleteTestFail1()
	{
		assertFalse(myjdbc.delete("genres", "name", "spookyz"));
	}
}
