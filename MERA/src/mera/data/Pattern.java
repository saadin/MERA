package mera.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="pattern")
public class Pattern 
{
	@DatabaseField(generatedId=true)
	int id;
	@DatabaseField
	String val;
	@DatabaseField
	String type;
	
	public Pattern()
	{
	}
	
	public Pattern(String val, String type)
	{
		this.val = val;
		this.type = type;
	}
	
	public String toString()
	{
		return "["+type+"]"+val;
	}
}
