import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mera.DatabaseManager;
import mera.FileInterface;
import mera.WordDocument;
import mera.data.Category;
import mera.data.DataCenter;
import mera.data.Pattern;
import mera.data.Prefix;
import mera.data.collection.City;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	
	public static void main(String[] args) throws IOException 
	{
		
		
//		String dirpath = "/home/sadegh/Desktop/Link to Final Project/mr/";
//		String fname = "record1.doc";
//		Path p = Paths.get(dirpath);
		
//		WordDocument doc = new WordDocument(dirpath+fname);
////		doc.changeParagraph(0, "test");
//		doc.changeParagraph(1, "test2");
//		doc.changeParagraph(2, "test3");
//		
//		System.out.println(doc.getRange());
		
//		InputStream is = new FileInputStream(dirpath+fname);
//		WordExtractor we = new WordExtractor(is);
//		
//		PrintWriter pw = new PrintWriter(dirpath+"output.txt");
//		pw.println(doc.getHeader());
//		pw.close();
//		
		DatabaseManager dbm = DatabaseManager.getInstance();
//		
//		boolean res = dbm.entityBeginsWith("اح", "firstname");
		boolean res = DataCenter.getInstance().prefixBeginsWith("NEP", "آ");
		System.out.println(res);
//		dbm.initdb();
//		List<Pattern> lp = dbm.getAll(Pattern.class);
//		for(Pattern p : lp)
//		{
//			System.out.println(p);
//		}
	}
}

