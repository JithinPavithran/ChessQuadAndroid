package com.jithin.games.chessquad.chess;

import android.util.Log;

import static com.jithin.games.chessquad.chess.Utls.hex;
import static com.jithin.games.chessquad.chess.Utls.intOf;

/**
 * Created by jithin on 24/1/18.
 */

public class King extends Piece {

    public King(Board board, String piece, char side, int id){
        super(board, piece, side, id);
    }

    @Override
    public void addHighlight(){
//        TODO: Avoid check cells
        Log.d("Addign Highlight", "King");
        int x = intOf(this.X);
        int y = intOf(this.Y);
        if(x+1 <= 11){
            if(board.mtx[x+1][y]==null || board.mtx[x+1][y].side!=Board.MINE){
                board.addHighlight(hex(x+1), hex(y));
            }
            if(y+1 <= 11 && (board.mtx[x+1][y+1]==null ||
                    board.mtx[x+1][y+1].side!=Board.MINE)){
                board.addHighlight(hex(x+1), hex(y+1));
            }
            if(y-1 >= 0 && (board.mtx[x+1][y-1]==null ||
                    board.mtx[x+1][y-1].side!=Board.MINE)){
                board.addHighlight(hex(x+1), hex(y-1));
            }
        }
        if(x-1 >= 0){
            if(board.mtx[x-1][y]==null || board.mtx[x-1][y].side!=Board.MINE){
                board.addHighlight(hex(x-1), hex(y));
            }
            if(y+1 <= 11 && (board.mtx[x-1][y+1]==null ||
                    board.mtx[x-1][y+1].side!=Board.MINE)){
                board.addHighlight(hex(x-1), hex(y+1));
            }
            if(y-1 >= 0 && (board.mtx[x-1][y-1]==null ||
                    board.mtx[x-1][y-1].side!=Board.MINE)){
                board.addHighlight(hex(x-1), hex(y-1));
            }
        }
        if(y-1 >= 0 && (board.mtx[x][y-1]==null ||
                board.mtx[x][y-1].side!=Board.MINE)){
            board.addHighlight(hex(x), hex(y-1));
        }
        if(y+1 <= 11 && (board.mtx[x][y+1]==null ||
                board.mtx[x][y+1].side!=Board.MINE)){
            board.addHighlight(hex(x), hex(y+1));
        }
    }
}
