package mera.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="category")
public class Category 
{
	@DatabaseField(id=true)
	String category;
	
	@DatabaseField
	String collection_table;
	
	@DatabaseField
	String type;
	
	@DatabaseField
	boolean collection_only;
	
	@DatabaseField
	String description;
	
	public Category()
	{
	}
	
	public String toString()
	{
		return category + " : " + description;
	}
}
