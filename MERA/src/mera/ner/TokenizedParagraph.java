package mera.ner;

import java.util.ArrayList;

import mera.data.DataCenter;
import mera.data.Punctuation;

public class TokenizedParagraph 
{
	ArrayList<Token> tokens;
	
	public TokenizedParagraph(String text)
	{
		for(Punctuation p : DataCenter.getInstance().getPunctuations())
		{
			text.replaceAll("["+p.getValue()+"]", " "+p.getValue()+" ");
		}
		String split[] = text.split("[ ]+");
		for(String s : split)
		{
			tokens.add(new Token(s));
		}
	}
	
	
	public ArrayList<Token> getTokens()
	{
		return tokens;
	}
	
	public Token getToken(int index)
	{
		return tokens.get(index);
	}
	
	public void matchPattern(PreparedPattern pattern)
	{
		for(int i = 0 ; i < tokens.size() ; i++)
		{
			ArrayList<Tag> tags = pattern.match(tokens, i);
			for(int j = 0 ; j < tags.size() ; j++)
			{
				tokens.get(i+j).applyTag(tags.get(j), pattern.type);
			}
		}
		
	}
}
