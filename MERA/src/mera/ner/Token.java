package mera.ner;

import java.util.ArrayList;

import mera.data.Category;
import mera.data.DataCenter;
import mera.ner.PreparedPattern.PatternType;

public class Token {
	String val;
	ArrayList<Tag> possibleTags;
	Category category;
	boolean fromPrevious;
	boolean determined;
	
	public Token(String val)
	{
		this.val = val;
		possibleTags = new ArrayList<Tag>();
		determined = false;
		fromPrevious = false;
		category = Category.other;
	}
	
	public void applyTag(Tag t, PatternType type)
	{
		if(type == PatternType.CERTAIN)
		{
			determined = true;
			category = DataCenter.getInstance().getCategoryByName(t.ent);
			fromPrevious = t.fromPrevious;
		}
		
		else if(type == PatternType.POSSIBLE)
		{
			if(!possibleTags.contains(t))possibleTags.add(t);
		}
		else
		{
			possibleTags.remove(t);
		}
	}
	
}
