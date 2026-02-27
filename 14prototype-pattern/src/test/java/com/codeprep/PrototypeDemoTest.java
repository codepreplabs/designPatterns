package com.codeprep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeDemoTest {

    @Test
    void testClonedBoardIsIndependentFromOriginal() {
        GameBoard original = new GameBoard();
        original.addGamePiece(new GamePiece("red", 1));
        original.addGamePiece(new GamePiece("blue", 5));

        GameBoard clone = original.clone();

        // Mutate the clone
        clone.getGamePiece(0).setColor("green");
        clone.getGamePiece(1).setPosition(99);

        // Original should be unaffected
        assertEquals("red", original.getGamePiece(0).getColor());
        assertEquals(5, original.getGamePiece(1).getPosition());
    }

    @Test
    void testClonedBoardHasSameInitialValues() {
        GameBoard original = new GameBoard();
        original.addGamePiece(new GamePiece("red", 1));
        original.addGamePiece(new GamePiece("blue", 5));

        GameBoard clone = original.clone();

        assertEquals("red", clone.getGamePiece(0).getColor());
        assertEquals(1, clone.getGamePiece(0).getPosition());
        assertEquals("blue", clone.getGamePiece(1).getColor());
        assertEquals(5, clone.getGamePiece(1).getPosition());
    }

    @Test
    void testClonedBoardIsNotSameReference() {
        GameBoard original = new GameBoard();
        original.addGamePiece(new GamePiece("red", 1));

        GameBoard clone = original.clone();

        assertNotSame(original, clone);
        assertNotSame(original.getGamePiece(0), clone.getGamePiece(0));
    }

    @Test
    void testGamePieceClone() {
        GamePiece original = new GamePiece("red", 3);
        GamePiece clone = original.clone();

        assertEquals("red", clone.getColor());
        assertEquals(3, clone.getPosition());
        assertNotSame(original, clone);
    }
}


