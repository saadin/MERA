package mera.ner;

import java.util.ArrayList;

import mera.WordDocument;
import mera.data.DataCenter;
import mera.data.Pattern;

public class EntityRecognizer 
{
	WordDocument doc;
	ArrayList<TokenizedParagraph> parag;
	
	
	public EntityRecognizer(WordDocument doc)
	{
		this.doc = doc;
		parag = new ArrayList<TokenizedParagraph>(30);
		for(String p : doc.getParagraphs())
		{
			parag.add(new TokenizedParagraph(p));
		}
	}
	
	public void tagDocument()
	{
		for(int i = 0 ; i < parag.size() ; i++)
		{
			parag.set(i, tagParagraph(parag.get(i)));
		}
	}
	
	public TokenizedParagraph tagParagraph(TokenizedParagraph tp)
	{
		tp = matchAllPatterns(tp, "CERTAIN");
		tp = matchAllPatterns(tp, "POSSIBLE");
		tp = matchAllPatterns(tp, "IMPOSSIBLE");
		return tp;
	}
	
	private TokenizedParagraph matchAllPatterns(TokenizedParagraph tp, String type)
	{
		for(Pattern p : DataCenter.getInstance().getPatterns("CERTAIN"))
		{
			PreparedPattern pattern = new PreparedPattern(p);
		}
		return tp;
	}
}
