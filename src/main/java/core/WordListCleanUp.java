package core;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class WordListCleanUp {
	private static ArrayList<String> words = new ArrayList<String>();
	private static BufferedReader reader;
	private static FileWriter writer;

	public WordListCleanUp()
	{
	}

	public static void main(String args[]){
		wordListCombinor.main("extraWords", "wordList");
		WordListCleanUp.readIn();
		WordListCleanUp.writeCleanFile();
		System.out.println("File Clean Complete");
	}

	private static void readIn(){
		try{
			reader = new BufferedReader(new FileReader("MegaDictionary.txt"));
			new Scanner(reader);
		}
		catch(FileNotFoundException e){
			System.err.println("File Could Not Be Found");
			return;
		}
		try{
			String word = reader.readLine();
			while(word != null){
				if(goodWord(word)){
					words.add(word);
				}
				word = reader.readLine();
			}
			words.trimToSize();
			System.out.println("File Read In");
			Collections.sort(words);
			System.out.println("Sorted Alphabetically");
		}
		catch(IOException e){
			System.err.println("Could Not Read From File");
			return;
		}
	}

	private static boolean goodWord(String s){
		if(s.contains("-")){
			return false;
		}else if(s.contains("'")){
			return false;
		}else if(s.contains(" ")){
			return false;
		}else if(s.contains(".")){
			return false;
		}else if(s.contains("fuck")){
			return false;
		}else if(s.contains("shit")){
			return false;
		}else if(s.contains("crap")){
			return false;
		}else{
			return true;
		}
	}

	private static void writeCleanFile(){
		try{
			writer = new FileWriter("MegaDictionary.txt");
		}
		catch(IOException e){
			System.err.println("New File Could Not Be Initialised For Writing");
		}

		for(int i = 0; i<words.size(); i++){
			String toWrite = (words.get(i));
			char[] write = toWrite.toCharArray();

			try{
				writer.write(write);
				writer.write('\r');
				writer.write('\n');
			}
			catch(IOException e){
				System.err.println("Could Not Write To File");
				e.printStackTrace();
			}
		}
		System.out.println("New File Written");
	}
}
