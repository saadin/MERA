import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import mera.DatabaseManager;
import mera.data.Category;
import mera.data.Prefix;
import mera.data.collection.City;

import org.apache.poi.hwpf.extractor.WordExtractor;
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	
	public static void main(String[] args) throws IOException {
//		String dirpath = "/home/sadegh/Desktop/mr/";
//		String fname = "record1.doc";
//		InputStream is = new FileInputStream(dirpath+fname);
//		WordExtractor we = new WordExtractor(is);
//		
//		PrintWriter pw = new PrintWriter(dirpath+"output.txt");
//		pw.println(we.getParagraphText()[1]);
//		pw.close();
		
		DatabaseManager dbm = DatabaseManager.getInstance();
		
		boolean res = dbm.entityExists("خر", "firstname");
		System.out.println(res);
//		dbm.initdb();
//		List<Category> lp = dbm.getAll(Category.class);
//		for(Category p : lp)
//		{
//			System.out.println(p);
//		}
	}
}
