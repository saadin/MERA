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
		instance = new DataCenter();
		
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
	public ArrayList<Prefix> getPrefixes()
	{
		return prefix;
	}
	public ArrayList<Pattern> getPatterns()
	{
		return pattern;
	}
	public ArrayList<Punctuation> getPunctuations()
	{
		return punc;
	}
	
	
	public static DataCenter getInstance()
	{
		if(instance == null) instance = new DataCenter();
		return instance;
	}
}