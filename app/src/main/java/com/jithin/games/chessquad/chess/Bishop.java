package com.jithin.games.chessquad.chess;

import android.util.Log;

import static com.jithin.games.chessquad.chess.Utls.hex;
import static com.jithin.games.chessquad.chess.Utls.intOf;

/**
 * Created by jithin on 24/1/18.
 */

public class Bishop extends Piece {

    public Bishop(Board board, String piece, char side, int id){
        super(board, piece, side, id);
    }

    @Override
    public void addHighlight(){
        Bishop.addHigh(this.X, this.Y, this.board);
    }
    
    public static void addHigh(char X, char Y, Board board){
        Log.d("Adding Highlight", "Bishop");
        int y = intOf(Y)-1;
        int x = intOf(X)-1;
        while (y >= 0 && x >= 0){
            if (board.mtx[x][y] == null) {
                board.addHighlight(hex(x), hex(y));
                --x; --y;
            } else if (board.mtx[x][y].side != 'b') {
                board.addHighlight(hex(x), hex(y));
                break;
            } else { break; }
        }
        y = intOf(Y)-1;
        x = intOf(X)+1;
        while (y >=0 && x<=11 ){
            if(board.mtx[x][y] == null){
                board.addHighlight(hex(x), hex(y));
                ++x; --y;
            } else if(board.mtx[x][y].side!='b'){
                board.addHighlight(hex(x), hex(y));
                break;
            } else{ break; }
        }
        y = intOf(Y)+1;
        x = intOf(X)+1;
        while (y<=11 && x<=11 ){
            if(board.mtx[x][y] == null){
                board.addHighlight(hex(x), hex(y));
                ++x; ++y;
            } else if(board.mtx[x][y].side!='b'){
                board.addHighlight(hex(x), hex(y));
                break;
            } else{ break; }
        }
        y = intOf(Y)+1;
        x = intOf(X)-1;
        while (y<=11 && x>=0 ){
            if(board.mtx[x][y] == null){
                board.addHighlight(hex(x), hex(y));
                --x; ++y;
            } else if(board.mtx[x][y].side!='b'){
                board.addHighlight(hex(x), hex(y));
                break;
            } else{ break; }
        }
    }
}
