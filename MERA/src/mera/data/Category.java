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
	
	public static Category other = new Category("","OTH","دیگر");
	
	public Category()
	{
	}
	public Category(String type, String desc)
	{
		this.type = type;
		this.description=desc;
	}
	public Category(String name, String type, String desc)
	{
		this.category = name;
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
	
	
	public static String getAgeEquivalent(int age)
	{
		if(age<2) return "طفل";
		if(age<12) return "کودک";
		if(age<18) return "نوجوان";
		if(age<30) return "جوان";
		if(age<50) return "میانسال";
		if(age<65) return "مسن";
		return "پیر";
	}
	public static String getKidsEquivalent(int kids)
	{
		if(kids<=2) return "تعداد کم";
		if(kids<=4) return "تعداد متوسط";
		if(kids<=8) return "تعداد زیاد";
		return "تعداد خیلی زیاد";
	}
	
	public String toString()
	{
		return category + " : " + description;
	}
	
	
}
