package mera;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	public InputStream getInputStream(String path)
	{
		InputStream stream;
		try {
			stream = new FileInputStream(getFile(path));
			return stream;
		} catch (FileNotFoundException e) {
			System.out.println("File not found: "+ path);
			e.printStackTrace();
			return null;
		}
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
	
	public OutputStream openOutputStream(String path)
	{
		try {
			FileOutputStream fos = new FileOutputStream(path);
			return fos;
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file for writing: "+path);
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getName(String path)
	{
		Path p = Paths.get(path);
		return p.getFileName().toString();
	}
	
	public static String getDirectory(String path)
	{
		Path p = Paths.get(path);
		return p.getParent().toString();
	}
	

}
