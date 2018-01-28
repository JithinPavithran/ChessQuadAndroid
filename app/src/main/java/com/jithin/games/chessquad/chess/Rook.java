package com.jithin.games.chessquad.chess;

import android.util.Log;

import static com.jithin.games.chessquad.chess.Utls.*;

/**
 * Created by jithin on 23/1/18.
 */

public class Rook extends Piece {

    public Rook(Board board, String piece, char side, int id){
        super(board, piece, side, id);
    }

    @Override
    public void addHighlight(){
        Rook.addHigh(this.X, this.Y, this.board);
    }
    
    public static void addHigh(char X, char Y, Board board){
        Log.d("Adding Highlight", "Rook");
        int y = intOf(Y)-1;
        int x = intOf(X);
        while (y >= 0){
            if(board.mtx[x][y]==null) {
                board.addHighlight(X, hex(y));
                --y;
            } else if(board.mtx[x][y].side!='b'){
                board.addHighlight(X, hex(y));
                break;
            } else{ break; }
        }
        y = intOf(Y)+1;
        while (y <= 11){
            if(board.mtx[x][y]==null) {
                board.addHighlight(X, hex(y));
                ++y;
            } else if(board.mtx[x][y].side!='b'){
                board.addHighlight(X, hex(y));
                break;
            } else{ break; }
        }
        y = intOf(Y);
        x = x - 1;
        while (x >= 0){
            if(board.mtx[x][y]==null){
                board.addHighlight(hex(x), Y);
                x = x - 1;
            } else if(board.mtx[x][y].side!='b'){
                board.addHighlight(hex(x), Y);
                break;
            } else{ break; }
        }
        x = intOf(X) + 1;
        while (x <= 11){
            if(board.mtx[x][y]==null){
                board.addHighlight(hex(x), Y);
                x = x + 1;
            } else if(board.mtx[x][y].side!='b'){
                board.addHighlight(hex(x), Y);
                break;
            } else { break; }
        }
    }
    
}
