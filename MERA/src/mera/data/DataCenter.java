package mera.data;

import java.util.ArrayList;

import mera.DatabaseManager;

public class DataCenter 
{
	private static DataCenter instance;
	private ArrayList<Category> category;
	private ArrayList<Suffix> suffix;
	private ArrayList<Prefix> prefix;
	private ArrayList<Pattern> pattern;
	private ArrayList<Punctuation> punc;
	
	
	private DataCenter()
	{
		DatabaseManager dbm = DatabaseManager.getInstance();
		
		category = new ArrayList<Category>();
		suffix = new ArrayList<Suffix>();
		prefix = new ArrayList<Prefix>();
		pattern = new ArrayList<Pattern>();
		punc = new ArrayList<Punctuation>();
		
		suffix.addAll(dbm.getAll(Suffix.class));
		prefix.addAll(dbm.getAll(Prefix.class));
		pattern.addAll(dbm.getAll(Pattern.class));
		punc.addAll(dbm.getAll(Punctuation.class));
		
	}
	
	public ArrayList<Category> getCategories()
	{
		return category;
	}
	
	public ArrayList<Suffix> getSuffixes()
	{
		return suffix;
	}
	public ArrayList<Suffix> getSuffixes(String ent)
	{
		ArrayList<Suffix> res = new ArrayList<Suffix>();
		for(Suffix s : suffix)
		{
			if(s.category.equals(ent))res.add(s);
		}
		return res;
	}
	
	public ArrayList<Prefix> getPrefixes()
	{
		return prefix;
	}
	public ArrayList<Prefix> getPrefixes(String ent)
	{
		ArrayList<Prefix> res = new ArrayList<Prefix>();
		for(Prefix p : prefix)
		{
			if(p.category.equals(ent))res.add(p);
		}
		return res;
	}
	
	public ArrayList<Pattern> getPatterns()
	{
		return pattern;
	}
	public ArrayList<Pattern> getPatterns(String type)
	{
		ArrayList<Pattern> res = new ArrayList<Pattern>();
		for(Pattern p : pattern)
		{
			if(p.type.equals(type))res.add(p);
		}
		return res;
	}
	
	public boolean prefixExists(String ent, String pref)
	{
		for(Prefix elem : prefix)
		{
			if(elem.category.equals(ent) && elem.val.equals(pref))
				return true;
		}
		return false;
	}
	public boolean suffixExists(String ent, String suf)
	{
		for(Suffix elem : suffix)
		{
			if(elem.category.equals(ent) && elem.val.equals(suf))
				return true;
		}
		return false;
	}
	
	public boolean prefixBeginsWith(String ent, String pref)
	{
		for(Prefix elem : prefix)
		{
			if(elem.category.equals(ent) && elem.val.startsWith(pref))
				return true;
		}
		return false;
	}
	public boolean suffixBeginsWith(String ent, String suf)
	{
		for(Suffix elem : suffix)
		{
			if(elem.category.equals(ent) && elem.val.startsWith(suf))
				return true;
		}
		return false;
	}
	
	
	public ArrayList<Punctuation> getPunctuations()
	{
		return punc;
	}
	
	public Category getCategoryByName(String name)
	{
		for(Category c : category)
		{
			if(c.getName().equals(name))return c;
		}
		return null;
	}
	
	
	public static DataCenter getInstance()
	{
		if(instance == null) instance = new DataCenter();
		return instance;
	}
}