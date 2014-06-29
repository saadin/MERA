package mera;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileInterface 
{
	private static FileInterface instance;
	private FileInterface()
	{
		
	}
	
	public static FileInterface getInstance()
	{
		if(instance == null) instance = new FileInterface();
		return instance;
	}
	
	public File getFile(String path)
	{
		return new File(path);
	}
	
	public String[] getLines(String path)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			ArrayList<String> lines = new ArrayList<String>();
			while(br.ready())
			{
				String line = br.readLine();
				if(!line.equals(""))
					lines.add(line);
			}
			br.close();
			String res[] = new String[lines.size()];
			lines.toArray(res);
			return res;
		} catch (FileNotFoundException e) {
			System.out.println("File not found: "+path);
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("Error in reading file: "+path);
			e.printStackTrace();
			return null;
		}
	}

}
