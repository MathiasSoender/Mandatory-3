package Checkers_V2.java;
import java.util.Scanner;
//Readme:
//Made by Mathias Søndergaard, s174426.
//Input should be: X Y, and should be integers.
//So seperate the two integers with ONE space.
//After choosing the piece to move, the piece is marked with a "X" to denote the piece about to be moved.
//If one inputs something invalid, the turn is restarted.
//No jumping/multijumping, and no generics are used.



//Making a Board class, this board is then used later:
class Board{
	private int[][] board;

	//This method extract info from the board:
	public int getPieceInfo(Position newpos){

		return board[newpos.posY][newpos.posX];
	}
	//This method sets specific board coordinates
	public void setBoard(Position oldpos, int val) {
		board[oldpos.posY][oldpos.posX]=val;		
	}
	//Reset board
	public void resetBoard() {
		board= new int[][] {
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{2,0,2,0,2,0,2,0},
			{0,2,0,2,0,2,0,2},
			{2,0,2,0,2,0,2,0}		
	};
	}
	public void printBoard() {
		//function for printing the matrix to console
			String l1="  +================+";
			String l3="^ +================+";
			String l2="   0 1 2 3 4 5 6 7     <-X-axis";
			String l4="|  0 1 2 3 4 5 6 7     ";
			String l5="|";
			String l6="Y-axis";

			System.out.println(l2); System.out.println(l1);
			//Looping all the elements of the playing matrix, and mapping elements to strings and finally printing these strings:
			for(int i=0;i<=7;i++) {
				String TempString=i+" |";
				for(int j=0;j<=7;j++) {
					if(board[i][j]==0) {
						TempString=TempString+"  ";
					}
					if(board[i][j]==1) {
						TempString=TempString+"1 ";
									
					}
					if(board[i][j]==2) {
						TempString=TempString+"2 ";
					}
					if(board[i][j]==3) {
						TempString=TempString+"X ";
					}
					
					
				}
				TempString=TempString+"| "+i;
					System.out.println(TempString);
				}
				System.out.println(l3); System.out.println(l4); System.out.println(l5); System.out.println(l6);
	}
}
//Creating an interface for methods concerning pieces. Since there is only 1 piece, this class is inheritated once. However, different pieces can easily be created now.
interface Piece{
	public boolean isValidPiece(Position newpos, Board board);
	public boolean isValidMove(Position oldpos, Position newpos, Board board);
	public void Move(Position oldpos, Position newpos, Board board);
}
//Create class for the players (there is 2 players, player 1 & 2).
class PlayerPosition{
	public int Playernum;
	//set player :
	public void setPlayer(int val) {
		this.Playernum=val;

		}
	//a method to print the player value:
	public String getPlayer() {
		return String.valueOf(Playernum);
		
	}
	
	}
//Make a class called position, which holds position infomation. This is used in the other classes.
class Position{
	
	public int posX;
	public int posY;
	public Position(int v1,int v2) {
		this.posX=v1;
		this.posY=v2;
			}
	}
//Implement piece, so we can get the methods from there.
class PlayerPiece extends PlayerPosition implements Piece{ // extend PlayerPosition, so i get call variable "Playernum".

	
	//check for validPiece
	public boolean isValidPiece(Position oldpos, Board board) {
		if ((oldpos.posX>=0 && oldpos.posX<=7 && oldpos.posY>=0 && oldpos.posY<=7) &&
			((board.getPieceInfo(oldpos)==1 && Playernum==1) ||
			(board.getPieceInfo(oldpos)==2 && Playernum==2))) {
			return true;			
		}else {return false;}
		}

		
	//Check for validMove, here board is used, old position and new position
	public boolean isValidMove(Position oldpos, Position newpos, Board board) {
		
		int difX = Math.abs(oldpos.posX-newpos.posX);
		int difY = oldpos.posY-newpos.posY;
		if ((difX==1 && board.getPieceInfo(newpos)==0) && ((difY==-1 && Playernum==1) ||
			(difY==1 && Playernum==2))) {
			return true;
			
		}
	
		else {return false;}
	}
	//this calls the setBoard method created in board class. 
	//So it evolves the game.
	//I guess this is quite strange for the playerPiece class to do. 
	public void Move(Position oldpos, Position newpos, Board board) {
		board.setBoard(oldpos, 0);
		board.setBoard(newpos,Playernum);
		
	}
	}
//We need an utility class to get inputs from players:
class Scanner2Position{
	Scanner s = new Scanner(System.in);
	private int pieceX;
	private int pieceY;
	//Parsing the infomation:
	public void Search() {
		String arg = s.nextLine();
		String[] argV = arg.split("\\s"); 
		//Implement error messages & error handling:
		if(argV.length==2) {
		try {
		this.pieceX = Integer.parseInt(argV[0]);
		this.pieceY = Integer.parseInt(argV[1]);
		}catch(NumberFormatException d) {
			System.err.println("Wrong type of input, try again...");		}
		}	
		else {System.err.println("Wrong type of input, try again...");}
	}
	//And returning the infomation as a position:
	public Position s2p() {
	Position posS = new Position(pieceX,pieceY);
	return posS;
	}

}


public class Checkers_V2 {
	public static void main(String[] args) {
		//Start up everything
		boolean turn = true;
		Board board = new Board();
		board.resetBoard();
		PlayerPiece player = new PlayerPiece();
		Scanner2Position info = new Scanner2Position();
		

		
		while(true) {
		//Check whoms turn it is
		if (turn) {
			player.setPlayer(1);
			
		}else {player.setPlayer(2);}
		board.printBoard();
		
		//Get the piece
		System.out.println("Player: "+(player.getPlayer())+", what do you want to move?: ");
		info.Search();
		Position CPos = info.s2p();
		
		
		//is piece valid?
		if (player.isValidPiece(CPos,board)) {
			//Make choosen piece into a X, for User to see
			board.setBoard(CPos, 3);
			board.printBoard();
			board.setBoard(CPos, player.Playernum);
			//Get move location
			System.out.println("Player "+(player.getPlayer())+", where do you want to move?: ");
			info.Search();
			Position CPos1 = info.s2p();


			if (player.isValidMove(CPos,CPos1,board)) {
				//move location was okay, move the piece, and change turns:
				player.Move(CPos, CPos1, board);
				turn = !turn;
			
				//Some simple "error" messages for the user:
			}else { System.out.println("Cannot be moved here");}
		}else { System.out.println("This piece cannot be choosen");}
		}
			
			
			
		
		
		
	}
}


