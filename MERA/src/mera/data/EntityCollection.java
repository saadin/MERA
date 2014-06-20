package mera.data;

import com.j256.ormlite.field.DatabaseField;

public class EntityCollection 
{
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField
	String val;
	
}
