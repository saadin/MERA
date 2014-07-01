package mera.ner;

import mera.DatabaseManager;
import mera.data.DataCenter;
import mera.data.Punctuation;

public class PatternState 
{
	enum MatchType {MATCH_WHOLE, MATCH_PARTIAL,LOOK_FOR_NEXT,MATCH_MULTI_WORD,NO_MATCH,TYPE_NO_MATCH}
	static final int MAX_CONTINOUS_STATES=15;
	String type;
	String category;
	String optional;
	int min;
	int max;
	
	public PatternState(String state)
	{
		String[] part = state.split(":");
		type = part[0];
		category = part[1];
		optional = part[2];
		if(part[3].equals("*"))
		{
			min = 0;
			max = MAX_CONTINOUS_STATES;
		}
		else if(part[3].equals("+"))
		{
			min = 1;
			max = MAX_CONTINOUS_STATES;
		}
		else if(part[3].equals("?"))
		{
			min = 0;
			max = MAX_CONTINOUS_STATES;
		}
		else if(!part[3].contains("{"))
		{
			min=max=Integer.parseInt(part[3]);
		}
		else
		{
			part[3] = part[3].substring(1,part[3].length()-1);
			String range[] = part[3].split(",");
			min = (range[0].equals("") ? 0 : Integer.parseInt(range[0]));
			max = (range[1].equals("") ? MAX_CONTINOUS_STATES : Integer.parseInt(range[1]));
		}
	}
	
	public MatchType match(String token)
	{
		boolean isNumber = isNumber(token);
		
		if(!category.equals("OTH") && type.equals("ENT") && DataCenter.getInstance().getCategoryByName(category).getType().equals("number") && !isNumber)
			return MatchType.TYPE_NO_MATCH;
		//check if this is an entity state and type of entity differs
		if(type.equals("ENT") && optional.equals("ANY"))
		{
			
			if(isNumber)
				return MatchType.MATCH_WHOLE;
			else
				return MatchType.LOOK_FOR_NEXT;
		}
		if(checkWholeMatch(token))return MatchType.MATCH_WHOLE;
		if(checkPartialMatch(token))return MatchType.MATCH_PARTIAL;
		
		return MatchType.NO_MATCH;
	}
	
	private boolean checkWholeMatch(String token)
	{
//		if(
//				type.equals("PRE") && DataCenter.getInstance().prefixExists(category, token)
//				|| type.equals("SUF") && DataCenter.getInstance().suffixExists(category, token)
//				|| type.equals("ENT") && optional.equals("COL") 
//						&& DatabaseManager.getInstance().entityExists(token, DataCenter.getInstance().getCategoryByName(category).getCollectionTable())
//				)
//		{
//			return true;
//		}
		String tableName="";
		String cat = "";
		if(type.equals("SUF")){
			tableName = "suffix";
			cat = category;
		}
		else if(type.equals("PRE")){
			tableName = "prefix";
			cat = category;
		}
		else if(type.equals("PUN"))tableName = "punctuation";
		else if(type.equals("ENT")){
			tableName = DataCenter.getInstance().getCategoryByName(category).getCollectionTable();
		}
		else
			tableName="";
		if(optional.equals("COL")) 
		{
			return DatabaseManager.getInstance().entityExistsWhereCategory(token, tableName,cat);
		}
		if(type.equals("PUN"))
		{
			Punctuation p = DataCenter.getInstance().getPunctuation(optional);
			if(p!=null)return p.equals(token);
		}
		
		return false;
		
	}
	private boolean checkPartialMatch(String token)
	{
		if(
				type.equals("PRE") && DataCenter.getInstance().prefixBeginsWith(category, token)
				|| type.equals("SUF") && DataCenter.getInstance().suffixBeginsWith(category, token)
				|| type.equals("ENT") && optional.equals("COL") 
						&& DatabaseManager.getInstance().entityBeginsWith(token, DataCenter.getInstance().getCategoryByName(category).getCollectionTable())
				)
		{
			return true;
		}
		
		return false;
		
	}
	
	private boolean isNumber(String str)
	{
		str=str.replaceAll("۰", "0");
		str=str.replaceAll("۱", "1");
		str=str.replaceAll("۲", "2");
		str=str.replaceAll("۳", "3");
		str=str.replaceAll("۴", "4");
		str=str.replaceAll("۵", "5");
		str=str.replaceAll("۶", "6");
		str=str.replaceAll("۷", "7");
		str=str.replaceAll("۸", "8");
		str=str.replaceAll("9", "9");
		try{
			Integer.parseInt(str);
			return true;
		}catch (Exception e)
		{
			return false;
		}
	}
	
	public String toString()
	{
		return "<"+type+":"+category+":"+optional+":"+min+","+max+">";
	}
	
}
