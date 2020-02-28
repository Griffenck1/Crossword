import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class ParseText{
	//returns a random word of length between 1 and the max length passed to the function
	public static String RandomWord(int max_len){
		Random rand = new Random();
		//the dictionary has 10k words, I stop rand at 9k so it has plenty of runway to find words... sorry z words
		int rand_int = rand.nextInt(9000);
		
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader("words.txt"));
			String line = reader.readLine();
			int counter = 0;
			while(counter < rand_int){
				reader.readLine();
				counter++;
			}
			line = reader.readLine();
			while(line.length() > max_len){
				line = reader.readLine();
				while(line.length() < 4){
					line = reader.readLine();
				}
			}
			return line;
		}
		catch(IOException e){
			e.printStackTrace();
			return "";
		}
	}
	
	public static String MatchedWord(int max_len, HashMap<Integer, Character> word_match_map){
		Random rand = new Random();
		//the dictionary has 10k words, I stop rand at 9k so it has plenty of runway to find words... sorry z words
		
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader("words.txt"));
			String line = reader.readLine();
			boolean is_match = false;
			while(is_match == false){
				is_match = true;
				if(line.length() > max_len){is_match = false;}
				for(int i = 0; i < line.length(); i++){
					if(word_match_map.containsKey(i)){
						if(line.charAt(i) != word_match_map.get(i)){is_match = false;}
					}
				}
				if(is_match == false){
					line = reader.readLine();
					if(line == null){return null;}
				}
			}
			return line;
		}
		catch(IOException e){
			e.printStackTrace();
			return "";
		}
	}
}