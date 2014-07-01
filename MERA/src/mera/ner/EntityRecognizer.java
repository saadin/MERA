package mera.ner;

import java.util.ArrayList;

import mera.WordDocument;
import mera.data.DataCenter;
import mera.data.Pattern;

public class EntityRecognizer 
{
	WordDocument doc;
	ArrayList<TokenizedParagraph> parag;
	ArrayList<PreparedPattern> certainPreparedPatterns;
	ArrayList<PreparedPattern> possiblePreparedPatterns;
	ArrayList<PreparedPattern> impossiblePreparedPatterns;
	
	public EntityRecognizer(WordDocument doc)
	{
		this.doc = doc;
		parag = new ArrayList<TokenizedParagraph>(30);
		for(String p : doc.getParagraphs())
		{
			parag.add(new TokenizedParagraph(p));
		}
		certainPreparedPatterns = new ArrayList<PreparedPattern>();
		possiblePreparedPatterns = new ArrayList<PreparedPattern>();
		impossiblePreparedPatterns = new ArrayList<PreparedPattern>();
		for(Pattern p : DataCenter.getInstance().getPatterns("CERTAIN"))
			certainPreparedPatterns.add(new PreparedPattern(p));
		for(Pattern p : DataCenter.getInstance().getPatterns("POSSIBLE"))
			possiblePreparedPatterns.add(new PreparedPattern(p));
		for(Pattern p : DataCenter.getInstance().getPatterns("IMPOSSIBLE"))
			impossiblePreparedPatterns.add(new PreparedPattern(p));
		
	}
	
	public void saveDocument()
	{
		for (int i = 0; i < parag.size(); i++) {
			doc.changeParagraph(i, parag.get(i).toString());
		}
		doc.deleteHeader();
		doc.writeDocument();
	}
	
	public void anonymizeDocument()
	{
		for(TokenizedParagraph tp : parag)
		{
			tp.anonymizeParagraph();
		}
	}
	
	public String getTaggedParagraph(int index)
	{
		if(index>=parag.size())return null;
		return parag.get(index).toTaggedString();
	}
	public String getParagraphText(int index)
	{
		return parag.get(index).toString();
	}
	
	public void tagDocument()
	{
		for(int i = 0 ; i < parag.size() ; i++)
		{
			tagParagraph(parag.get(i));
		}
	}
	
	public void tagParagraph(TokenizedParagraph tp)
	{
		matchAllPatterns(tp, certainPreparedPatterns);
		matchAllPatterns(tp, possiblePreparedPatterns);
		matchAllPatterns(tp, impossiblePreparedPatterns);
		for(Token t : tp.getTokens())
		{
			t.applyOnePossibe();
		}
	}
	
	public ArrayList<Token> getAnonymizableTokens()
	{
		ArrayList<Token> res = new ArrayList<Token>();
		for(int i = 0 ; i < parag.size() ; i++)
		{
			res.addAll(getAnonymizableTokens(i));
		}
		return res;
	}
	
	public ArrayList<Token> getAnonymizableTokens(int paragraphIndex)
	{
		ArrayList<Token> res = new ArrayList<Token>();
		for(Token t : parag.get(paragraphIndex).tokens)
		{
			if(t.isAnonymizable())res.add(t);
		}
		
		return res;
	}
	
	private void matchAllPatterns(TokenizedParagraph tp, ArrayList<PreparedPattern> patterns)
	{
		for(PreparedPattern p : patterns)
		{
			tp.matchPattern(p);
		}
	}
}
