package mera.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName="suffix")
public class Suffix 
{
	@DatabaseField(id=true)
	int id;
	@DatabaseField
	String val;
	@DatabaseField
	String category;
	
	public Suffix()
	{
	}
	
	public String toString()
	{
		return "<SUF:"+category+":"+val+"1>";
	}
}
