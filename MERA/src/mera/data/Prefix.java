package mera.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName="prefix")
public class Prefix 
{
	@DatabaseField(id=true)
	int id;
	@DatabaseField
	String val;
	@DatabaseField
	String category;
	
	public Prefix()
	{
	}
	
	public String toString()
	{
		return "<PRE:"+category+":"+val+"1>";
	}
}
