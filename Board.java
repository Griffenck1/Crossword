import java.util.*;

class Board{
	public static class position{
		private int row;
		private int col;
		public position(){
			row = 0;
			col = 0;
		}
		public position(int r, int c){
			row = r;
			col = c;
		}
		public int GetRow(){return row;}
		public int GetCol(){return col;}
	}
	
	Vector<position> across_pos_;
	Vector<position> down_pos_;
	private char board_[][];
	//maps a word to the vector of it's locations
	HashMap<String, Vector> word_map_;
	
	
	//Creates a new random board
	public Board(){
		across_pos_ = new Vector<position>();
		down_pos_ = new Vector<position>();
		board_ = new char[7][7];
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				board_[i][j] = 'X';
			}
		}
		
		String word = ParseText.RandomWord(7);
		for(int i = 0; i < word.length(); i++){
			board_[0][i] = word.charAt(i);
			position pos = new position(0, i);
			if(i < 6){
				across_pos_.add(pos);
			}
		}
		
		for(int i = 0; i < 5; i++){
			Random rand = new Random();
			word = null;
			//Holds map of characters in path of word to how far from start of word they are
			HashMap<Integer, Character> word_match_map = new HashMap<Integer, Character>();
			if(i % 2 == 1){
				int rand_index = rand.nextInt(down_pos_.size());
				int rand_row = down_pos_.get(rand_index).GetRow();
				int rand_col = down_pos_.get(rand_index).GetCol();
				boolean is_across_pos = true;
				while(is_across_pos == true){
					is_across_pos = false;
					for(int j = 0; j < across_pos_.size(); j++){
						if((across_pos_.get(j).GetRow() == rand_row) & (across_pos_.get(j).GetCol() == rand_col)){
							rand_index = rand.nextInt(down_pos_.size());
							rand_row = down_pos_.get(rand_index).GetRow();
							rand_col = down_pos_.get(rand_index).GetCol();
							is_across_pos = true;
						}
					}
				}
				while(word == null){
					for(int j = rand_col; j < 7; j++){
						if(board_[rand_row][j] != 'X'){
							word_match_map.put(j - rand_col, board_[rand_row][j]);
						}
					}
					word = ParseText.MatchedWord((6 - rand_col), word_match_map);
				}
				for(int j = rand_col; j < word.length(); j++){
					board_[rand_row][j] = word.charAt(j - rand_col);
					position pos = new position(rand_row, j);
					if(j < 6){
						across_pos_.add(pos);
					}
				}
			}
			else{
				int rand_index = rand.nextInt(across_pos_.size());
				int rand_row = across_pos_.get(rand_index).GetRow();
				int rand_col = across_pos_.get(rand_index).GetCol();
				boolean is_down_pos = true;
				while(is_down_pos == true){
					is_down_pos = false;
					for(int j = 0; j < down_pos_.size(); j++){
						if((down_pos_.get(j).GetRow() == rand_row) & (down_pos_.get(j).GetCol() == rand_col)){
							rand_index = rand.nextInt(across_pos_.size());
							rand_row = across_pos_.get(rand_index).GetRow();
							rand_col = across_pos_.get(rand_index).GetCol();
							is_down_pos = true;
						}
					}
				}
				while(word == null){
					for(int j = rand_row; j < 7; j++){
						if(board_[j][rand_col] != 'X'){
							word_match_map.put(j - rand_row, board_[j][rand_col]);
						}
					}
					word = ParseText.MatchedWord((6 - rand_row), word_match_map);
				}
				for(int j = rand_row; j < word.length(); j++){
					board_[j][rand_col] = word.charAt(j - rand_row);
					position pos = new position(j, rand_col);
					if(j < 6){
						down_pos_.add(pos);
					}
				}
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