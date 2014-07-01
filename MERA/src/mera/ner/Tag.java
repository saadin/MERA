package mera.ner;

import mera.data.Category;

public class Tag implements Comparable<Tag>
{
	String ent;
	boolean isEntity;
	boolean fromPrevious;
	
	public Tag()
	{
		ent = Category.other.getName();
		fromPrevious = false;
	}
	
	public Tag(String category, boolean isEntity, boolean fromPrevious)
	{
		this.ent = category;
		this.fromPrevious = fromPrevious;
		this.isEntity = isEntity;
	}

	@Override
	public int compareTo(Tag o) {
		if(ent.equals(o.ent) && fromPrevious==o.fromPrevious)return 0;
		return 1;
	}
	
	public String toString()
	{
		return "["+ent+"]";
	}
}
