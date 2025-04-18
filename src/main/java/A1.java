import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class A1 implements A1Interface {//implements A1Interface
    private StringBuilder currentString;
    Set<String> result;
	//Created a board to store visited squares
	int[][] visitedSquares = { {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0}};

    public Set<String> getWordsOfLength(char[][] boggleBoard, DictInterface dictionary, int wordLength){
		result = new HashSet<>();
		// loop through the board to search words
		for (int i = 0; i < boggleBoard.length; i++)
		{
			for (int j = 0; j < boggleBoard.length; j++)
			{
				resetBoard(boggleBoard);//Reset the visited square board
				currentString = new StringBuilder();
				if(boggleBoard[i][j] == '*'){//handle the * character. Loop from A to Z.
					visitedSquares[i][j] = 1;
					for(char nChar = 'A'; nChar <= 'Z'; nChar++){
						currentString.append(nChar);						
						backTrack(i, j, 0, wordLength, boggleBoard, dictionary);
						currentString.deleteCharAt(currentString.length()-1);
					}
				}else{
					currentString.append(boggleBoard[i][j]);
					visitedSquares[i][j] = 1;
					backTrack(i, j, 0, wordLength, boggleBoard, dictionary);
				}	
			}
		}
        return result;
    }


    //reset visited squares to 0
	private void resetBoard(char[][] boggleBoard){
		for (int i = 0; i < boggleBoard.length; i++)
		{
			for (int j = 0; j < boggleBoard.length; j++)
			{
				visitedSquares[i][j] = 0;
			}
		}
	}


    private void backTrack(int row, int col, int depth, int wordLength, char[][] boggleBoard, DictInterface dictionary){
        //Loop in all 8 directions
		for(int direction=0; direction<8; direction++){
			//Check if it is a valid square to visit
			if(isValid(row, col, direction, boggleBoard)){
				char nChar = nextChar(row, col, direction, boggleBoard);
				if (nChar == '*') {//Handles * character, loops from A to Z.
					for(char c = 'A'; c <= 'Z'; c++){
						executeSearch(c, direction, row, col, depth, wordLength, boggleBoard, dictionary);
					}
				}else{
					executeSearch(nChar, direction, row, col, depth, wordLength, boggleBoard, dictionary);
				}			
			}
		}
	}

	private void executeSearch(char nChar, int direction, int row, int col, int depth, int wordLength, char[][] boggleBoard, DictInterface dictionary){
		currentString.append(nChar);
		//mark the letter as visited
		Coordinates nextCoords = nextCoordinates(row, col, direction);
		visitedSquares[nextCoords.row][nextCoords.col] = 1;
		int res = dictionary.searchPrefix(currentString);//call dictionary object to search prefix or word

		if(res == 1){ //prefix but not word
			if (currentString.length() != wordLength ) {
				backTrack(nextCoords.row, nextCoords.col, depth + 1, wordLength, boggleBoard, dictionary);
			}
					
		}
		if(res == 2){ //word but not prefix
			if(currentString.length() == wordLength){
                result.add(currentString.toString());
			}
		}

		if(res == 3){ //word and prefix
			if(currentString.length() == wordLength){
				result.add(currentString.toString());
			}
			backTrack(nextCoords.row, nextCoords.col, depth + 1, wordLength, boggleBoard, dictionary);
		}

		currentString.deleteCharAt(currentString.length()-1);
		visitedSquares[nextCoords.row][nextCoords.col] = 0;
	}

    //is the letter already visited or are we at an edge of the board?
	private boolean isValid(int row, int col, int direction, char[][] boggleBoard){
		Coordinates coords = nextCoordinates(row, col, direction);
				
		if(coords.row < 0){
			return false;
		}
		if(coords.row >= boggleBoard.length){
			return false;
		}
		if(coords.col >= boggleBoard.length){
			return false;
		}
		if(coords.col < 0){
			return false;
		}

		//If the letter is visited
		if (visitedSquares[coords.row][coords.col] == 1) {
			return false;
		}
	
		//If the letter is #
		if(boggleBoard[coords.row][coords.col] == '#') {
			return false;
		}
		
		return true;
    }


    private Coordinates nextCoordinates(int row, int col, int direction){
		Coordinates result = null;
		switch(direction){
			case 0: //up left
				result = new Coordinates(row-1, col-1);
				break;
			case 1: //up
				result = new Coordinates(row-1, col);
				break;
			case 2: //up right
				result = new Coordinates(row-1, col+1);
				break;
			case 3: //right
				result = new Coordinates(row, col+1);
				break;
			case 4: //bottom right
				result = new Coordinates(row+1, col+1);
				break;
			case 5: //bottom
				result = new Coordinates(row+1, col);
				break;
			case 6: //bottom left
				result = new Coordinates(row+1, col-1);
				break;
			case 7: //left
				result = new Coordinates(row, col-1);
				break;
		}
		return result;
	}


    private char nextChar(int row, int col, int direction, char[][] boggleBoard){
		Coordinates coords = nextCoordinates(row, col, direction);
		return boggleBoard[coords.row][coords.col];
	}





    //inner class
	private class Coordinates {
		int row;
		int col;

		private Coordinates(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}


