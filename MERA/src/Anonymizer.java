import java.io.FileNotFoundException;
import java.io.PrintWriter;

import mera.WordDocument;
import mera.ner.EntityRecognizer;
import mera.ner.Token;


public class Anonymizer {

	String path;
	EntityRecognizer ner;
	public Anonymizer(String path)
	{
		this.path = path;
		
		ner = new EntityRecognizer(new WordDocument(path));
	}
	
	public void anonymizeAndSave()
	{
		ner.tagDocument();
		PrintWriter pw;
		try {
			pw = new PrintWriter(path+".log");
			pw.println("Anonymizable Entities:");
			for(Token t : ner.getAnonymizableTokens())
			{
				pw.println(t.toTaggedString());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ner.anonymizeDocument();
		ner.saveDocument();
	}

}
