package com.example.jithin.chessquad.chess;

/**
 * Created by jithin on 24/1/18.
 */

public class Queen extends Piece {

    public Queen(Board board, String piece, char side, int id){
        super(board, piece, side, id);
    }

    @Override
    public void addHighlight(){
        Rook.addHigh(this.X, this.Y, this.board);
        Bishop.addHigh(this.X, this.Y, this.board);
    }
}
