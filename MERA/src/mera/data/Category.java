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
	
	public static Category other = new Category("OTH","دیگر");
	
	public Category()
	{
	}
	public Category(String type, String desc)
	{
		this.type = type;
		this.description=desc;
	}
	
	public String getName()
	{
		return category;
	}
	public String getCollectionTable()
	{
		return collection_table;
	}
	public String getType()
	{
		return type;
	}
	public boolean getCollectionOnly()
	{
		return collection_only;
	}
	public String getDescription()
	{
		return description;
	}
	
	
	public String toString()
	{
		return category + " : " + description;
	}
	
	
}
