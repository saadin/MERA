package mera;


import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;


public class WordDocument 
{
	private WordExtractor we;
	private HWPFDocument doc;
	private String path;
//	HeaderStories header;
	public WordDocument(String path)
	{
		this.path = path;
		try {
			doc = new HWPFDocument(FileInterface.getInstance().getInputStream(path));
			we = new WordExtractor(doc);
			
			
		} catch (Exception e) {
			System.out.println("Error reading from file: "+path);
			e.printStackTrace();
		}
	}
	
	public String getHeader()
	{
		return doc.getHeaderStoryRange().text();
	}
	
	public void deleteHeader()
	{
		doc.getHeaderStoryRange().delete();
	}
	
	public String getRange()
	{
		return doc.getRange().text();
	}
	
	public void changeParagraph(int index, String newText)
	{
		doc.getRange().getParagraph(index).replaceText(newText, false);
	}
	
	public String[] getParagraphs()
	{
		return we.getParagraphText();
	}
	
	public String getParagraph(int index)
	{
		return we.getParagraphText()[index];
	}
	
	public boolean writeDocument()
	{
		String newPath = path.substring(0,path.lastIndexOf("."))+"-anonymized"+path.substring(path.lastIndexOf("."));
		
		OutputStream os = FileInterface.getInstance().openOutputStream(newPath);
		try {
			doc.write(os);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
