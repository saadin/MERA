package mera.ner;

import java.util.ArrayList;

import mera.data.Category;
import mera.data.DataCenter;
import mera.ner.PreparedPattern.PatternType;

public class Token {
	String val;
	ArrayList<Tag> possibleTags;
	Category category;
	boolean isEntity;
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
			isEntity = t.isEntity;
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
	
	public void applyOnePossibe()
	{
		if(possibleTags.size()!=0)
		{
			Tag t = possibleTags.get(0);
			determined = true;
			category = DataCenter.getInstance().getCategoryByName(t.ent);
			fromPrevious = t.fromPrevious;
			isEntity = t.isEntity;
		}
	}
	
	public boolean isAnonymizable()
	{
		if(isEntity && determined)
		{
			return true;
		}
		return false;
	}
	
	
	public boolean anonymize(String manualValue)
	{
		if(!isAnonymizable())return false;
		val = manualValue;
		return true;
	}
	public boolean anonymize()
	{
		if(!isAnonymizable())return false;
		if(!anonymizeWithReplace())
			anonymizeWithRemove();
		val = "["+val+"]";
		return true;
	}
	
	private boolean anonymizeWithReplace()
	{
		try{
			if(category.getName().equals("NEA"))
				val = Category.getAgeEquivalent(Integer.parseInt(val));
			else if(category.getName().equals("NEK"))
				val = Category.getKidsEquivalent(Integer.parseInt(val));
			else if(category.getName().equals("NEP")) 
				val = val.substring(0,1);
			else 
				return false;
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}
	
	private boolean anonymizeWithRemove()
	{
		val = "-حذف شده-";
		return true;
	}
	
	
	public String toString()
	{
		return val;
	}
	
	public String toTaggedString()
	{
		return category.toString() + " : " + val;
	}
	
}
