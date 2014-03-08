package core;
import java.util.*;
import java.io.*;

public class wordListCombinor
{
	private static BufferedReader readerA;
	private static BufferedReader readerB;
	private static FileWriter writer;

	public wordListCombinor(String newFile){
	}

	public static void main(String firstFile, String secondFile){
		combine(firstFile, secondFile);
	}

	private static void combine(String f, String s){
		try{
			readerA = new BufferedReader(new FileReader("" + f + ".txt"));
			new Scanner(readerA);
			readerB = new BufferedReader(new FileReader("" + s + ".txt"));
			new Scanner(readerB);
		}
		catch(FileNotFoundException e){
			System.err.println("Files Could Not Be Found");
			return;
		}
		
		try{
			writer = new FileWriter("MegaDictionary.txt");
		}
		catch(IOException e){
			System.err.println("New File Could Not Be Initialised For Writing");
		}

		try{
			String a = readerA.readLine();
			while(a!=null){
				try{
					writer.write(a.toCharArray());
					writer.write('\r');
					writer.write('\n');
					a = readerA.readLine();
				}
				catch(IOException e){
					System.err.println("Could Not Write To File");
					e.printStackTrace();
				}
			}
		}
		catch(IOException e){
			System.err.println("Could Not Read From First File");
		}
		
		try{
			String b = readerB.readLine();
			while(b!=null){
				try{
					writer.write(b.toCharArray());
					writer.write('\r');
					writer.write('\n');
					b = readerB.readLine();
				}
				catch(IOException e){
					System.err.println("Could Not Write To File");
					e.printStackTrace();
				}
			}
		}
		catch(IOException e){
			System.err.println("Could Not Read From Second File");
		}
	}

}