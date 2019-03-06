package Checkers_V2.java;
//Not really sure how testing should be done, but I tried testing some parts of the system.
//I should have covered most classes.
import static org.junit.Assert.*;
import org.junit.Test;

public class Junit {

	@Test
	public void PlayerTest() {
		PlayerPiece player = new PlayerPiece();
		player.setPlayer(1);
		assertEquals("100",player.getPlayer()); //Should fail
		
		
		
	}
	@Test
	public void PositionTest() {
		Position Pos = new Position(2,1);
		assertEquals(2,Pos.posX);
		assertEquals(1,Pos.posY);

	}
	
	@Test
	public void boardTest() {
		Position Pos = new Position(2,1);
		Board board = new Board();
		board.resetBoard();
		assertEquals(1,board.getPieceInfo(Pos));
		board.setBoard(Pos, 100);
		assertEquals(120,board.getPieceInfo(Pos)); //Should fail
			
		
		
		
	}
	@Test
	public void PlayerPieceTest() {
		Board board = new Board();
		PlayerPiece player = new PlayerPiece();
		board.resetBoard();
		Position newPos = new Position(0,3);
		Position oldPos = new Position(1,2);
		player.setPlayer(1);
		assertTrue(player.isValidMove(oldPos, newPos, board));
		assertTrue(player.isValidPiece(oldPos, board));
		player.Move(oldPos, newPos, board);
		assertEquals(0,board.getPieceInfo(oldPos));
	}
	
	

}
