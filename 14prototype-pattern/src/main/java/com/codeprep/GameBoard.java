package com.codeprep;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Prototype<GameBoard>{

    private List<GamePiece> gamePieces = new ArrayList<>();

    public void addGamePiece(GamePiece gamePiece) {
        gamePieces.add(gamePiece);
    }

    public GamePiece getGamePiece(int position) {
        return gamePieces.get(position);
    }

    public void showBoardState(){
        for(GamePiece gamePiece : gamePieces){
            System.out.println(gamePiece);
        }
    }

    @Override
    public GameBoard clone() {
        GameBoard clonedBoard = new GameBoard();
        clonedBoard.gamePieces.addAll(this.gamePieces.stream()
                .map(GamePiece::clone)
                .toList());
        return clonedBoard;
    }
}
