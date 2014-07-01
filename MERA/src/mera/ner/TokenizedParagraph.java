package mera.ner;

import java.util.ArrayList;

import mera.data.DataCenter;
import mera.data.Punctuation;

public class TokenizedParagraph 
{
	ArrayList<Token> tokens;
	
	public TokenizedParagraph(String text)
	{
		tokens = new ArrayList<Token>();
		for(Punctuation p : DataCenter.getInstance().getPunctuations())
		{
			text = text.replaceAll("["+p.getValue()+"]", " "+p.getValue()+" ");
		}
		String split[] = text.split("[ ]+");
		for(String s : split)
		{
			tokens.add(new Token(s));
		}
	}
	
	public void anonymizeParagraph()
	{
		for(Token t : tokens)
		{
			t.anonymize();
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
			if(tags == null)continue;
			for(int j = 0 ; j < tags.size() ; j++)
			{
				tokens.get(i+j).applyTag(tags.get(j), pattern.type);
			}
		}
		
	}
	
	public String toTaggedString()
	{
		String res="";
		for(int i = 0 ; i < tokens.size() ; i++)
		{
			Token t = tokens.get(i);
			if(t.determined && t.isEntity)
			{
				
				res+="<"+t.category.getName()+">"+t.val;
				if(i<tokens.size()-1 && tokens.get(i+1).fromPrevious)
				{
					i++;
					for(;i<tokens.size() && tokens.get(i).fromPrevious ; i++)
						res+=" "+tokens.get(i).val;
					i--;
				}
				res+="</"+t.category.getName()+">";
			}
			else if(!t.determined && t.possibleTags.size()>0)
			{
				res+="<MANY>"+t+"</MANY>";
			}
			else
			{
				res += t.val + " ";
			}
		}
		return res;
	}
	
	public String toString()
	{
		String res = "";
		for(Token t : tokens)
		{
			res+= t.val + " ";
		}
		return res + System.lineSeparator();
	}
}
