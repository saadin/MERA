package mera.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName="prefix")
public class Punctuation 
{
	@DatabaseField(id=true)
	private int id;
	@DatabaseField
	String val;
	@DatabaseField
	String name;
	
	public Punctuation()
	{
	}
	
	public String toString()
	{
		return "<PUC::"+name+":"+"1>";
	}
}
