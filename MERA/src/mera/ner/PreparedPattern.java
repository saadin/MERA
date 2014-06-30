package mera.ner;

import java.util.ArrayList;

import mera.data.Pattern;

public class PreparedPattern 
{
	ArrayList<PatternState> states = new ArrayList<PatternState>();
	public static enum PatternType {CERTAIN, POSSIBLE, IMPOSSIBLE}
	PatternType type;
	public PreparedPattern(Pattern p)
	{
		String split[] = p.getValue().split(">");
		for(int i = 0 ; i < split.length-1 ; i++)
		{
			states.add(new PatternState(split[i].substring(1)));
		}
		type = PatternType.valueOf(PatternType.class, p.getType());
	}
	
	public ArrayList<Tag> match(ArrayList<Token> tokens , int beginIndex)
	{
		ArrayList<Tag> res = new ArrayList<Tag>();
		int stateIndex = 0;
		int selfLoops = 0;
		int	tokenIndex = beginIndex;
		PatternState current = states.get(0);
		
		for(; stateIndex < states.size() ; )
		{
			
		}
		return res;
	}
}
