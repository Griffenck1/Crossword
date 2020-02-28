import java.util.*;

class Board{
	private char board_[][];
	//maps a word to the vector of it's locations
	Map<String, Vector> word_map_;
	
	public Board(){
		board_ = new char[7][7];
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				board_[i][j] = 'X';
			}
		}
		
		
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				System.out.print(board_[i][j]);
			}
			System.out.println();
		}
	}
}