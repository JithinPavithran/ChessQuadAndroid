package com.jithin.games.chessquad.chess;

import android.util.Log;

import static com.jithin.games.chessquad.chess.Utls.hex;
import static com.jithin.games.chessquad.chess.Utls.intOf;

/**
 * Created by jithin on 24/1/18.
 */

public class Knight extends Piece {

    public Knight(Board board, String piece, char side, int id){
        super(board, piece, side, id);
    }

    @Override
    public void addHighlight(){
        Log.d("Adding Highlight", "Knight");
        int x = intOf(this.X);
        int y = intOf(this.Y);
        if(x+1 <= 11){
            if(y+2<=11 && (board.mtx[x+1][y+2] == null ||
                    board.mtx[x+1][y+2].side != Board.MINE)) {
                board.addHighlight(hex(x + 1), hex(y + 2));
            }
            if(y-2 >= 0 && (board.mtx[x+1][y-2] == null ||
                    board.mtx[x+1][y-2].side != Board.MINE)){
                board.addHighlight(hex(x+1), hex(y-2));
            }
            if(x+2 <= 11){
                if(y+1<=11 && (board.mtx[x+2][y+1] == null ||
                        board.mtx[x+2][y+1].side != Board.MINE)){
                    board.addHighlight(hex(x+2), hex(y+1));
                }
                if(y-1>=0 && (board.mtx[x+2][y-1] == null ||
                        board.mtx[x+2][y-1].side != Board.MINE)){
                    board.addHighlight(hex(x+2), hex(y-1));
                }
            }
        }
        if(x-1 >= 0){
            if(y+2 <= 11 && (board.mtx[x-1][y+2] == null ||
                    board.mtx[x-1][y+2].side != Board.MINE) ){
                board.addHighlight(hex(x-1), hex(y+2));
            }
            if(y-2 >= 0 && (board.mtx[x-1][y-2] == null ||
                    board.mtx[x-1][y-2].side != Board.MINE)){
                board.addHighlight(hex(x-1), hex(y-2));
            }
            if(x-2 >= 0){
                if(y+1 <= 11 && (board.mtx[x-2][y+1] == null ||
                        board.mtx[x-2][y+1].side != Board.MINE)){
                    board.addHighlight(hex(x-2), hex(y+1));
                }
                if(y-1 >= 0 && (board.mtx[x-2][y-1] == null ||
                        board.mtx[x-2][y-1].side != Board.MINE)){
                    board.addHighlight(hex(x-2), hex(y-1));
                }
            }
        }
    }
}
