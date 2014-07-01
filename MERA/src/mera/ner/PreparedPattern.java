package mera.ner;

import java.util.ArrayList;

import mera.data.Pattern;
import mera.ner.PatternState.MatchType;

public class PreparedPattern 
{
	static final int MAX_NUMBER_OF_WORDS=5;
	ArrayList<PatternState> states = new ArrayList<PatternState>();
	public static enum PatternType {CERTAIN, POSSIBLE, IMPOSSIBLE}
	PatternType type;
	public PreparedPattern(Pattern p)
	{
		String split[] = p.getValue().split(">");
		for(int i = 0 ; i < split.length ; i++)
		{
			states.add(new PatternState(split[i].substring(1)));
		}
		type = PatternType.valueOf(PatternType.class, p.getType());
	}
	
	public ArrayList<Tag> match(ArrayList<Token> tokens , int beginIndex)
	{
		return recursiveMatch(tokens, beginIndex, 0, 1);
	}
	
	ArrayList<Tag> recursiveMatch(ArrayList<Token> tokens , int tokenIndex, int stateIndex, int loops)
	{
		if(stateIndex == states.size())
			return new ArrayList<Tag>();
		if(tokenIndex == tokens.size())
			return null;
		
		PatternState current = states.get(stateIndex);
		if(loops > current.max)
			return null;

		MatchType mt = current.match(tokens.get(tokenIndex).val);
		ArrayList<Tag> res = new ArrayList<Tag>();
		ArrayList<Tag> temp;
		
		switch (mt) {
		case NO_MATCH:
		case TYPE_NO_MATCH:
			return null;
			
		case MATCH_WHOLE:
			res.add(new Tag(current.category, current.type.equals("ENT"), false));
			temp = recursiveMatch(tokens, tokenIndex+1, stateIndex+1, 1);
			if(temp!=null){
				res.addAll(temp);
				return res;
			}
			break;
		case MATCH_PARTIAL:
			int endIndex = checkMultiWord(tokens, tokenIndex, current);
			if(endIndex>0) 
			{
				res.addAll(generateMultipleTags(endIndex-tokenIndex+1, current.category,current.type.equals("ENT"), true));
				temp = recursiveMatch(tokens, endIndex+1, stateIndex+1, 1);
				if(temp!=null){
					res.addAll(temp);
					return res;
				}
			}
			break;
			
		case LOOK_FOR_NEXT:
			res.add(new Tag(current.category, current.type.equals("ENT") ,loops>1));
			temp = recursiveMatch(tokens, tokenIndex+1, stateIndex+1, 1);
			if(temp!=null)
			{
				res.addAll(temp);
				return res;
			}
			
			temp = recursiveMatch(tokens, tokenIndex+1, stateIndex, loops+1);
			if(temp!=null)
			{
				res.addAll(temp);
				return res;
			}
			break;
			
		default:
			break;
		}
		
		
		return null;
		
	}
	
	private int checkMultiWord(ArrayList<Token> tokens, int tokenIndex, PatternState current)
	{
		String expression = tokens.get(tokenIndex).val;
		int endIndex;
		for(endIndex = tokenIndex+1; endIndex<tokens.size() && endIndex - tokenIndex + 1 <= MAX_NUMBER_OF_WORDS; endIndex++)
		{
			expression += " "+tokens.get(endIndex).val;
			MatchType mt = current.match(expression);
			if(mt == MatchType.MATCH_WHOLE)return endIndex;
			if(mt == MatchType.MATCH_PARTIAL)continue;
			break;
		}
		return -1;
	}
	
	private ArrayList<Tag> generateMultipleTags(int count, String category, boolean isEntity ,boolean continous)
	{
		ArrayList<Tag> tags = new ArrayList<Tag>(count);
		tags.add(new Tag(category, isEntity, false));
		Tag t = new Tag(category, isEntity, continous);
		for(int i = 1 ; i < count ; i++)
			tags.add(t);
		
		return tags;
		
	}
	
}
