package mera.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName="punctuation")
public class Punctuation 
{
	@DatabaseField
	String val;
	@DatabaseField(id=true)
	String name;
	
	public Punctuation()
	{
	}
	
	public String getValue()
	{
		return val;
	}
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return "<PUC::"+name+":"+"1>";
	}
}
