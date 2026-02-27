package com.codeprep;

public class PrototypeDemo
{
    public static void main( String[] args )
    {
        // Create the original game board and add some pieces to it
        GameBoard gameBoard = new GameBoard();
        gameBoard.addGamePiece(new GamePiece("red", 1));
        gameBoard.addGamePiece(new GamePiece("blue", 5));

        System.out.println("=== Original Board (before cloning) ===");
        gameBoard.showBoardState();

        // Clone the board using the Prototype pattern.
        // This creates a deep copy, so changes to the clone won't affect the original.
        GameBoard clonedBoard = gameBoard.clone();

        System.out.println("\n=== Cloned Board (immediately after cloning) ===");
        clonedBoard.showBoardState();

        // Mutate a piece on the cloned board to demonstrate independence from the original
        clonedBoard.getGamePiece(0).setColor("green");

        System.out.println("\n=== Cloned Board (after changing piece 0 color to green) ===");
        clonedBoard.showBoardState();

        // Show that the original board remains unchanged after mutating the clone
        System.out.println("\n=== Original Board (should be unchanged) ===");
        gameBoard.showBoardState();
    }
}
