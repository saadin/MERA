package mera.ner;

import mera.data.Category;

public class Tag implements Comparable<Tag>
{
	String ent;
	boolean fromPrevious;
	
	public Tag()
	{
		ent = Category.other.getName();
		fromPrevious = false;
	}
	
	public Tag(String category, boolean fromPrevious)
	{
		this.ent = category;
		this.fromPrevious = fromPrevious;
	}

	@Override
	public int compareTo(Tag o) {
		if(ent.equals(o.ent) && fromPrevious==o.fromPrevious)return 0;
		return 1;
	}
}
