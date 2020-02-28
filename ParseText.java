import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
			}
			return line;
		}
		catch(IOException e){
			e.printStackTrace();
			return "";
		}
	}
}