import java.util.Scanner;


class Board{
	public int[][] board;
	
	public int getPieceInfo(Position newpos){


		return board[newpos.posY][newpos.posX];
	}
	public void setBoard(Position oldpos, int val) {
		board[oldpos.posY][oldpos.posX]=val;		
	}
	
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
					
					
				}
				TempString=TempString+"| "+i;
					System.out.println(TempString);
				}
				System.out.println(l3); System.out.println(l4); System.out.println(l5); System.out.println(l6);
	}
}

interface Piece{
	public boolean isValidPiece(Position newpos, Board board);
	public boolean isValidMove(Position oldpos, Position newpos, Board board);
	public void Move(Position oldpos, Position newpos, Board board);
}
class PlayerPosition{
	int Playernum;
	public void setPlayer(int val) {
		if (val==1) {
			Playernum=val;
		}
		if (val==2) {
			Playernum=-1;
		}
		else {//Throw exception//
		}
		}
	public String getPlayer() {
		return String.valueOf(Playernum);
		
	}
	
	}
class Position{
	
	public int posX;
	public int posY;
	public Position(int v1,int v2) {
		this.posX=v1;
		this.posY=v2;
			}
	}

class PlayerPiece extends PlayerPosition implements Piece{
	Board board;
	public Position oldpos;
	public Position newpos;
	
	public boolean isValidPiece(Position oldpos, Board board) {
		if ((oldpos.posX>=0 && oldpos.posX<=7 && oldpos.posY>=0 && oldpos.posY<=7) &&
			((board.getPieceInfo(oldpos)==1 && Playernum==1) ||
			(board.getPieceInfo(oldpos)==2 && Playernum==-1))) {
			return true;			
		}else {return false;}
		
	}
	public boolean isValidMove(Position oldpos, Position newpos, Board board) {
		this.oldpos=oldpos;
		this.newpos=newpos;
		
		int difX = Math.abs(oldpos.posX-newpos.posX);
		int difY = oldpos.posY-newpos.posY;
		if ((difX==1 && board.getPieceInfo(newpos)==0) && ((difY==-1 && Playernum==1) ||
			(difY==1 && Playernum==-1))) {
			return true;
			
		}
	
		else {return false;}
	}
	public void Move(Position oldpos, Position newpos, Board board) {
		board.setBoard(oldpos, 0);
		if(Playernum==1) {
			board.setBoard(newpos, 1);
			
		}else {
			board.setBoard(newpos,2);
		}
		
	}
	
		
		
	}

class Scanner2Position{
	Scanner s = new Scanner(System.in);
	String arg = s.nextLine();
	String[] argV = arg.split("\\s+"); 
	int pieceX = Integer.parseInt(argV[0]);
	int pieceY = Integer.parseInt(argV[1]);

	public int X() {
		return pieceX;}
	public int Y() {
		return pieceY;		

		
	}
}


public class Checkers_V2 {
	public static void main(String[] args) {
		boolean turn = true;
		Board board = new Board();
		board.resetBoard();
		PlayerPiece player = new PlayerPiece();
		

		board.printBoard();
		
		while(true) {
		if (turn) {
			player.setPlayer(1);
			
		}else {player.setPlayer(2);}
		
		System.out.println("Player: "+(player.getPlayer())+", what do you want to move?: ");
		Scanner2Position info = new Scanner2Position();
		Position CPos = new Position(info.X(),info.Y());
		
		
		if (player.isValidPiece(CPos,board)) {
			
			System.out.println("Player "+(player.getPlayer())+", where do you want to move?: ");
			Scanner2Position info1 = new Scanner2Position();
			Position CPos1 = new Position(info1.X(),info1.Y());	


			if (player.isValidMove(CPos,CPos1,board)) {
				player.Move(CPos, CPos1, board);
				board.printBoard();
				turn = !turn;
				
			}else { System.out.println("Cannot be moved here");}
		}else { System.out.println("This piece cannot be choosen");}
		}
			
			
			
		
		
		
	}
}


